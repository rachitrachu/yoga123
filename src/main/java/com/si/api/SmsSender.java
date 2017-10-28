package com.si.api;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class SmsSender {
	
	public void sendSmsNow(String customer, String message){
		
		String APIkey = "Abe6c8f38c23df7cc1b486c8c1fb7f4c6";
		String SenderId = "NXTLFE";
		
		SendSMSMaster sendSms = new SendSMSMaster();
		//send_sms sendSms = new send_sms();
		//sendSms.setparams(URL,method,APIkey,SenderId);//old version
		sendSms.setparams(APIkey,SenderId);
		
		try {
			sendSms.send_sms(customer, message);
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
