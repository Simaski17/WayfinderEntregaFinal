package com.wayfinder.rinno.wayfinderentregafinal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


import com.wayfinder.rinno.wayfinderentregafinal.activitys.MainScreenActivity;

import java.io.IOException;

/**
 * Created by tecnova on 04-05-2017.
 */

public class AutoStart extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i = new Intent(context, MainScreenActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            String str = "79";
            if (Build.VERSION.SDK_INT >= 14)
                str = "42";
            try {
                Runtime localRuntime = Runtime.getRuntime();
                String[] arrayOfString = new String[3];
                arrayOfString[0] = "su";
                arrayOfString[1] = "-c";
                arrayOfString[2] = ("service call activity " + str + " s16 com.android.systemui");
                Process localProcess = localRuntime.exec(arrayOfString);
                try {
                    localProcess.waitFor();
                } catch (InterruptedException localInterruptedException) {
                    localInterruptedException.printStackTrace();
                }
            } catch (IOException localIOException) {
                localIOException.printStackTrace();
            }
        }
    }
}
