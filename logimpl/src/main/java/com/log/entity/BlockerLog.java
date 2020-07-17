package com.log.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class BlockerLog{
	@Id @GeneratedValue
	int id;
	String msg;
	String date;
	String time;
	
	public BlockerLog(String msg, String date, String time) {
		super();
		this.msg = msg;
		this.date = date;
		this.time = time;
	}

}
