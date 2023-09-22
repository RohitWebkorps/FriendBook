package com.webkorps.friendBook.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webkorps.friendBook.Model.User;
import com.webkorps.friendBook.ServiceImpl.CommentsServiceImpl;
import com.webkorps.friendBook.ServiceImpl.UserServiceImpl;

@Controller
@RequestMapping("/comments")
public class CommentsController {

	@Autowired
	CommentsServiceImpl commentsServiceImpl;
	@Autowired
	UserServiceImpl userServiceImpl;
	Logger logger = LoggerFactory.getLogger(CommentsController.class.getName());
	
	
	
	
	@PostMapping("addComment")
     public ResponseEntity<String> addComments(@RequestParam("PersonName") String personName,@RequestParam("comment") String comments,@RequestParam("postId") String postId,@RequestParam("commentedBy") String commentedBy,HttpServletRequest request,Model m) throws IOException
     {
		 logger.info("Inside : addComments()");
     Integer id=Integer.parseInt(postId);
     commentsServiceImpl.saveComment(commentedBy,comments,id,personName);
	 User person=userServiceImpl.getUser(personName);
	 m.addAttribute("comments",commentsServiceImpl.getComments(person));
	 logger.info("end : addComments()");
	 return new ResponseEntity<>("added",HttpStatus.OK);
	 }
}
