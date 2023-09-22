package com.webkorps.friendBook.Service;

public interface LikesService {
	 void AddLike(String userName, Integer postId);
	 void removeLike(String userName, Integer postId);
	 String isLiked(String userName,Integer postId);
}
