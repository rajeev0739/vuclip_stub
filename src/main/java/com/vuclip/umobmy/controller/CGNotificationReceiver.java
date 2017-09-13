package com.vuclip.umobmy.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vuclip.umobmy.bean.Notification;

@Controller
public class CGNotificationReceiver {

	public static Logger LOGGER = Logger.getLogger(CGNotificationReceiver.class.getName());

	@RequestMapping("/cgnotificationreceiver")
	public @ResponseBody String getReceiverParamValues(Notification notification, HttpServletRequest request) {

		LOGGER.debug("CG Notification Received >>>");

		LOGGER.debug(request.getQueryString());
		
		LOGGER.debug(notification.toString());

		return HttpStatus.OK.name();
	}

}
