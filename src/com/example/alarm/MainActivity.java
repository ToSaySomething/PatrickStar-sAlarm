package com.example.alarm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

public static String TAG;

//	DateFormat date = DateFormat.getDateTimeInstance();
//	Button btnOpen;
//	Button btnOff;
//	Calendar calendar;
//	TextView textView;
//	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		Toast.makeText(this, "I am Patrick Star and happy to service you", 8000).show();
		
//
//		textView =(TextView)this.findViewById(R.id.textView1);
		
		final Button btnOpen = (Button)this.findViewById(R.id.timeBtn);
		final Button btnOff = (Button)this.findViewById(R.id.buttonOFF);
//		Button btnOpen = (Button)this.findViewById(R.id.timeBtn);
//		timeButt =(TimePicker)this.findViewById(R.id.timePicker1);
//		btnOpen.setOnClickListener(new TimeButtonClickListener(timeButt,true));
		final Calendar calendar = Calendar.getInstance();
		
		btnOpen.setOnClickListener(new View.OnClickListener() {
			
//		private String TAG;

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Log.d(TAG, "click the timeButton to set the time ");
				
				
				
				calendar.setTimeInMillis(System.currentTimeMillis());
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				
				final Aa x=new Aa();
				x.num3=format(calendar.get(Calendar.HOUR_OF_DAY));
			    x.num4=format(calendar.get(Calendar.MINUTE));
			    
			    Toast.makeText(MainActivity.this, x.num3+"="+ x.num4, 3000).show();
				new TimePickerDialog(MainActivity.this
						, new TimePickerDialog.OnTimeSetListener() {
					
					private String TAG;

					@Override
					public void onTimeSet(TimePicker view, int a, int b) {
						// TODO Auto-generated method stub
						btnOpen.setText(format(a)+":"+format(b));
						
						//更新时间
						calendar.setTimeInMillis(System.currentTimeMillis());
						//设置时间与本地时间相同
			            calendar.set(Calendar.HOUR_OF_DAY, a);  
			            calendar.set(Calendar.MINUTE, b);  
			            calendar.set(Calendar.SECOND, 0);  
			            calendar.set(Calendar.MILLISECOND, 0);  
			            x.num1=format(a);
			            x.num2=format(b);
			            
			            Intent intent = 
			            		new Intent(MainActivity.this,AlarmReceiver.class);
			            
			            
			            PendingIntent pendingIntent = 
			            		PendingIntent.getBroadcast(MainActivity.this,0, intent, 0);
			            
			            AlarmManager alarmManager =
			            		(AlarmManager)getSystemService(ALARM_SERVICE);
			            //明确的周期
			            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP
			            		, System.currentTimeMillis()+(10*1000)
			            		, 10*1000, pendingIntent);
			            
			            String time = "Set the time："+format(a)+":"+format(b);
			            	            
			            Toast.makeText(MainActivity.this, time, 3000).show();
//			            Log.d(MainActivity.TAG, "set the time to"+time);
			           
			            startService(intent);
			            
			            
					}

				

					
				},hour,minute,true).show();
				
			}
		});
		
		
		btnOff.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				btnOpen.setText("   Open me,please！");
				
				Intent intent = new Intent (MainActivity.this,AlarmReceiver.class);
				
				PendingIntent pendingIntent = PendingIntent
						.getBroadcast(MainActivity.this, 0, intent, 0);
				AlarmManager alarmManager = 
						(AlarmManager)getSystemService(ALARM_SERVICE);
				alarmManager.cancel(pendingIntent);
//				textView.setText("The alarm is canceled!");
				
				Log.d(MainActivity.TAG, "the time is up,start the alarm...");  
				Toast.makeText(MainActivity.this
						, "The alarm is canceled!"
						, Toast.LENGTH_SHORT).show();
			}
		});
	}
	public String format(int time) {
			// TODO Auto-generated method stub
			String str =""+time;
			if(str.length()==1){
				str ="0"+str;
			}
			return str;
		}			
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
