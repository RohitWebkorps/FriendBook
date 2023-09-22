package com.webkorps.friendBook.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webkorps.friendBook.Controller.UserController;

@Entity
public class RequestEntities {
	
@Id
private int requestId;	
private String sender;
private String reciever;
private String status;


public String getStatus() {

	return status;
}
public void setStatus(String status) {
	
	this.status = status;
}
public RequestEntities(int requestId, String sender, String reciever, String status) {
	super();

	this.requestId = requestId;
	this.sender = sender;
	this.reciever = reciever;
	this.status = status;
}
public String getSender() {
	
	return sender;
}
public void setSender(String sender) {
	
	this.sender = sender;
}
public String getReciever() {

	return reciever;
}
public void setReciever(String reciever) {
	
	this.reciever = reciever;
}
public RequestEntities(String sender, String reciever) {

	this.sender = sender;
	this.reciever = reciever;
}
public RequestEntities() {

}
public RequestEntities(String sender, String reciever, String status) {
	
	this.sender = sender;
	this.reciever = reciever;
	this.status=status;
}

}
