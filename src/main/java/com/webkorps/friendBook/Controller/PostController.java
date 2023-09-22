package com.webkorps.friendBook.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.webkorps.friendBook.Model.User;
import com.webkorps.friendBook.ServiceImpl.PostServiceImpl;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostServiceImpl postServiceImpl;
	Logger logger = LoggerFactory.getLogger(PostController.class.getName());
	
	@PostMapping("createPost")
	public String createPost(@RequestParam("Postimg") MultipartFile Postimg,HttpServletRequest request) throws IOException
	{
		 logger.info("Inside : login()");
		HttpSession session=request.getSession();
	    User user=(User) session.getAttribute("user");
	    postServiceImpl.createPost(Postimg,user);
	    logger.info("end : login()");
	    return "redirect:Users/GoToProfile";
	}

}
