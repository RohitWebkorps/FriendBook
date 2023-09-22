package com.webkorps.friendBook.Service;

import java.util.List;

import com.webkorps.friendBook.Model.Comments;
import com.webkorps.friendBook.Model.User;

public interface CommentsService {
	 List<Comments>  getComments(User searchedPerson);
	 void saveComment(String commentedBy, String comments, int postId,String personName);
	
}
