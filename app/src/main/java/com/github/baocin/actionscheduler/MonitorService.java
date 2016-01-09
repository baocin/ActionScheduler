package com.github.baocin.actionscheduler;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by aoi on 1/8/16.
 */
public class MonitorService extends Service{
    public void onCreate(){
        super.onCreate();
    }

    public void onDestroy(){

    }

    public int onStartCommand(Intent intent, int flags, int startId){
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
