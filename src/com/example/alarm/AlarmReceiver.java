package com.example.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		
		Toast.makeText(arg0
				, Aa.num1+Aa.num2+"=?"+Aa.num3+Aa.num4, 3000).show();
		
		if(Aa.num1==Aa.num3&&Aa.num2==Aa.num4){
			final MediaPlayer player =  MediaPlayer.create(arg0,R.raw.bao);
			
			player.start();
			
			new Thread (){
			public void run(){
				try{
					Thread.sleep(30000);
				}catch(Exception e){}
				player.stop();
			}
			}.start();
			
			
			Vibrator vibrator = 
						(Vibrator)arg0.getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(3000);
				
			
		}
	}

}
