package com.log.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.log.entity.CriticalLog;

@Repository
public interface CriticalLogDAO extends JpaRepository<CriticalLog, Integer>{

}
