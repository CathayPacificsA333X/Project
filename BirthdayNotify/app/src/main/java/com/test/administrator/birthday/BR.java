package com.test.administrator.birthday;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
//实现开机启动，获取系统的开机广播
public class BR extends BroadcastReceiver {
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        if (intent.getAction().equals(ACTION)) {
            Intent i1 = new Intent(context, MainActivity.class);
            i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i1);
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}
