package com.sprinklr.assignment.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprinklr.assignment.model.LoginData;

@Repository
public interface LoginDataRepository extends CrudRepository<LoginData,Long>{

	public List<LoginData> findByUserId(@Param("id") long userId, Pageable pageable);
}
