package com.vuclip.umobmy.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vuclip.umobmy.bean.Notification;

@Controller
public class CgnotificationListener {

	public static Logger LOGGER = Logger.getLogger(CgnotificationListener.class.getName());

	@RequestMapping(value = "/cgnotificationListener", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String getListenerParamValues(Notification notification, HttpServletRequest request,
			@RequestBody String jsonString) {

		LOGGER.debug("CG Notification Listened >>>");

		// LOGGER.debug(request.getQueryString());

		LOGGER.debug(notification.toString());

		printRequestBody(jsonString);
		printRequestHeaders(request);
		printRequestParams(request);

		return HttpStatus.OK.name();
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

}
