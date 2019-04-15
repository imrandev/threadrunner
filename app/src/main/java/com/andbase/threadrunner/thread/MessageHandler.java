package com.andbase.threadrunner.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.andbase.threadrunner.constant.Constant;

import static com.andbase.threadrunner.constant.Constant.MSG_UPDATE;

public class MessageHandler extends Handler {

    private Handler.Callback onHandlerCallback;

    public MessageHandler(Looper looper, Callback onHandlerCallback) {
        super(looper);
        this.onHandlerCallback = onHandlerCallback;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case MSG_UPDATE: {
                Message message = new Message();
                message.what = Constant.MSG_UPDATE;
                sendMessageDelayed(message, 1000);
                onHandlerCallback.handleMessage(message);
            } break;
        }
    }
}
