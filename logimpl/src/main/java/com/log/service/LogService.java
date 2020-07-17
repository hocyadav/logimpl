package com.log.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.log.dao.BlockedLogDAO;
import com.log.dao.CriticalLogDAO;
import com.log.dao.InfoLogDAO;
import com.log.dao.NotificationDAO;
import com.log.dao.WarningLogDAO;
import com.log.entity.BlockerLog;
import com.log.entity.CriticalLog;
import com.log.entity.InfoLog;
import com.log.entity.Notification_;
import com.log.entity.WarningLog;
import com.log.util.Constants_;

@Service
public class LogService {

	private static LocalTime stime = null; 
	private static int fr = 0;
	
	@Autowired InfoLogDAO infoLogDAO;
	@Autowired WarningLogDAO warnLogDAO;
	@Autowired BlockedLogDAO blockLogDAO;
	@Autowired CriticalLogDAO criticalLogDAO;
	@Autowired NotificationDAO notificationDAO;
	
	public void saveLogLine(String str) {
		System.out.println("saveLogLine start : "+str);
		if(str == null || str.length() == 0) 
			return;
		
		String[] arr = str.split(" ");
		if(arr.length >= 3) {
			String date = arr[0];
			String time = arr[1];
			String type = arr[2];
			String msg = arr[3];
			
			if(type.equalsIgnoreCase(Constants_.INFO)) {
				infoLogDAO.save(new InfoLog(msg, date, time));
			} else if(type.equalsIgnoreCase(Constants_.WARNING)) {
				warnLogDAO.save(new WarningLog(msg, date, time));
			} else if(type.equalsIgnoreCase(Constants_.BLOCKER)) {
				blockLogDAO.save(new BlockerLog(msg, date, time));
			} else if(type.equalsIgnoreCase(Constants_.CRITICAL)) {
				criticalLogDAO.save(new CriticalLog(msg, date, time));
				notifyCritical(msg, type);
			} else {
				System.out.println(Constants_.INVALID);
				return;
			}
			System.out.println(Constants_.SAVED);
		}
		System.out.println("saveLogLine end : ");
	}
	
	public void notifyCritical(String notificationMsg, String type) {
		System.out.println("notifyCritical start :");
		
		List<CriticalLog> list = criticalLogDAO.findAll();
		System.out.println(list);
		Iterator<CriticalLog> it = list.iterator();
		while(it.hasNext()) {
			fr++;
			CriticalLog c = it.next();
			String t = c.getTime();
			LocalTime ctime = LocalTime.parse(t);
			if(stime == null) stime = ctime;//1st time
			
			int hour = ctime.getHour();
			int minute = ctime.getMinute();
			int second = ctime.getSecond();
			
			if(fr == 10) {
				List<Notification_> logTypeUser = notificationDAO.findAllByType("critical");
				logTypeUser.stream().forEach(u -> {
					u.setNotificationMsg(notificationMsg);
					notificationDAO.save(u);
				});
				fr = 0;//reset fre
			}
			
			if(fr >= 10 && stime.isBefore(ctime)) {
				int h = stime.getHour();
				int m = stime.getMinute();
				int s = stime.getSecond();
				if((hour == h) && (minute == m) && (second - s) >= 100) {
					//notify
					String logType = "";
					List<Notification_> logTypeUser = notificationDAO.findAllByType(logType);
					logTypeUser.stream().forEach(u -> {
						u.setNotificationMsg(notificationMsg);
						notificationDAO.save(u);
					});
				}
				stime = ctime;//updte start time
				fr = 0;//reset fre
			}
		}
		System.out.println("notifyCritical end :");
	}
	
}
