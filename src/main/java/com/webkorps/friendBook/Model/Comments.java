package com.webkorps.friendBook.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webkorps.friendBook.Controller.UserController;

@Entity
public class Comments {
	@Id
	private int commentId;
	private Integer postId;
	private String comments;
	private String userName;
	private String commentedBy;
	
	public String getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
	}
	public Comments(String commentedBy, String comments, Integer postId,String userName) {
		super();
		this.comments = comments;
		this.userName = userName;
		this.postId = postId;
		this.commentedBy=commentedBy;
	}
    public Comments() {
	    }
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}

   public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
