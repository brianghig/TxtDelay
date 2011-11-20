package com.brianghig.android.txtdelay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("TXTDEL", "Hooooly shit it worked!!");
		
		Toast.makeText(context, "Don't panik but your time is up!!!!.", Toast.LENGTH_LONG).show();
		
		// Vibrate the mobile phone
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(2000);
	}

}
