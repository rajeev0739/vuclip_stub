package com.vuclip.baas.controller;


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

import com.vuclip.util.GenericUtil;

@Controller
public class CGController {
	public static Logger LOGGER = Logger.getLogger(CGController.class.getName());

	@RequestMapping(value = "/baas/CGController/consentParser/{id}",method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView  processCgCallback(HttpServletRequest request,HttpServletResponse response,@RequestBody String requestBody,Model model, @PathVariable("id") long id) {
		LOGGER.debug("Cg CallBack Received >>>");
		ModelAndView mv=null;
		try {
			mv=new ModelAndView("cgCallBack");
			model.addAttribute("message", "Welcome To Vuclip");
			model.addAttribute("uinfoData",GenericUtil.getQueryStringParameters(request,true));
			LOGGER.debug(GenericUtil.getRequestParametersHeadersAndBody(request,requestBody,true));
		}catch(Exception e){
			LOGGER.debug(" error in cg controller ");
		}				
		return mv;
	}

}
