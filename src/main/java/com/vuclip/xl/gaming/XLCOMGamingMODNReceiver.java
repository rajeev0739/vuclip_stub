package com.vuclip.xl.gaming;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vuclip.util.GenericUtil;


@Controller
@RequestMapping(value = "/services/xlgaming")
public class XLCOMGamingMODNReceiver {
	public static Logger LOGGER = Logger.getLogger(XLCOMGamingMODNReceiver.class.getName());

	@RequestMapping(value = "/moReceiver", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String processMO(HttpServletRequest request,@RequestBody String jsonString) {
		LOGGER.debug(" MO Received >>>");
		LOGGER.debug(GenericUtil.getRequestParametersHeadersAndBody(request,jsonString));
		return HttpStatus.OK.name();
	}

	
	@RequestMapping(value = "/drReceiver", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String processDN( HttpServletRequest request,@RequestBody String jsonString) {
		    LOGGER.debug("DR Received >>>");
		    LOGGER.debug(GenericUtil.getRequestParametersHeadersAndBody(request,jsonString));
		    return HttpStatus.OK.name();
	}
	

	

}
