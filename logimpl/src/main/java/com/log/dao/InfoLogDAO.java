package com.log.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.log.entity.InfoLog;

@Repository
public interface InfoLogDAO extends JpaRepository<InfoLog, Integer>{

}
