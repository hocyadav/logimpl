package com.log.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.log.entity.BlockerLog;

@Repository
public interface BlockedLogDAO extends JpaRepository<BlockerLog, Integer> {

}
