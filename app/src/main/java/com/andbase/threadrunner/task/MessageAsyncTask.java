package com.andbase.threadrunner.task;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.andbase.threadrunner.thread.MessageRunner;
import com.andbase.threadrunner.thread.MessageThread;
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
        HandlerThread mHandlerThread = new HandlerThread("HandlerThread");
        mHandlerThread.start();
        showMessage("Thread has started to run");

        MessageThread mMessageThread = new MessageThread(mHandlerThread.getLooper(), onHandlerCallback);
        MessageRunner messageRunner = new MessageRunner(mMessageThread);
        mMessageThread.postDelayed(messageRunner, 1000);
        return mHandlerThread;
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
