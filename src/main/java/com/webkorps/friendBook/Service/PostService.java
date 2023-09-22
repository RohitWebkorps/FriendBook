package com.webkorps.friendBook.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.webkorps.friendBook.Model.Post;
import com.webkorps.friendBook.Model.User;

public interface PostService {
	List<Post> getPost(User user);
	void createPost(MultipartFile postimg,User u);
	List<Post> getFeedPost(User u);

}
