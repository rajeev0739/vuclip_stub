package com.vuclip.beans;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ 
	"inboundSMSMessageNotification"
	})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class InboundSMSMessageNotificationWrapper implements Serializable{
	
//@JsonProperty("inboundSMSMessageNotification")
	
@JsonProperty("inboundSMSMessageNotification")	
private  InboundSMSMessageNotification inboundSMSMessageNotificationdata;


@Override
public String toString() {
	return "InboundSMSMessageNotificationWrapper [inboundSMSMessageNotificationdata="
			+ inboundSMSMessageNotificationdata + "]";
}

public static class InboundSMSMessageNotification
{
	@JsonProperty("callbackData")
	private String callbackData;
	@JsonProperty("inboundSMSMessage")
	private InboundSMSMessage inboundSMSMessage;
	
	@Override
	public String toString() {
		return "InboundSMSMessageNotification [callbackData=" + callbackData + ", inboundSMSMessage="
				+ inboundSMSMessage + "]";
	}
	
	
	
}

public static class InboundSMSMessage implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
@JsonProperty("destinationAddress")	
private String destinationAddress;
@JsonProperty("messageId")
private String messageId;
@JsonProperty("message")
private String message;
@JsonProperty("senderAddress")
private String senderAddress;
@JsonProperty("dateTime")
private String dateTime;


public String getDateTime() {
	return dateTime;
}
public void setDateTime(String dateTime) {
	this.dateTime = dateTime;
}
public String getDestinationAddress() {
	return destinationAddress;
}
public void setDestinationAddress(String destinationAddress) {
	this.destinationAddress = destinationAddress;
}
public String getMessageId() {
	return messageId;
}
public void setMessageId(String messageId) {
	this.messageId = messageId;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getSenderAddress() {
	return senderAddress;
}
public void setSenderAddress(String senderAddress) {
	this.senderAddress = senderAddress;
}

@Override
public String toString() {
	return "InboundSMSMessage [destinationAddress=" + destinationAddress + ", messageId=" + messageId + ", message="
			+ message + ", senderAddress=" + senderAddress + ", dateTime=" + dateTime + "]";
}

}



}
