package com.tistory.yunsubaek.smsforwarder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class MyBroadcastReceiver extends BroadcastReceiver {
    String origNumber;
    String message;
    String senderNumber = "";//set senderNumber
    String receiverNumber = "";//set receiverNumber
    public void sendSMS() {
        if(origNumber.equals(senderNumber)) {
                SmsManager sendMsg = SmsManager.getDefault();
                sendMsg.sendTextMessage(receiverNumber, null, message, null, null);
        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                Object messages[] = (Object[]) bundle.get("pdus");
                SmsMessage smsMessage[] = new SmsMessage[messages.length];

                for (int i = 0; i < messages.length; i++) {
                    smsMessage[i] = SmsMessage.createFromPdu((byte[]) messages[i]);
                }
                origNumber = smsMessage[0].getOriginatingAddress();
                message = smsMessage[0].getMessageBody().toString();
                sendSMS();
            }
        }
    }
}
