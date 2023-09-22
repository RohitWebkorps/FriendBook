package com.webkorps.friendBook.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webkorps.friendBook.Model.User;


public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByUserNameAndPassword(String userName,String password);
	User findByUserName(String userName);
	@Query(value = "select * from user u where u.user_name like %:searchedPersonName% ", nativeQuery = true)
	List<User> findBySimilarUsers(String searchedPersonName);
	
	
}
