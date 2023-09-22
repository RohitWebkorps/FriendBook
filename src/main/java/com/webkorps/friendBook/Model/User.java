package com.webkorps.friendBook.Model;

import java.text.SimpleDateFormat;
import java.util.Date; 

import javax.persistence.Entity;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webkorps.friendBook.Controller.UserController;

@Entity
public class User {
@Id
private String userName;
private String fullName;
private String email;
private String password;
private String favSong;
private String favBook;
private String favPlace;
private String profilePic;
private String signUpTime;
private int noOfPost;

public int getNoOfPost() {
	
	return noOfPost;
}
public void setNoOfPost(int noOfPost) {
	this.noOfPost = noOfPost;
}
public void setSignUpTime(String signUpTime) {


	this.signUpTime = signUpTime;
}
public String getSignUpTime() {
	return signUpTime;
}
public void setSignUpTime() {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date();  
	this.signUpTime = formatter.format(date);
}
public String getUserName() {
	
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getFavSong() {
	return favSong;
}
public void setFavSong(String favSong) {
	this.favSong = favSong;
}
public String getFavBook() {
	return favBook;
}
public void setFavBook(String favBook) {
	this.favBook = favBook;
}
public String getFavPlace() {
	return favPlace;
}
public void setFavPlace(String favPlace) {
	this.favPlace = favPlace;
}
public String getProfilePic() {
	return profilePic;
}
public void setProfilePic(String profilePic) {
	this.profilePic = profilePic;
}
public User(String userName, String fullName, String email, String password, String favSong, String favBook,
		String favPlace, String profilePic) {
	super();
	this.userName = userName;
	this.fullName = fullName;
	this.email = email;
	this.password = password;
	this.favSong = favSong;
	this.favBook = favBook;
	this.favPlace = favPlace;
	this.profilePic = profilePic;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	Date date = new Date();  
	this.signUpTime = formatter.format(date);
	this.noOfPost=0;
}
public User(String fullName, String email, String password, String userName) {
	super();
	this.fullName = fullName;
	this.email = email;
	this.password = password;
	this.userName = userName;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date();  
	this.signUpTime = formatter.format(date);
	this.favSong = "Not added";
	this.favBook = "Not added";
	this.favPlace = "Not Added";
}
public User() {
	
}
}
