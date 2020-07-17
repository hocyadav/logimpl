package com.log.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class Notification_ {
	@Id @GeneratedValue
	int userId;
	String type;
	String notificationMsg;
	//mode
	
	public Notification_(String type, String notificationMsg) {
		super();
		this.type = type;
		this.notificationMsg = notificationMsg;
	}
}
