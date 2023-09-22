package com.webkorps.friendBook.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webkorps.friendBook.Controller.UserController;
@Entity
public class Post {
	 @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
private String userName;
private String post;
private Integer likes;
public Post() {
}
public Post(String userName, String post) {
	super();
	this.userName = userName;
	this.post = post;
	this.likes=0;
}
public Integer getPostId() {
	return postId;
}
public void setPostId(Integer postId) {
	this.postId = postId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPost() {
	return post;
}
public void setPost(String post) {
	this.post = post;
}
public Integer getLikes() {
	return likes;
}
public void setLikes(Integer likes) {
	this.likes = likes;
}
}
