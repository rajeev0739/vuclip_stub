package com.vuclip.uiaas.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vuclip.api.service.util.XinWebUtil;

@Controller
public class UserInfoController {

	private static final String BR = "<br/>";
	public static Logger LOGGER = Logger.getLogger(UserInfoController.class.getName());

	@RequestMapping(value="/uinfo", method = RequestMethod.POST)
	public ModelAndView processUinfoUser(HttpServletRequest request,Model model) throws ServletException, IOException {
		return processUserInfoPage(request,model);
	}


	@RequestMapping(value = "/uinfo", method = RequestMethod.GET)
	public  ModelAndView processUserInfoPage(HttpServletRequest request,Model model) throws ServletException, IOException {

		StringBuilder responseData = getUInfoHtml(request);
		ModelAndView mv=new ModelAndView("uinfo");
		LOGGER.debug("uinfoData :"+responseData.toString());
		model.addAttribute("uinfoData",responseData.toString());
		return mv;
	}


	private final static StringBuilder getUInfoHtml(HttpServletRequest request){
		StringBuilder responseData = new StringBuilder();		
		responseData.append(getCoreUInfoData(request));
		return responseData;
	}

	private final static StringBuilder getCoreUInfoData(HttpServletRequest request){
		StringBuilder responseData = new StringBuilder();
		String[] ipua =	XinWebUtil.getIpUA(request);
		String ip = ipua[0];
		@SuppressWarnings("unused")
		String ua = ipua[1];
		@SuppressWarnings("rawtypes")
		Enumeration e = request.getParameterNames();

		responseData.append("=====================================================================" + BR);
		responseData.append(new Date(System.currentTimeMillis()) + BR);
		responseData.append(request.getRequestURL() + BR);
		responseData.append(ip + "  &nbsp;&nbsp;&nbsp;"+ BR);
		responseData.append("--------------------------Parameter----------------------------------" + BR);
		while (e.hasMoreElements()){
			String name = (String)e.nextElement();
			String[] values = request.getParameterValues(name);
			for (int i=0; i<values.length; i++){
				responseData.append(name+": "+values[i] + BR);
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
