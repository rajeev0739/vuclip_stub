package com.vuclip.smart.combodia.controller;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vuclip.beans.InboundSMSMessageNotificationWrapper;
import com.vuclip.util.GenericUtil;


@Controller
public class SmartCombodiaNotificationReceiver {
	private static ObjectMapper jsonObjectMapper = new ObjectMapper();
	public static Logger LOGGER = Logger.getLogger(SmartCombodiaNotificationReceiver.class.getName());

	@RequestMapping(value = "/notifications/DeliveryInfoNotification",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String getNotificationDetails(HttpServletRequest request,@RequestBody InboundSMSMessageNotificationWrapper ismn) {

		LOGGER.debug("DeliveryInfoNotification Received >>>");
		printRequestBodyJson(ismn);
		LOGGER.debug(GenericUtil.getRequestParametersHeadersAndBody(request,ismn.toString()));

		return HttpStatus.OK.name();
	}
	
	public void printRequestBodyJson(InboundSMSMessageNotificationWrapper ismn) {
		
		LOGGER.debug("=================Starting to print  json Receieved ================\n");
		if(null!=ismn) {
			
			//LOGGER.debug(ismn.toString());
			LOGGER.debug(marshalPojoToJson(ismn));
		
		}else {
			LOGGER.debug(" Request body is empty ");
		}
		LOGGER.debug("=================completed to print  json Receieved ================\n");
		
	}

	public String marshalPojoToJson(InboundSMSMessageNotificationWrapper ismn) {
		
		StringWriter stringWriter = new StringWriter();
		try {
			jsonObjectMapper.writeValue(stringWriter, ismn);
		} catch (IOException ioe) {
			LOGGER.error("Exception occurred while marshalling Notification, {}"+ismn.toString());
		}
		return stringWriter.toString();
	}
}
