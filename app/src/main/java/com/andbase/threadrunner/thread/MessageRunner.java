package com.andbase.threadrunner.thread;

import android.os.Message;

import com.andbase.threadrunner.constant.Constant;

public class MessageRunner implements Runnable {

    private MessageThread messageThread;

    public MessageRunner(MessageThread messageThread) {
        this.messageThread = messageThread;
    }

    @Override
    public void run() {
        Message message = new Message();
        message.what = Constant.MSG_UPDATE;
        messageThread.handleMessage(message);
    }
}
