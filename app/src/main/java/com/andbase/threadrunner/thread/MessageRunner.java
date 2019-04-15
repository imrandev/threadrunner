package com.andbase.threadrunner.thread;

import android.os.Message;

import com.andbase.threadrunner.constant.Constant;

public class MessageRunner implements Runnable {

    private MessageHandler messageHandler;

    public MessageRunner(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {
        Message message = new Message();
        message.what = Constant.MSG_UPDATE;
        messageHandler.handleMessage(message);
    }
}
