package com.hornedheck.comeon;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class DayProgresWidgetOne
  extends AppWidgetProvider{
	
	private static final String DATE_FORMAT = "HH:mm";
	
	@Override
	public void onUpdate( Context context , AppWidgetManager appWidgetManager , int[] appWidgetIds ){
		// There may be multiple widgets active, so update all of them
		for( int appWidgetId : appWidgetIds ){
			updateAppWidget( context , appWidgetManager , appWidgetId );
		}
	}
	
	
	@Override
	public void onEnabled( Context context ){
		// Enter relevant functionality for when the first widget is created
	}
	
	@Override
	public void onDisabled( Context context ){
		// Enter relevant functionality for when the last widget is disabled
	}
	
	static void updateAppWidget( Context context , AppWidgetManager appWidgetManager , int appWidgetId ){
		
		// Construct the RemoteViews object
		RemoteViews views = new RemoteViews( context.getPackageName() , R.layout.day_proggres_widget_one );
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( context );
		long startInMilis = timeInMilis( preferences.getString( SettingsActivity.START_TIME , context.getString( R.string.start_time_def ) ) );
		long endInMilis = timeInMilis( preferences.getString( SettingsActivity.END_TIME , context.getString( R.string.end_time_def ) ) );
		String currentDate = new SimpleDateFormat( DATE_FORMAT ).format( new Date() );
		long curInMilis = timeInMilis( currentDate );
		int progress = 0;
		if( endInMilis > startInMilis ){
			progress = (int) ( ( ( curInMilis - startInMilis ) * 100 ) / ( endInMilis - startInMilis ) );
		}
		views.setTextViewText( R.id.wo_progress_text , context.getString( R.string.wd_text , progress ) );
		views.setProgressBar( R.id.day_progress_bar , 100 , progress , false );
		// Instruct the widget manager to update the widget
		appWidgetManager.updateAppWidget( appWidgetId , views );
	}
	
	private static long timeInMilis( String s ){
		if( s.indexOf( ':' ) == - 1 ){
			return 1;
		}
		long hours = Long.parseLong( s.substring( 0 , s.indexOf( ':' ) ) ) * 1000 * 3600;
		long minutes = Long.parseLong( s.substring( s.indexOf( ':' ) + 1 ) ) * 60 * 1000;
		return hours + minutes;
	}
}

