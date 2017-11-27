package com.artyomd.uninstalldetector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by artyomd on 11/27/17.
 */

public class NotifyUninstall extends AppCompatActivity {

	public static String PACKAGE_NAME = "package";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String packageName = bundle.getString(PACKAGE_NAME);
			displayAlert(packageName);
		}

	}

	private void displayAlert(String packageName) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(packageName + " has been uninstalled").setNeutralButton("ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				NotifyUninstall.this.finish();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
}