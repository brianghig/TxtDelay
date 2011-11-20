package com.brianghig.android.txtdelay;

import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Main extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final EditText phoneText = (EditText)this.findViewById(R.id.phoneText);
        final EditText msgText = (EditText)this.findViewById(R.id.msgText);
        Button sendTextBtn = (Button)this.findViewById(R.id.sendTextBtn);
        sendTextBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				
				String receivingNumber = phoneText.getEditableText().toString();
				String msgToSend = msgText.getEditableText().toString();
				
				SmsManager managerSms = SmsManager.getDefault();
				managerSms.sendTextMessage(receivingNumber, null, msgToSend, null, null);
			}
        	
        });
        
        final DatePicker datePicker = (DatePicker)this.findViewById(R.id.datePicker1);
        final TimePicker timePicker = (TimePicker)this.findViewById(R.id.timePicker1);
        Button setAlarmBtn = (Button)this.findViewById(R.id.setAlarmBtn);
        setAlarmBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				int day = datePicker.getDayOfMonth();
				int month = datePicker.getMonth();
				int year = datePicker.getYear();
				int hour = timePicker.getCurrentHour();
				int minute = timePicker.getCurrentMinute();
				
				Log.d("TXTDEL", "Hour: " + hour + " and Minute: " + minute);
				
				Date selectedDate = new Date( (year-1900), month, day, hour, minute );
				Log.d("TXTDEL", "Setting alarm for: " + selectedDate.toGMTString());
				
				Intent intent = new Intent(Main.this, AlarmBroadcastReceiver.class);
				PendingIntent pendingIntent = PendingIntent.getBroadcast(
						Main.this.getApplicationContext(), 234324243, intent, 0);
				AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
				alarmManager.set(AlarmManager.RTC_WAKEUP, selectedDate.getTime(), pendingIntent);
				Toast.makeText(Main.this, "Alarm set for " + selectedDate.toGMTString(),
						Toast.LENGTH_LONG).show();
				
				
			}
		});
    }
    
}