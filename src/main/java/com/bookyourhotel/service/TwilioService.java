package com.bookyourhotel.service;

import com.bookyourhotel.configuration.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }


    public String sendSms(String to, String message) {
       Message sms = Message
                       .creator(new PhoneNumber(to), new PhoneNumber(twilioConfig.getTwilioPhoneNumber()),message)
                       .create();
       return sms.getSid();
    }
}

