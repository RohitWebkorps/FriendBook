package com.webkorps.friendBook.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webkorps.friendBook.Model.Comments;
import com.webkorps.friendBook.Model.User;

public interface CommentsRepository extends CrudRepository<Comments,Integer> {

	List<Comments> findByUserName(String searchedPersonName);

}
