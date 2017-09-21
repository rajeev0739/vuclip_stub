package com.vuclip.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class GenericUtil {
	public static Logger logger = Logger.getLogger(GenericUtil.class.getName());
	
	public final static StringBuilder getRequestParametersHeadersAndBody(HttpServletRequest request,String jsonString){
		StringBuilder responseData = new StringBuilder();
        try {
				Enumeration<?> e=null;
				if(null!=request) {
					e = request.getParameterNames();
				}
				
		        responseData.append("\n--------------------------Parameters----------------------------------\n");
		
				if(null!=e){
					while ( e.hasMoreElements()){
						String name = (String)e.nextElement();
						String[] values = request.getParameterValues(name);
						for (int i=0; i<values.length; i++){														
								responseData.append(name+":"+values[i] + "\n");
						}
					}
				}
				responseData.append("--------------------------Parameters----------------------------------\n");
				responseData.append("---------------------------Headers------------------------------------\n");
		
				if(null!=request)
					e = request.getHeaderNames();
		
				if(null!=e){
					while (e.hasMoreElements()){
						String name = (String)e.nextElement();
						@SuppressWarnings("rawtypes")
						Enumeration values = request.getHeaders(name);
						while (values.hasMoreElements()){
							String value = (String)values.nextElement();
							responseData.append(name+":"+value + "\n");
						}
					}
					responseData.append("---------------------------Headers------------------------------------\n");
				}
				
				if(null!=jsonString) {
					 responseData.append("--------------------------Body----------------------------------------\n");
					 responseData.append(jsonString);
					 responseData.append("--------------------------Body----------------------------------------\n");
				}
        }catch(Exception e) {
        	logger.error("Exception occured while printing Request param and headers" + e.getMessage(), e);
        }        
		return responseData;
	}
	
	public final static StringBuilder getRequestParametersHeadersAndBody(HttpServletRequest request,String jsonString,boolean isMVSCGCallback){
		StringBuilder responseData = new StringBuilder();
        try {
				Enumeration<?> e=null;
				if(null!=request) {
					e = request.getParameterNames();
				}
				
		        responseData.append("\n--------------------------Parameters----------------------------------\n");
		
				if(null!=e){
					while ( e.hasMoreElements()){
						String name = (String)e.nextElement();
						String[] values = request.getParameterValues(name);
						for (int i=0; i<values.length; i++){
							
							if(!"result".equalsIgnoreCase(name)) { // ony for maxis it required
								responseData.append(name+":"+values[i] + "\n");
							}
							
							
						}
					}
				}
				responseData.append("--------------------------Parameters----------------------------------\n");
				responseData.append("---------------------------Headers------------------------------------\n");
		
				if(null!=request)
					e = request.getHeaderNames();
		
				if(null!=e){
					while (e.hasMoreElements()){
						String name = (String)e.nextElement();
						@SuppressWarnings("rawtypes")
						Enumeration values = request.getHeaders(name);
						while (values.hasMoreElements()){
							String value = (String)values.nextElement();
							responseData.append(name+":"+value + "\n");
						}
					}
					responseData.append("---------------------------Headers------------------------------------\n");
				}
				
				if(null!=jsonString) {
					 responseData.append("--------------------------Body----------------------------------------\n");
					 responseData.append(jsonString);
					 responseData.append("--------------------------Body----------------------------------------\n");
				}
        }catch(Exception e) {
        	logger.error("Exception occured while printing Request param and headers" + e.getMessage(), e);
        }        
		return responseData;
	}
	
	
	public final static StringBuilder getQueryStringParameters(HttpServletRequest request){
		final String BR = "<br/>";
		StringBuilder responseData = new StringBuilder();
		Enumeration<?> e = request.getParameterNames();
		responseData.append("--------------------------CG Callback Parameters----------------------------------" + BR);
		while (e.hasMoreElements()){
			String name = (String)e.nextElement();
			String[] values = request.getParameterValues(name);
			for (int i=0; i<values.length; i++){				
					responseData.append(name+":"+values[i] + BR);
			}
		}

		return responseData;
	}
	
	
	public final static StringBuilder getQueryStringParameters(HttpServletRequest request,boolean b){
		final String BR = "<br/>";
		StringBuilder responseData = new StringBuilder();
		Enumeration<?> e = request.getParameterNames();
		responseData.append("--------------------------CG Callback Parameters----------------------------------" + BR);
		while (e.hasMoreElements()){
			String name = (String)e.nextElement();
			String[] values = request.getParameterValues(name);
			for (int i=0; i<values.length; i++){
				if(!"result".equalsIgnoreCase(name)) {
					responseData.append(name+":"+values[i] + BR);
				}
				
			}
		}

		return responseData;
	}
}
