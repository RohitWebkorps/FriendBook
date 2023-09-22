package com.webkorps.friendBook.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.webkorps.friendBook.Model.User;

public interface UserService {
	 ResponseEntity<String> addUser(User user);
	 boolean profileSave(MultipartFile file, String userName) throws IOException;
	 boolean isUserPresent(String userName, String password);
	 User updateDetails(String favSong, String favBook, String favPlace, String userName);
	 User getUser(String searchedPersonName);
	 List<User> getSimilarUser(String searchedPersonName);
}
