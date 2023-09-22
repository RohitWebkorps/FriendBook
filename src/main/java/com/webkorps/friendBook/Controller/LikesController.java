package com.webkorps.friendBook.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webkorps.friendBook.Model.User;
import com.webkorps.friendBook.Service.LikesService;

@Controller
@RequestMapping("/likes")
public class LikesController {
	@Autowired
	LikesService likesServiceImpl;
	Logger logger = LoggerFactory.getLogger(LikesController.class.getName());
	
@PostMapping("addLikes")
public ResponseEntity<String> addLikes(@RequestParam("PostId") Integer postId,HttpServletRequest request)
{
	 logger.info("Inside : addLikes()");
	HttpSession session=request.getSession();
    User user=(User) session.getAttribute("user");
    likesServiceImpl.AddLike(user.getUserName(), postId);
	 logger.info("end : addLikes()");
    return new ResponseEntity<>("added",HttpStatus.OK);
    
}
@PostMapping("removeLikes")
public ResponseEntity<String> removeLikes(@RequestParam("PostId") Integer postId,HttpServletRequest request)
{
	logger.info("Inside : removeLikes()");   
	HttpSession session=request.getSession();
    User user=(User) session.getAttribute("user");
    likesServiceImpl.removeLike(user.getUserName(), postId);
    return new ResponseEntity<>("added",HttpStatus.OK);
}
@GetMapping("checkLikeStatus")
public ResponseEntity<String> checkLikeStatus(@RequestParam("postId") Integer postId,HttpServletRequest request)
{
	logger.info("Inside : checkLikeStatus()");
	HttpSession session=request.getSession();
    User user=(User) session.getAttribute("user");
    String userName=user.getUserName();
    String state= likesServiceImpl.isLiked(userName,postId);
	logger.info("end : checkLikeStatus()");  
    return new ResponseEntity<>(state,HttpStatus.OK);
}
}
