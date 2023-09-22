package com.webkorps.friendBook.ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.webkorps.friendBook.Controller.UserController;
import com.webkorps.friendBook.Exception.InvalidCredentialsException;
import com.webkorps.friendBook.Exception.UserNotFoundException;
import com.webkorps.friendBook.Model.Comments;
import com.webkorps.friendBook.Model.Likes;
import com.webkorps.friendBook.Model.Post;
import com.webkorps.friendBook.Model.RequestEntities;
import com.webkorps.friendBook.Model.User;
import com.webkorps.friendBook.Repository.CommentsRepository;
import com.webkorps.friendBook.Repository.LikesRepository;
import com.webkorps.friendBook.Repository.PostRepository;
import com.webkorps.friendBook.Repository.RequestRepository;
import com.webkorps.friendBook.Repository.UserRepository;
import com.webkorps.friendBook.Service.UserService;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RequestRepository requestRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	CommentsRepository commentsRepository;
	@Autowired
	LikesRepository likeRepository;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());

	public ResponseEntity<String> addUser(User user) {
		try {
			logger.info("Inside :addUser()");
			Random rand = new Random(); 
			String firstWord=(user.getUserName().substring(0, 1)).toUpperCase();
			String userName=firstWord+user.getUserName().substring(1)+""+ rand.nextInt(100,999);
			user.setUserName(userName);
			userRepository.save(user);
			logger.info("end :addUser()");

          return new ResponseEntity<>("success" ,HttpStatus.CREATED);
		}
		catch(Exception e)
	{
		e.printStackTrace();
	}
		logger.info("end :addUser()");

		return new ResponseEntity<>("Failure",HttpStatus.BAD_REQUEST);
		
		}
	
	
	public boolean profileSave(MultipartFile file, String userName) 
	{
		logger.info("inside :profileSave()");

		 String profilePhoto = "";
	try {
		if(!file.isEmpty())
		{
	      User user = userRepository.findByUserName(userName);
		
			 profilePhoto = file.getOriginalFilename().trim();
			
	            byte[] imageData = file.getBytes();
	            String path = "C:\\Users\\Dell\\Documents\\workspace-spring-tool-suite-4-4.19.1.RELEASE\\friendBook\\src\\main\\resources\\static\\img\\"
	                    + profilePhoto;
	            path.trim();
	            Files.write(Paths.get(path), imageData);
	            user.setProfilePic(path);
	         userRepository.save(user);
	 		logger.info("end :profileSave()");

			return true;
		}
	}
	catch(Exception e)
	{
		System.out.print(e);
	}
		logger.info("end :profileSave()");

	return false;
    }
	
	
	
public boolean isUserPresent(String userName, String password) 
{
		logger.info("inside :isUserPresent()");
    User user = userRepository.findByUserNameAndPassword(userName, password);
    if (user == null) {
		logger.info("end :isUserPresent()");
    	return false;
    }
	logger.info("end :isUserPresent()");

    	return true;   
}


public User updateDetails(String favSong, String favBook, String favPlace, String userName) {
	logger.info("inside :updateDetails()");

    User user = userRepository.findByUserName(userName);
    user.setFavSong(favSong);
    user.setFavBook(favBook);
    user.setFavPlace(favPlace);
    userRepository.save(user);
	logger.info("end :updateDetails()");
    return user;
}


public User getUser(String searchedPersonName) {
	logger.info("inside :getUser()");

	User u=userRepository.findByUserName(searchedPersonName);
	logger.info("end :getUser()");

	return u;
}

public List<User> getSimilarUser(String searchedPersonName) {
	logger.info("inside :getSimilarUser()");

	List<User> users=userRepository.findBySimilarUsers(searchedPersonName);

	if(users.isEmpty())
	{ 
		logger.error("inside :getSimilarUser()");
		throw new UserNotFoundException("No user with these name are found");
		
	}
	logger.info("end :getSimilarUser()");
	return users;
}



}
