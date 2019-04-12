package com.andbase.threadrunner.ui.main;

import com.andbase.threadrunner.ui.base.BasePresenter;
import com.andbase.threadrunner.ui.base.BaseView;

public class MainContract {
    public interface View extends BaseView<Presenter> {
        void updateMessage(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void onStartThread();
        void onStopThread();
    }
}
