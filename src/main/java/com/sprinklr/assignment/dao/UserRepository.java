package com.sprinklr.assignment.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprinklr.assignment.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
	
	//@Query("SELECT u FROM User u where u.email = :email") 
	public User findByEmail(@Param("email") String email);

}
