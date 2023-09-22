package com.webkorps.friendBook.DTO;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDTO {
@Id
private String userName;
private String fullName;
private String email;
private String password;
private String favSong;
private String favBook;
private String favPlace;
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
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public UserDTO(String fullName, String email, String password, String userName) {
	super();
	this.fullName = fullName;
	this.email = email;
	this.password = password;
	this.userName = userName;
}
public UserDTO() {
	
}
}
