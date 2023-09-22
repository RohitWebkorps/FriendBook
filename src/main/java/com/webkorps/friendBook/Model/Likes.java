package com.webkorps.friendBook.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webkorps.friendBook.Controller.UserController;
@Entity
public class Likes {
@Id
private String LikedBy;
private Integer postId;

public Likes(String likedBy, Integer postId) {
	LikedBy = likedBy;
	this.postId = postId;
}
public Likes() {
}
public String getLikedBy() {
	return LikedBy;
}
public void setLikedBy(String likedBy) {
	LikedBy = likedBy;
}
public Integer getPostId() {
	return postId;
}
public void setPostId(Integer postId) {
	this.postId = postId;
}
}
