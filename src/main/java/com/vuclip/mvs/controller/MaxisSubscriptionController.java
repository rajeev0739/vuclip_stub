package com.vuclip.mvs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vuclip.util.MaxisHashAndCGUrlGenerator;


@Controller
public class MaxisSubscriptionController {
	public static Logger LOGGER = Logger.getLogger(MaxisSubscriptionController.class.getName());
	
/*	@Value("${login:1623}")
	static private String login;
	@Value("${pswd:1623}")
	static private String pswd;
	@Value("${shortCode:23280}")
	static private String shortCode;
	@Value("${billServiceId:50340}")
	static private String billServiceId;
	@Value("${billPrice:200}")
	static private String billPrice;
	@Value("${contentType:W}")
	static private String contentType;
	@Value("${chargeType:W}")
	static private String chargeType;
	@Value("${contentId:23280}")
	static private String contentId;
	@Value("${transactionType:NS}")
	static private String transactionType;
	@Value("${serviceDescription:Video Store}")
	static private String serviceDescription;
	@Value("${applicationId:45252}")
	static private String applicationId;*/
	
	@Value("${login}")
	 private String login;
	@Value("${pswd}")
	 private String pswd;
	@Value("${shortCode}")
	 private String shortCode;
	@Value("${billServiceId}")
	 private String billServiceId;
	@Value("${billPrice}")
	 private String billPrice;
	@Value("${contentType}")
	 private String contentType;
	@Value("${chargeType}")
	 private String chargeType;
	@Value("${contentId}")
	 private String contentId;
	@Value("${transactionType}")
	 private String transactionType;
	@Value("${serviceDescription}")
	 private String serviceDescription;
	@Value("${applicationId}")
	 private String applicationId;
	
	static private String chargeId = System.nanoTime()+"" ;
	@RequestMapping(value = "${maxis.subscription.controller.request.mapping.value}",method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView  process(HttpServletRequest request,HttpServletResponse response) {
		String chargeMSISDN=request.getParameter("msisdn");
	    ModelAndView mv=new ModelAndView("redirect:"+MaxisHashAndCGUrlGenerator.getMaxisNewVideoStoreCGUrl(chargeMSISDN,login,pswd,shortCode,billServiceId,billPrice,contentType,chargeType,contentId,transactionType,serviceDescription,applicationId,chargeId));
		return mv;
	}
}
