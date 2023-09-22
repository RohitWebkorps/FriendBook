package com.webkorps.friendBook.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webkorps.friendBook.Controller.UserController;
import com.webkorps.friendBook.Model.RequestEntities;
import com.webkorps.friendBook.Model.User;
import com.webkorps.friendBook.Repository.RequestRepository;
import com.webkorps.friendBook.Service.RequestService;

@Service
public class RequestsServiceImpl implements RequestService{
	@Autowired
	RequestRepository requestRepository;
	Logger logger = LoggerFactory.getLogger(RequestsServiceImpl.class.getName());

	public void sendRequest(String reciever, String sender) {
		logger.info("Inside :sendRequest()");
		RequestEntities requestEntities=new RequestEntities(sender,reciever,"sended");	
           requestRepository.save(requestEntities);
   		logger.info("end :sendRequest()/");

	}

	public void deleteRequest(String reciever, String sender) {
		logger.info("Inside :deleteRequest()");
           requestRepository.deleteBySenderAndReciever(sender,reciever);
           logger.info("end :sendRequest()");
		
	}

	public int checkNotification(User u) {
		logger.info("Inside :checkNotification()/");
		int count= requestRepository.findByReciever(u.getUserName());
		return count;
	}

	public void addToFollower(String follower,String following) {
		logger.info("Inside :addToFollower()/");
	requestRepository.addStatusBySenderAndReciever(follower,following,"Added");
	}

	public void removeRequest(String sender, String reciever) {
		logger.info("Inside :removeRequest()/");
		requestRepository.deleteBySenderAndReciever(sender,reciever);		
	}

	public String isFriend(String userName, String searchedpersonName) {
		logger.info("Inside :isFriend()");

		Integer dataPresent=requestRepository.countByUserNameAndsearchedpersonName(userName,searchedpersonName);
	
		if(dataPresent>0)
		{
			Integer friends=requestRepository.countByUserNameAndsearchedpersonNameAndStatus(userName,searchedpersonName,"added");
			logger.info("end :isFriend()");

			if(friends>0)
            return "added";
			return "requested";
		}
		logger.info("end :isFriend()");

		return "notFriend";
	}
	
	

public boolean checkStatus(User you, User searchedPerson) {
	logger.info("inside :checkStatus()");

	String sender=you.getUserName();
	String reciever=searchedPerson.getUserName();
	boolean requested=requestRepository.existsBySenderAndRecieverAndStatus(sender,reciever,"sended");
	logger.info("end :checkStatus()");
	return requested;
}


public List<RequestEntities> TotalRequest(User you) {
	logger.info("inside :TotalRequest()/");

	List <RequestEntities> requestList=requestRepository.findByRecievers(you.getUserName());
	return requestList;
	}

public Integer countFollowing(User u) {
	logger.info("inside :countFollowing()/");

	String senderName=u.getUserName();
	return requestRepository.countBySender(senderName);
}


public Integer countFollowers(User u) {
	logger.info("inside :countFollowers()/");

	String recieverName=u.getUserName();
	return requestRepository.countByReciever(recieverName) ;
}


}
