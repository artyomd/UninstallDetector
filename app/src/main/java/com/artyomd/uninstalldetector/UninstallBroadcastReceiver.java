package com.artyomd.uninstalldetector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by artyomd on 11/27/17.
 */

public class UninstallBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Intent.ACTION_PACKAGE_REMOVED.equals(intent.getAction())) {
			Intent intent1 = new Intent(context, NotifyUninstall.class);
			intent1.putExtra(NotifyUninstall.PACKAGE_NAME, intent.getDataString());
			intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent1);
		}
	}
}
