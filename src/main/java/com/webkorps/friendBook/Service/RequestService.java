package com.webkorps.friendBook.Service;

import java.util.List;

import com.webkorps.friendBook.Model.RequestEntities;
import com.webkorps.friendBook.Model.User;

public interface RequestService {
	 void sendRequest(String reciever, String sender);
	 void deleteRequest(String reciever, String sender);
	 void addToFollower(String follower,String following);
	 void removeRequest(String sender, String reciever);
	 String isFriend(String userName, String searchedpersonName);
	 int checkNotification(User u);
	 boolean checkStatus(User you, User searchedPerson);
	  List<RequestEntities> TotalRequest(User you);
	  Integer countFollowing(User u);
	  Integer countFollowers(User u);
}
