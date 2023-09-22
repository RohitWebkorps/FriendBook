package com.webkorps.friendBook.ServiceImpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webkorps.friendBook.Controller.UserController;
import com.webkorps.friendBook.Model.Post;
import com.webkorps.friendBook.Model.User;
import com.webkorps.friendBook.Repository.PostRepository;
import com.webkorps.friendBook.Repository.UserRepository;
import com.webkorps.friendBook.Service.PostService;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;
	
	Logger logger = LoggerFactory.getLogger(PostServiceImpl.class.getName());

public void createPost(MultipartFile postimg,User u) {
	logger.info("Inside :createPost");
	String userName=u.getUserName();
	 String PostingImgName = "";
	try {
		if(!postimg.isEmpty())
		{
		
	      User user = userRepository.findByUserName(userName);
	
	      PostingImgName = postimg.getOriginalFilename().trim();
	
	            byte[] imageData = postimg.getBytes();
	            String path = "C:\\Users\\Dell\\Documents\\workspace-spring-tool-suite-4-4.19.1.RELEASE\\friendBook\\src\\main\\resources\\static\\Post\\"
	                    + PostingImgName;
	            path.trim();
	        	Post post=new Post(userName,path);
	            Files.write(Paths.get(PostingImgName),imageData);
	           
	           postRepository.save(post);
	       	logger.info("end :createPost");

	      }
	}
	catch(Exception e)
	{
		System.out.print(e);
	}
	logger.info("end :createPost");
}

public List<Post> getPost(User user) {
	logger.info("inside :createPost()");
	List<Post> post= postRepository.findByUserName(user.getUserName());
	logger.info("end :createPost/");
	return post;
}
public List<Post> getFeedPost(User user)
{
	logger.info("inside :getFeedPost()/");
	List<Post> post= postRepository.findPostsBySenderOrReceiverAndStatus(user.getUserName());
	return post;
}
}
