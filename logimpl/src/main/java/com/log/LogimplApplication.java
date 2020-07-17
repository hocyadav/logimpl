package com.log;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.log.dao.NotificationDAO;
import com.log.dao.WarningLogDAO;
import com.log.entity.Notification_;
import com.log.entity.WarningLog;
import com.log.service.LogService;

@SpringBootApplication
public class LogimplApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(LogimplApplication.class, args);
	}


	@Autowired
	WarningLogDAO warnDAO;
	@Autowired
	NotificationDAO notiDAO;
	@Autowired
	LogService logService;

	@Override
	public void run(String... args) throws Exception {
		warningLog();
		notifictionData();
		
		List<String> logs = new ArrayList<>();
		logs.add("2019-01-07 14:52:33 Warning data");
		logs.add("2019-01-07 14:52:34 Critical data");
		logs.add("2019-01-07 14:52:35 Info data");
		logs.add("2019-01-07 14:52:36 Critical data");
		logs.add("2019-01-07 14:52:37 Critical data");
		logs.add("2019-01-07 14:52:38 Critical data");
		logs.add("2019-01-07 14:52:39 Critical data");
		logs.add("2019-01-07 14:52:40 Critical data");
		logs.add("2019-01-07 14:52:41 warning data");
		logs.add("2019-01-07 14:52:42 Critical data");
		logs.add("2019-01-07 14:52:43 warning data");
		logs.add("2019-01-07 14:52:44 Critical data");
		
		logs.stream().forEach(log -> {
			logService.saveLogLine(log);
		});
		//logService.notifyCritical("dummy", "critical");
		System.out.println(notiDAO.findAll());
		
	}

	private void notifictionData() {
		Notification_ n = new Notification_("warning", "");
		notiDAO.save(n);
		Notification_ n2 = new Notification_("critical", "");
		notiDAO.save(n2);
		
		System.out.println(notiDAO.findAll());
	}

	private void warningLog() {
		WarningLog w = new WarningLog("data 1", "2019-01-07", "14:52:33");
		warnDAO.save(w);
		WarningLog w2 = new WarningLog("data 2", "2019-01-07", "14:52:33");
		warnDAO.save(w2);
		WarningLog w3 = new WarningLog("data 3", "2019-01-07", "14:52:33");
		warnDAO.save(w3);
		WarningLog w4 = new WarningLog("data 4", "2019-01-07", "14:52:33");
		warnDAO.save(w4);
		
		System.out.println(warnDAO.findAll());
	}

}
