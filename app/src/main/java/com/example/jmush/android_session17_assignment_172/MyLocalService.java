package com.example.jmush.android_session17_assignment_172;

import java.util.Calendar;
import java.util.Date;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyLocalService extends Service {
    private final IBinder binder = new LocalBinder();
    public class LocalBinder extends Binder {
        MyLocalService getService() {
            return MyLocalService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     *
     * @return current date and time
     */
    public Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }
}