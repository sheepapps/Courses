package com.hornedheck.comeon;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hornedheck.comeon.Utils.MySQLiteHelper;

import java.util.ArrayList;
import java.util.HashSet;

public class Tasks {
	
	public static final String DELIMETER = "-";
	private static final Tasks ourInstance = new Tasks();
	public final String PREF_TAG = "tasks";
	private ArrayList< Task > allTasks;
	private MySQLiteHelper dbHelper;
	private Tasks(){
	}

	public static Tasks getInstance(Context context) {
		if( ourInstance.dbHelper == null ){
			ourInstance.allTasks = new ArrayList<>();
			ourInstance.dbHelper = new MySQLiteHelper(context);
			ourInstance.refreshTasks();
		}
		return ourInstance;
	}
	
	public void refreshTasks(){
		allTasks = (ArrayList<Task>) this.dbHelper.getAllTasks();
	}
	
	public void addTask( Task task ){
		allTasks.add( task );
		dbHelper.putTask( task );
		//save();
	}
	
	public ArrayList< Task > getAllTasks(){
		return allTasks;
	}
	
	public void removeTask( int index ){
		//allTasks.remove( index );
		//save();
	}
	/*public void save(){
		SharedPreferences.Editor prefsEditor;
		prefsEditor = preferences.edit();
		HashSet< String > stringSet = new HashSet<>();
		for( Task stringTask : allTasks ){
			stringSet.add( stringTask.getString() );
		}
		prefsEditor.putStringSet( PREF_TAG , stringSet );
		prefsEditor.apply();
	}*/
	
}
