package com.webkorps.friendBook.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webkorps.friendBook.Model.User;
import com.webkorps.friendBook.Repository.UserRepository;
import com.webkorps.friendBook.ServiceImpl.RequestsServiceImpl;
import com.webkorps.friendBook.ServiceImpl.UserServiceImpl;

@Controller
@RequestMapping("/request")
public class RequestController {
	@Autowired
	RequestsServiceImpl requestsServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceimpl;
	 Logger logger = LoggerFactory.getLogger(RequestController.class.getName());
		
      @PostMapping("sendRequest")
    public ResponseEntity<String> sendRequest(@RequestParam("reciever") String reciever,@RequestParam("sender") String sender,HttpServletRequest request,Model m)
     {
    	  logger.info("Inside : login()");
	requestsServiceImpl.sendRequest(reciever,sender);
	HttpSession session=request.getSession(false);
 	User u=(User)session.getAttribute("user");
 	User searchedPerson=userServiceimpl.getUser(reciever);
   	m.addAttribute("user", u);
    m.addAttribute("serchedPerson",searchedPerson);
    m.addAttribute("searchedPersonName",searchedPerson.getUserName());
    logger.info("end : login()");
	 return new ResponseEntity<>("Send",HttpStatus.OK);
     }
@PostMapping("deleteRequest")
public ResponseEntity<String> deleteRequest(@RequestParam("reciever") String reciever,@RequestParam("sender") String sender,HttpServletRequest request,Model m)
{
	  logger.info("Inside : deleteRequest()");
	requestsServiceImpl.removeRequest(sender,reciever);
	HttpSession session=request.getSession(false);
 	User u=(User)session.getAttribute("user");
 	User searchedPerson=userServiceimpl.getUser(reciever);
   	m.addAttribute("user", u);
    m.addAttribute("serchedPerson",searchedPerson);
    m.addAttribute("searchedPersonName",searchedPerson.getUserName());
    boolean requested=false;
    m.addAttribute("requested",requested);
    logger.info("end : deleteRequest()");
	return new ResponseEntity<>("Delete",HttpStatus.OK);
	}
@PostMapping("notification")
public ResponseEntity<Integer> notification(HttpServletRequest request,HttpServletResponse response,Model m)
{
	logger.info("Inside : notification()");
	HttpSession session=request.getSession(false);
	if(session==null)
	{
	 	logger.info("end : notification()");
   
		//response.sendRedirect("/users");
	
	}
	else {
 	User u=(User)session.getAttribute("user");
 	int count=requestsServiceImpl.checkNotification(u);
   	m.addAttribute("user", u);
   	m.addAttribute("notification",count);
   	logger.info("end : notification()");
    return new ResponseEntity<>(count,HttpStatus.OK);
	}
	return new ResponseEntity<>(0,HttpStatus.OK);
	}

@PostMapping("accepted")
public ResponseEntity<String> accepted(HttpServletRequest request,Model m,@RequestParam String sender)
{
 	logger.info("inside : accepted()");
	HttpSession session=request.getSession(false);
 	User u=(User)session.getAttribute("user");
    requestsServiceImpl.addToFollower(sender,u.getUserName());
    logger.info("end : accepted()");
    return new ResponseEntity<>("added",HttpStatus.OK);
}
@PostMapping("remove")
public ResponseEntity<String> remove(HttpServletRequest request,Model m,@RequestParam String sender)
{
	logger.info("inside : remove()");
	HttpSession session=request.getSession(false);
 	User u=(User)session.getAttribute("user");
    requestsServiceImpl.removeRequest(sender,u.getUserName());
    logger.info("end : remove()");
    return new ResponseEntity<>("removed",HttpStatus.OK);
}
@PostMapping("sendRequestFromNotifications")
public ResponseEntity<String> addUser(HttpServletRequest request,Model m,@RequestParam String sender)
{
	logger.info("inside : addUser()");
	HttpSession session=request.getSession(false);
 	User u=(User)session.getAttribute("user");
	requestsServiceImpl.sendRequest(sender,u.getUserName());
	logger.info("end : addUser()");
    return new ResponseEntity<>("Send",HttpStatus.OK);
}
@GetMapping("checkFriendshipStatus")
public ResponseEntity<String> checkFriendshipStatus(@RequestParam("searchedpersonName") String searchedpersonName,HttpServletRequest request )
{
	logger.info("inside : checkFriendshipStatus()");
	HttpSession session=request.getSession();
    User user=(User) session.getAttribute("user");
    String userName=user.getUserName();
    String state=  requestsServiceImpl.isFriend(userName,searchedpersonName);
    logger.info("end : checkFriendshipStatus()");
    return new ResponseEntity<>(state,HttpStatus.OK);
}

}
