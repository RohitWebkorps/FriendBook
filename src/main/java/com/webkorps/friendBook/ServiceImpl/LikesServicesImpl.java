package com.webkorps.friendBook.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webkorps.friendBook.Controller.UserController;
import com.webkorps.friendBook.Model.Likes;
import com.webkorps.friendBook.Model.Post;
import com.webkorps.friendBook.Repository.LikesRepository;
import com.webkorps.friendBook.Repository.PostRepository;
import com.webkorps.friendBook.Service.*;
@Service
public class LikesServicesImpl implements LikesService{
	@Autowired
	LikesRepository likeRepository;
	@Autowired
	PostRepository postRepository;
	Logger logger = LoggerFactory.getLogger(LikesServicesImpl.class.getName());

	public void AddLike(String userName, Integer postId) {
		logger.info("Inside :AddLike");
		Post p=postRepository.findByPostId(postId);
		Integer likeIncrease=p.getLikes();
		likeIncrease++;
		p.setLikes(likeIncrease);
		postRepository.UpdateLikes(postId,likeIncrease);
		Likes like=new Likes(userName,postId);
		likeRepository.save(like);
		logger.info("end :AddLike");
	}

public void removeLike(String userName, Integer postId) {
	logger.info("Inside :removeLike");
	Post p=postRepository.findByPostId(postId);
	Integer likeIncrease=p.getLikes();
	likeIncrease--;
	p.setLikes(likeIncrease);
	postRepository.UpdateLikes(postId,likeIncrease);
	likeRepository.deleteByLikedBy(userName);
	logger.info("end :removeLike");
}
public String isLiked(String userName,Integer postId)
{
	logger.info("insides:isLiked");
	String isLiked=likeRepository.existsByLikedByAndPostId(userName,postId);	
	if(isLiked!=null)
	return "Liked";
	return "Like";
}
}
