package com.vuclip.umobmy.controller;


import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import com.vuclip.api.service.util.XinWebUtil;



@Controller
public class CGController {
	public static Logger LOGGER = Logger.getLogger(CGController.class.getName());
	
	@RequestMapping(value = "/baas/CGController/consentParser/{id}",method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView  processCgCallback(HttpServletRequest request,HttpServletResponse response,@RequestBody String requestBody,Model model, @PathVariable("id") long id) {

		LOGGER.debug("Cg CallBack Received >>>");
		ModelAndView mv=new ModelAndView("CgResponse");
		model.addAttribute("message", "Welcome To Vuclip");
		try {
		String queryString = request.getQueryString();
		
		if(null!=queryString && queryString.length()>0) {
			queryString=getCarrierQueryString(queryString);
		}
		
				
		//model.addAttribute("queryString", queryString);
		model.addAttribute("uinfoData",getUInfoHtml(request));
		printRequestBodyJson(requestBody);
		printRequestParams(request);
		printRequestHeaders(request);
		}catch(Exception e){
			LOGGER.debug(" error in cg controller ");
		}
		
		
		return mv;
	}
	
/*	public static void main(String[] args) {
		String queryString="p1=1&p2=2&p3=3&result=Error";
		queryString=getCarrierQueryString(queryString);
		System.out.println(queryString);
	}*/
	
	//private static String getCarrierQueryString(String queryString) {
	
	private  String getCarrierQueryString(String queryString) {
		String successParam="&result=Success";
		String errorParam="&result=Error";
		CharSequence successSeq="&result=Success";
		CharSequence errorSeq="&result=Error";
		int successStartIndex=0,successEndIndex=0,errorStartIndex=0,errorEndIndex=0;
		
		if(queryString.contains(successSeq)) {
			successStartIndex=queryString.indexOf(successParam);
			successEndIndex=successStartIndex+successParam.length();
			
			queryString=queryString.substring(0, successStartIndex);
		}
		
		if(queryString.contains(errorSeq)) {
			errorStartIndex=queryString.indexOf(errorParam);
			errorEndIndex=errorStartIndex+errorParam.length();
			queryString=queryString.substring(0, errorStartIndex);
		}
		
		return queryString;
	}
	
	public void printRequestBodyJson(String  reqBody) {
		
		LOGGER.debug("=================Starting to print  Request Body Receieved ================\n");
		if(null!=reqBody) {
			
			LOGGER.debug(reqBody);
			
		
		}else {
			LOGGER.debug(" Request body is empty ");
		}
		LOGGER.debug("=================completed to print  Request Body Receieved ================\n");
		
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
					
					if(!"result".equalsIgnoreCase(paramName)) {
						LOGGER.debug("request-param name is ::" + paramName + "\t request-param value is ::"
							+ request.getParameter(paramName));
					}
				}
			} else {
				LOGGER.debug("Nothing is present in requestParameters ");
			}

		} catch (Exception e) {

			LOGGER.debug("Exception occured while printing Request_Parameters" + e.getMessage(), e);
		}
		LOGGER.debug("=================printing  Request_Parameters completed=================\n");
	}
	
	
	private final static StringBuilder getUInfoHtml(HttpServletRequest request){
		StringBuilder responseData = new StringBuilder();		
		responseData.append(getCoreUInfoData(request));
		return responseData;
	}

	private final static StringBuilder getCoreUInfoData(HttpServletRequest request){
		final String BR = "<br/>";
		StringBuilder responseData = new StringBuilder();
		String[] ipua =	XinWebUtil.getIpUA(request);

		@SuppressWarnings("unused")
		String ua = ipua[1];
		@SuppressWarnings("rawtypes")
		Enumeration e = request.getParameterNames();

		
		responseData.append("--------------------------Parameter----------------------------------" + BR);
		while (e.hasMoreElements()){
			String name = (String)e.nextElement();
			String[] values = request.getParameterValues(name);
			for (int i=0; i<values.length; i++){
				
				if(!"result".equalsIgnoreCase(name)) {
					responseData.append(name+": "+values[i] + BR);
				}
				
			}
		}
		
		responseData.append("---------------------------Header------------------------------------" + BR);
		e = request.getHeaderNames();
		while (e.hasMoreElements()){
			String name = (String)e.nextElement();
			@SuppressWarnings("rawtypes")
			Enumeration values = request.getHeaders(name);
			while (values.hasMoreElements()){
				String value = (String)values.nextElement();
				responseData.append(name+": "+value + BR);
			}
		}
		return responseData;
	}

}
