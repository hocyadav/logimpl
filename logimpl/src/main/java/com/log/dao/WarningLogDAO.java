package com.log.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.log.entity.WarningLog;

@Repository
public interface WarningLogDAO extends JpaRepository<WarningLog, Integer> {

}
