package com.example.jmush.android_session17_assignment_172;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends Activity {


    MyLocalService localService;
    private boolean isBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    on Activity started, connect the service
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyLocalService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

//    //    on Activity stop, stop the service
    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }

    /**
     *
     * On click of show button, getting current date from bound service,
     * and settinf the date to textview
     */
    public void dispalyDate(View v) {
        if (isBound) {
            Date date = localService.getCurrentDate();
            TextView textView= (TextView) findViewById(R.id.textView);
            textView.setText(date.toString());
        }
    }

    /**
     * ServiceConnection to get the sevice if connected
     */
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            MyLocalService.LocalBinder binder = (MyLocalService.LocalBinder) service;
            localService = binder.getService();
            isBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };
}
