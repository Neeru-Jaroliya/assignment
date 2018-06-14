package com.sprinklr.assignment.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sprinklr.assignment.model.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token,Long>{
	
	@Transactional
    Long deleteByData(String data);
	
	 @Query("SELECT t FROM Token t where t.data = :data")
	public Token findOne(@Param("data") String data); 
	 
	public List<Token> findByCreatedAtLessThanEqual(@Param("created_at") Date createdAt);
	
	@Transactional
	public List<Token> deleteByCreatedAtLessThanEqual(@Param("created_at") Date createdAt);


}
