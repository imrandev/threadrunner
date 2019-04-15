package com.andbase.threadrunner.task;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.andbase.threadrunner.thread.MessageRunner;
import com.andbase.threadrunner.thread.MessageHandler;
import com.andbase.threadrunner.ui.main.MainContract;

import static com.andbase.threadrunner.constant.Constant.MSG_UPDATE;

public class MessageAsyncTask extends AsyncTask<Void, Void, HandlerThread> {

    private MainContract.View mMainView;
    private int count;

    public MessageAsyncTask(MainContract.View mMainView) {
        this.mMainView = mMainView;
    }

    @Override
    protected HandlerThread doInBackground(Void... voids) {
        HandlerThread mMessageThread = new HandlerThread("HandlerThread");
        mMessageThread.start();
        showMessage("Thread has started to run");

        MessageHandler mMessageHandler = new MessageHandler(mMessageThread.getLooper(), onHandlerCallback);
        MessageRunner messageRunner = new MessageRunner(mMessageHandler);
        mMessageHandler.postDelayed(messageRunner, 1000);
        return mMessageThread;
    }

    private Handler.Callback onHandlerCallback
            = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MSG_UPDATE:
                    ++count;
                    mMainView.updateMessage(String.format("Thread is running (%s times)", count));
                    showMessage("Thread is running!");
                    break;
            }
            return true;
        }
    };

    private void showMessage(String message) {
        Log.e("ThreadRunner", message);
    }
}
