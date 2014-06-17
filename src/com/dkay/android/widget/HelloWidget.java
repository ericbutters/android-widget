package com.dkay.android.widget;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.widget.RemoteViews;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;


public class HelloWidget extends AppWidgetProvider {
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new MyTime(context, appWidgetManager), 1, 1000);
		
	}
	
	private class MyTime extends TimerTask {
		RemoteViews remoteViews;
		AppWidgetManager appWidgetManager;
		ComponentName thisWidget;
		//DateFormat format = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());
		
	public MyTime(Context context, AppWidgetManager appWidgetManager) {
		this.appWidgetManager = appWidgetManager;
		remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);
		thisWidget = new ComponentName(context, HelloWidget.class);
	}
	
	@Override
	public void run() {
		Bitmap bitmap = Bitmap.createBitmap(200, 200, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
        int x = 200;
        int y = 200;
        int radius;
        radius = 100;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.TRANSPARENT);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#FF0000"));
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        canvas.drawCircle(x / 2, y / 2, radius, paint);
        
		/*remoteViews.setTextViewText(R.id.widget_textview, "TIME = " +format.format(new Date()));*/
        remoteViews.setImageViewBitmap(R.id.imageView1, bitmap);
		appWidgetManager.updateAppWidget(thisWidget, remoteViews);
	}
		
	} 
}