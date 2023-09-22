package com.webkorps.friendBook.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.List;


import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webkorps.friendBook.Exception.InvalidCredentialsException;
import com.webkorps.friendBook.Exception.UserNotFoundException;
import com.webkorps.friendBook.Model.User;
import com.webkorps.friendBook.Repository.UserRepository;
import com.webkorps.friendBook.Service.LikesService;
import com.webkorps.friendBook.ServiceImpl.CommentsServiceImpl;
import com.webkorps.friendBook.ServiceImpl.PostServiceImpl;
import com.webkorps.friendBook.ServiceImpl.RequestsServiceImpl;
import com.webkorps.friendBook.ServiceImpl.UserServiceImpl;

@Controller
@RequestMapping("users")
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	CommentsServiceImpl commentsServiceImpl;
	@Autowired
	LikesService likesServiceImpl;
	@Autowired
	PostServiceImpl postServiceImpl;
	@Autowired
	RequestsServiceImpl requestServiceImpl;
 Logger logger = LoggerFactory.getLogger(UserController.class.getName());
	
	@GetMapping
	  public String login()
	  {
		logger.info("Inside : login()/");
	 	    return "LoginPage";
	  } 
	@PostMapping
		public String addUser(@ModelAttribute User u,Model m)
		{
		logger.info("Inside : addUser()");
			User user= new User(u.getFullName(),u.getEmail(),u.getPassword(),u.getFullName());
			m.addAttribute(user);
			userServiceImpl.addUser(user);
			m.addAttribute("username",user.getUserName());
			logger.info("End : addUser()");
		    return "showUserName";	
	    }
	
	@GetMapping("register")
    public String showRegistrationPage() {
		logger.info("Inside : showRegistrationPage()/");
        return "Registration"; 
    }

  @PostMapping("authenticate")  
  public String homeHandler(@RequestParam String userName,@RequestParam String password, Model m,HttpServletRequest request)
  {	
	  logger.info("Inside : homeHandler()");
	  userName=userName.trim();
	  password=password.trim();
	  
     boolean isPresent = userServiceImpl.isUserPresent(userName, password);

      if (isPresent)
      {
    	  User u=userServiceImpl.getUser(userName);
          HttpSession session=request.getSession();
          session.setAttribute("user",u);
    	  m.addAttribute("user", u); 
    	  logger.info("End : homeHandler()");
    	  return "redirect:profile";
      } 
      logger.error("Error :User Not Found() homeHandler");
        
          throw new InvalidCredentialsException("UserName or password is Incorrect");       
   }
  
  
  @GetMapping("logout")
  public String logout(HttpServletRequest request)
  {
	  logger.info("Inside : logout()");
	 HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }
	    logger.info("Inside : logout()/");
	    return "redirect:/users";
  }
   
      @GetMapping("profile")
      public String gotoProfile( Model m,HttpServletRequest request)
      {
    	  logger.info("Inside : gotoProfile()");
    	HttpSession session=request.getSession(false);
           	if(session==null)
           {
           		logger.info("end : gotoProfile()");	
          	 return "LoginPage";
           }
    	User u=(User)session.getAttribute("user");
      	m.addAttribute("user", u);
        Integer followers=requestServiceImpl.countFollowers(u);
        Integer following=requestServiceImpl.countFollowing(u);
        m.addAttribute("followers",followers);
        m.addAttribute("following",following);
        m.addAttribute("post", postServiceImpl.getPost(u));
        m.addAttribute("comments",commentsServiceImpl.getComments(u));
        logger.info("end : gotoProfile()");
          return "UserProfile";  
       }
         
    @PostMapping("upload")
    public String UploadProfilePic(@RequestParam("profileImage") MultipartFile file, HttpServletRequest request) 
    {
    	try {
    	logger.info("Inside : UploadProfilePic()");
    	HttpSession session=request.getSession(false);
    	User u=(User)session.getAttribute("user");
    	String userName=u.getUserName();
    	
    if(userServiceImpl.profileSave(file,userName))
    {
    	session.setAttribute("user",u);
    	logger.info("end : UploadProfilePic()");
    return "redirect:profile";
    }
    } catch(Exception e)
    	{
		System.out.println(e);
	}
    return "error";
    }

    

    @PostMapping("updateDetails")
    public String UdateDetails(@RequestParam("favSong")String favSong,@RequestParam("favBook")String favBook,@RequestParam("favPlace") String favPlace,@RequestParam("userName")String UserName,Model m,HttpSession session)
    {
    	logger.info("Inside : UdateDetails()");
    	User u=userServiceImpl.updateDetails(favSong,favBook,favPlace,UserName);
    	session.setAttribute("user",u);
    	logger.info("end : UdateDetails()");
		return "redirect:profile";
    }
   
    @PostMapping("/searchList")
    public String searchUser(@RequestParam("searchedPersonName") String searchedPersonName, Model m, HttpServletRequest request)
    {
    	logger.info("Inside : searchUser()");
    	HttpSession session=request.getSession();
    	User you=(User) session.getAttribute("user");
    	List searchedPerson=userServiceImpl.getSimilarUser(searchedPersonName);
       if( !searchedPerson.isEmpty())
       {
    	m.addAttribute("user",you);
    	m.addAttribute("searchedPerson",searchedPerson);
        m.addAttribute("followers",requestServiceImpl.countFollowers(you));
        m.addAttribute("following",requestServiceImpl.countFollowers(you));
        logger.info("end : searchUser()");
    	return "SearchedList";
       }
       logger.info("error : UserName or password is Incorrect | searchUser()");
       throw new UserNotFoundException("UserName or password is Incorrect");    
	
    }
    @PostMapping("/searched")
    public String searchedUsers(@RequestParam("searchedPersonName") String searchedPersonName, Model m, HttpServletRequest request)
    {
    	logger.info("Inside : searchedUsers()");
    	HttpSession session=request.getSession();
    	User user=(User) session.getAttribute("user");
    	String userName=user.getUserName();
    	User searchedPerson=userServiceImpl.getUser(searchedPersonName);
    	m.addAttribute("user",user);
    	m.addAttribute("searchedPerson",searchedPerson);
        m.addAttribute("followers",requestServiceImpl.countFollowers(user));
        m.addAttribute("following",requestServiceImpl.countFollowers(user));
        m.addAttribute("requested",requestServiceImpl.checkStatus(user,searchedPerson));
        m.addAttribute("post", postServiceImpl.getPost(searchedPerson));
        m.addAttribute("comments",commentsServiceImpl.getComments(searchedPerson));
        if(searchedPersonName.equals(userName))
        {
        	logger.info("end : searchedUsers()");	
        return "redirect:profile";
        }
        logger.info("end : searchedUsers()");
		return "OtherUserProfiles";
    }

@GetMapping("/seeNotification")
public String seeNotification(Model m, HttpServletRequest request)
{
	logger.info("Inside : searchedUsers()");	
	HttpSession session=request.getSession();
	User you=(User) session.getAttribute("user");
	m.addAttribute("totalRequest",requestServiceImpl.TotalRequest(you));
	logger.info("end : searchedUsers()");
    return "SeeNotification";
}

}






