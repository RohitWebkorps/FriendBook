package com.webkorps.friendBook.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webkorps.friendBook.Model.User;
import com.webkorps.friendBook.ServiceImpl.CommentsServiceImpl;
import com.webkorps.friendBook.ServiceImpl.PostServiceImpl;

@Controller
@RequestMapping("commons")
public class CommonController {
	@Autowired
	PostServiceImpl postServiceImpl;
	@Autowired
	CommentsServiceImpl commentsServiceImpl;
	Logger logger = LoggerFactory.getLogger(CommonController.class.getName());
	
    @GetMapping("homes")
      public String GoToHome( Model m,HttpServletRequest request)
       {
    	 logger.info("Inside : GoToHome()");
              HttpSession session=request.getSession(false);
              User u=(User) session.getAttribute("user");
              String userName=u.getUserName();
              m.addAttribute("post", postServiceImpl.getFeedPost(u));
              m.addAttribute("comments",commentsServiceImpl.getComments(u));
              if(session==null)
              {
             	 logger.info("end : GoToHome()");
             	 return "LoginPage";
              }
          	u=(User)session.getAttribute("user");
            m.addAttribute("user", u);	
       	 logger.info("end : GoToHome()");
             return "Home";  
       }
}
