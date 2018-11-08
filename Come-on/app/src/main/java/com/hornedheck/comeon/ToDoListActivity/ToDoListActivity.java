package com.hornedheck.comeon.ToDoListActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.hornedheck.comeon.R;
import com.hornedheck.comeon.RVAdapter;
import com.hornedheck.comeon.SettingsActivity;
import com.hornedheck.comeon.Task;
import com.hornedheck.comeon.Tasks;

import java.util.Calendar;
import java.util.Date;

public class ToDoListActivity
  extends AppCompatActivity{
    private ToDoListActivityViewModel viewModel;
	
	final int DIALOG_DATE = R.id.add_date;
	final int DIALOG_TIME = R.id.add_time;
	RecyclerView rv;
	int myYear;
	int myMonth;
	int myDay;
	int myHour;
	int myMinute;
	RVAdapter adapter;
	DatePickerDialog.OnDateSetListener callBackDate = new DatePickerDialog.OnDateSetListener(){
		
		public void onDateSet( DatePicker view , int year , int monthOfYear , int dayOfMonth ){
			myYear = year;
			myMonth = monthOfYear;
			myDay = dayOfMonth;
		}
	};
	TimePickerDialog.OnTimeSetListener callBackTime = new TimePickerDialog.OnTimeSetListener(){
		
		public void onTimeSet( TimePicker view , int hourOfDay , int minute ){
			myHour = hourOfDay;
			myMinute = minute;
			
		}
	};
	
	@Override
	protected void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_to_do_list );
		rv = findViewById( R.id.tasks );
		LinearLayoutManager llm = new LinearLayoutManager( this );
		rv.setLayoutManager( llm );
        viewModel = ViewModelProviders.of(this).get(ToDoListActivityViewModel.class);
        final Observer<Boolean> goToSettingsObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    startActivity(new Intent(ToDoListActivity.this, SettingsActivity.class));
                    viewModel.getSettingsBtnPressed().setValue(false);
                }
            }
        };
        viewModel.getSettingsBtnPressed().observe(this, goToSettingsObserver);
		adapter = new RVAdapter( Tasks.getInstance( this ).getAllTasks() );
		rv.setAdapter( adapter );
	}

    public void goToSettings(View view) {
        viewModel.getSettingsBtnPressed().setValue(true);
    }

	public void addTask( View v ){
		Calendar calendar = Calendar.getInstance();
		myYear = calendar.get( Calendar.YEAR );
		myMonth = calendar.get( Calendar.MONTH );
		myDay = calendar.get( Calendar.DATE );
		myHour = calendar.get( Calendar.HOUR );
		myMinute = calendar.get( Calendar.MINUTE );
		AlertDialog.Builder builder = new AlertDialog.Builder( ToDoListActivity.this );
		final LinearLayout layout = (LinearLayout) getLayoutInflater().inflate( R.layout.alert_dialog_add_task , null );
		builder.setTitle( getString( R.string.add_alert ) ).setIcon( R.mipmap.ic_launcher_round ).setCancelable( false ).setView( layout ).setNegativeButton( getString( R.string.negative_button ) , new DialogInterface.OnClickListener(){
			
			public void onClick( DialogInterface dialog , int id ){
				dialog.cancel();
			}
		} ).setPositiveButton( getString( R.string.positive_button ) , new DialogInterface.OnClickListener(){
			
			public void onClick( DialogInterface dialog , int id ){
				//Сюда можно дописать проверку на корректность введенных данных
				Calendar cal = Calendar.getInstance();
				cal.set( Calendar.YEAR , myYear );
				cal.set( Calendar.MONTH , myMonth );
				cal.set( Calendar.DAY_OF_MONTH , myDay );
				cal.set( Calendar.HOUR , myHour );
				cal.set( Calendar.MINUTE , myMinute );
				Date date = cal.getTime();
				EditText name = (EditText) layout.getChildAt( 0 );
				EditText info = (EditText) layout.getChildAt( 1 );
				Task newTask = new Task( name.getText().toString() , info.getText().toString() , false , date );
				Tasks.getInstance( ToDoListActivity.this ).addTask( newTask );
				adapter.setTasks( Tasks.getInstance( ToDoListActivity.this ).getAllTasks() );
				dialog.cancel();
                /*
                 * ...
                 * t = new Task();
                 * List tasks = viewModel.getTasksData().getValue();
                 * tasks.add(t);
                 * viewModel.getTasksData().setValue(tasks);
                 * */
			}
		} );
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public void getDateTime( View v ){
		showDialog( v.getId() );
	}
	
	protected Dialog onCreateDialog( int id ){
		Dialog tpd = null;
		switch( id ){
			case DIALOG_DATE:
				tpd = new DatePickerDialog( this , callBackDate , myYear , myMonth , myDay );
				
				break;
			case DIALOG_TIME:
				tpd = new TimePickerDialog( this , callBackTime , myHour , myMinute , true );
				break;
			default:
				tpd = super.onCreateDialog( id );
				break;
		}
		return tpd;
		
	}
	public void setChecked(View v){
		int index = Integer.parseInt(v.getTag().toString());
		Tasks tasks = Tasks.getInstance(this);
		boolean done = ((CheckBox)v).isChecked();
		tasks.getAllTasks().get(index).setDone(done);
		tasks.save();
	}
}