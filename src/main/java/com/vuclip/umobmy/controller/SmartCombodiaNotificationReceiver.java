package com.vuclip.umobmy.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vuclip.smartcombodia.bean.InboundSMSMessageNotificationWrapper;


@Controller
public class SmartCombodiaNotificationReceiver {
	private static ObjectMapper jsonObjectMapper = new ObjectMapper();
	public static Logger LOGGER = Logger.getLogger(SmartCombodiaNotificationReceiver.class.getName());

	@RequestMapping(value = "/notifications/DeliveryInfoNotification",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String getNotificationDetails(HttpServletRequest request,@RequestBody InboundSMSMessageNotificationWrapper ismn) {

		LOGGER.debug("DeliveryInfoNotification Received >>>");



		printRequestBodyJson(ismn);
		printRequestHeaders(request);
		printRequestParams(request);

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

	public void printRequestHeaders(HttpServletRequest request) {

		try {
			LOGGER.debug("=================Starting to print  Request_Headers details=================\n");
			Enumeration<?> headerNames = request.getHeaderNames();
			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String key = (String) headerNames.nextElement();
					String value = request.getHeader(key);
					LOGGER.debug("header-name ::" + key + "\t header-value ::" + value);
				}
			} else {
				LOGGER.debug("Nothing is present in header ");
			}
		} catch (Exception e) {

			LOGGER.debug("Exception occured while printing Request_Headers" + e.getMessage(), e);
		}

		LOGGER.debug("=================printing  Request_Headers completed=================\n");

	}

	public void printRequestParams(HttpServletRequest request) {

		try {
			LOGGER.debug("=================Starting to print  Request_Parameters=================\n");
			Enumeration<?> requestParameters = request.getParameterNames();
			if (requestParameters != null) {
				while (requestParameters.hasMoreElements()) {
					String paramName = (String) requestParameters.nextElement();
					LOGGER.debug("request-param name is ::" + paramName + "\t request-param value is ::"
							+ request.getParameter(paramName));

				}
			} else {
				LOGGER.debug("Nothing is present in requestParameters ");
			}

		} catch (Exception e) {

			LOGGER.debug("Exception occured while printing Request_Parameters" + e.getMessage(), e);
		}
		LOGGER.debug("=================printing  Request_Parameters completed=================\n");
	}

	public void printRequestBody(String jsonString) {
		try {
			LOGGER.debug("=================Starting to print  Request_Body=================\n");

			LOGGER.debug("Data Received from  in request body :: \n " + jsonString);
		} catch (Exception e) {

			LOGGER.debug("Exception occured while printing Request_Body" + e.getMessage(), e);
		}

		LOGGER.debug("=================printing  Request_Body completed=================\n");
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
