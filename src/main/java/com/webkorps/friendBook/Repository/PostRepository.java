package com.webkorps.friendBook.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webkorps.friendBook.Model.Post;

public interface PostRepository extends CrudRepository<Post,Integer>{

	public List<Post> findByUserName(String userName);

	public Post findByPostId(Integer postId);
	@Transactional
	@Modifying
@Query(value="update post set likes=:likeIncrease where post_id=:postId",nativeQuery = true)
	public void UpdateLikes(int postId,int likeIncrease);
	@Query(value = "SELECT DISTINCT p.* FROM post p JOIN request_entities r ON (p.user_name = r.sender OR p.user_name = r.reciever) WHERE r.status = 'added' AND (r.sender = :senderUsername OR r.reciever = :senderUsername)", nativeQuery = true)	 
	List<Post> findPostsBySenderOrReceiverAndStatus(@Param("senderUsername") String senderUsername);


}
