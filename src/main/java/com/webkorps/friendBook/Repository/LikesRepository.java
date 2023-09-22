package com.webkorps.friendBook.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webkorps.friendBook.Model.Likes;

public interface LikesRepository extends CrudRepository<Likes, Integer> {
	@Transactional
	@Modifying
@Query(value="delete from likes where liked_by=:userName",nativeQuery=true)
	void deleteByLikedBy(String userName);
@Query(value="select * from likes where liked_by=:userName and post_id=:postId",nativeQuery=true)
	boolean findByLikedBy(String userName, Integer postId);
@Query(value="select * from likes where liked_by=:userName and post_id=:postId",nativeQuery=true)
String existsByLikedByAndPostId(String userName, Integer postId);

	//void increaseLikes(String userName, String userName2);

}
