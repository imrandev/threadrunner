package com.andbase.threadrunner.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.andbase.threadrunner.R;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter mMainPresenter;
    private TextView mUpdateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter = new MainPresenter(this);
        mUpdateView = findViewById(R.id.update_view);
    }

    public void onStop(View view) {
        mMainPresenter.onStopThread();
    }

    public void onStart(View view) {
        mMainPresenter.onStartThread();
    }

    @Override
    public void updateMessage(String message) {
        mUpdateView.setText(message);
    }

    @Override
    public void attachPresenter(MainContract.Presenter presenter) {
        this.mMainPresenter = presenter;
    }
}
