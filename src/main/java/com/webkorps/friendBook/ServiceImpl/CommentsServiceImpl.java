package com.webkorps.friendBook.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webkorps.friendBook.Controller.UserController;
import com.webkorps.friendBook.Model.Comments;
import com.webkorps.friendBook.Model.User;
import com.webkorps.friendBook.Repository.CommentsRepository;
import com.webkorps.friendBook.Service.CommentsService;
@Service
public class CommentsServiceImpl implements CommentsService{
	@Autowired
	CommentsRepository commentsRepository;
	Logger logger = LoggerFactory.getLogger(CommentsServiceImpl.class.getName());

	public List<Comments>  getComments(User searchedPerson) {
		logger.info("Inside :getComments");
		String searchedPersonName=searchedPerson.getUserName();
		List<Comments> comment=commentsRepository.findByUserName(searchedPersonName);
		logger.info("end :getComments");
		return comment;
	}
	public void saveComment(String commentedBy, String comments, int postId,String personName) {
		logger.info("Inside :saveComment");
		Comments comment=new Comments(commentedBy,comments,postId,personName);
		commentsRepository.save(comment);
		logger.info("end :saveComment");
	}


}
