package com.andbase.threadrunner.ui.main;

import android.os.HandlerThread;
import android.util.Log;

import com.andbase.threadrunner.task.MessageAsyncTask;

import java.util.concurrent.ExecutionException;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mMainView;
    private HandlerThread mHandlerThread;

    MainPresenter(MainContract.View mMainView) {
        this.mMainView = mMainView;
        this.mMainView.attachPresenter(this);
    }

    @Override
    public void onStartThread() {
        try {
            onStopThread();
            MessageAsyncTask messageAsyncTask =  new MessageAsyncTask(mMainView);
            mHandlerThread = messageAsyncTask.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStopThread() {
        if (mHandlerThread != null){
            if (mHandlerThread.isAlive()){
                mHandlerThread.quit();
                mMainView.updateMessage("Thread is stopped");
                Log.e("ThreadRunner", "Thread is stopped!");
            }
        }
    }

    @Override
    public void attachView(MainContract.View view) {
        this.mMainView = view;
    }
}
