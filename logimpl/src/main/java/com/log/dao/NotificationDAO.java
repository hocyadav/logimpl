package com.log.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.log.entity.Notification_;

@Repository
public interface NotificationDAO extends JpaRepository<Notification_, Integer> {

	List<Notification_> findAllByType(String logType);
	
}
