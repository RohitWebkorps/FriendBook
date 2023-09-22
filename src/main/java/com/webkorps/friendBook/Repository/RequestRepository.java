package com.webkorps.friendBook.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webkorps.friendBook.Model.RequestEntities;
import com.webkorps.friendBook.Model.User;


public interface RequestRepository extends CrudRepository<RequestEntities, Integer>{
	
	boolean existsBySenderAndRecieverAndStatus(String sender, String reciever, String status);

	@Transactional
    @Modifying  
	@Query(value = "DELETE FROM request_entities WHERE sender = :sender AND reciever = :reciever", nativeQuery = true)
    void deleteBySenderAndReciever(@Param("sender") String sender,@Param("reciever") String reciever);
	@Query(value = "SELECT COUNT(reciever) FROM request_entities WHERE reciever = :userName and status='sended'",nativeQuery = true)
	int findByReciever(String userName);
	@Query(value = "SELECT * FROM request_entities WHERE reciever =:userName and status='sended'",nativeQuery = true)
	List<RequestEntities> findByRecievers(String userName);
    @Modifying
    @Transactional
    @Query(value = "UPDATE request_entities SET status = :status WHERE sender = :sender AND reciever = :reciever", nativeQuery = true)
    void addStatusBySenderAndReciever(@Param("sender") String sender, @Param("reciever") String reciever, @Param("status") String status);
    @Query(value = "SELECT COUNT(sender) FROM request_entities WHERE status='Added' and sender=:senderName",nativeQuery = true)
	int countBySender(String senderName);
    @Query(value = "SELECT COUNT(reciever) FROM request_entities WHERE status='Added' and reciever=:recieverName",nativeQuery = true)
	int countByReciever(String recieverName);
    @Query(value="select COUNT(sender) from request_entities where sender=:userName and reciever=:searchedpersonName",nativeQuery=true)
	Integer countByUserNameAndsearchedpersonName(String userName, String searchedpersonName);
    @Query(value="select COUNT(sender) from request_entities where sender=:userName and reciever=:searchedpersonName and status=:status",nativeQuery=true)
	Integer countByUserNameAndsearchedpersonNameAndStatus(String userName, String searchedpersonName, String status);
}

