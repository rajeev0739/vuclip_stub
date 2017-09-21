package com.vuclip.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;



public class MaxisHashAndCGUrlGenerator {
	public static Logger logger = Logger.getLogger(MaxisHashAndCGUrlGenerator.class.getName());

	public static String getMaxisNewVideoStoreCGUrl(String chargeMSISDN,String login,String pswd,String shortCode,String billServiceId,String billPrice,String contentType
			,String chargeType,String contentId,String transactionType,String serviceDescription,String applicationId,String chargeId) {

		serviceDescription =urlEncodeUTF8(serviceDescription) ;
		String QueryString = applicationId + shortCode + chargeId + billServiceId + billPrice + contentType + chargeType + serviceDescription + contentId + transactionType + chargeMSISDN;
		
		String saltKey = login + pswd;
		logger.debug(" saltKey:: "+ saltKey);
		logger.debug(" QueryString:: "+ QueryString);
		String saltedQueryString = saltKey + QueryString;
		logger.debug(" saltedQueryString:: "+ saltedQueryString);
		String encryptedValue = encryption(saltedQueryString);
		logger.debug("encryptedValue of saltedQueryString:: "+ encryptedValue);
		String hashValue = urlEncodeUTF8(encryptedValue);

		String url = "http://aoc.maxis.com.my/WAPAOCPUSH/Buy?"
				     +"ChargeMSISDN="+chargeMSISDN+"&ApplicationId="+applicationId+"&BillServiceId="+billServiceId+"&BillPrice="+billPrice
				     +"&ContentType="+contentType+"&ChargeType="+chargeType+"&ServiceDescription="+serviceDescription+"&ContentId="+contentId
				     +"&TransactionType="+transactionType+"&hashvalue="+hashValue+"&ShortCode="+shortCode+"&ChargeId="+chargeId;

		logger.debug("Maxis Cgurl:: "+ url);
		return url;
	}
	
	private static String encryption(String unhashedValue) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(unhashedValue.getBytes());
			byte[] byteData = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte element : byteData) {
				sb.append(Integer.toString((element & 0xFF) + 256, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.debug("NoSuchAlgorithmException"+e.getMessage(),e);
		}
		return "";
	}

	private static String urlEncodeUTF8(String text) {
		if (text == null) {
			return "";
		}
		try {
			byte[] rs = text.getBytes("UTF-8");
			return urlEncode(rs);
		} catch (UnsupportedEncodingException e) {
			logger.debug("UnsupportedEncodingException"+e.getMessage(),e);
		}
		return null;
	}

	private static String urlEncode(byte[] rs) {
		StringBuffer result = new StringBuffer(rs.length * 4);
		for (byte element : rs) {
			char c = (char)element;
			switch (c) {
			case '*':
			case '-':
			case '.':
			case '/':
			case '_':
				result.append(c);
				break;
			case ' ':
				result.append('+');
				break;
			default:
				if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9') {
					result.append(c);
				} else {
					result.append('%');
					result.append("0123456789ABCDEF".charAt((c & 0xF0) >> '\004'));
					result.append("0123456789ABCDEF".charAt(c & 0xF));
				}
				break;
			}
		}
		return result.toString();
	}




}
