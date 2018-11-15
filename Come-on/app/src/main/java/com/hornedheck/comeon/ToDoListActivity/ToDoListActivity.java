package com.hornedheck.comeon.ToDoListActivity;

import android.app.DatePickerDialog;
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
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.hornedheck.comeon.R;
import com.hornedheck.comeon.RVAdapter;
import com.hornedheck.comeon.SettingsActivity;
import com.hornedheck.comeon.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ToDoListActivity
        extends AppCompatActivity {
    int year, mounth, day, hour, minute;
    private ToDoListActivityViewModel viewModel;
    private RVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        viewModel = ViewModelProviders.of(this).get(ToDoListActivityViewModel.class);
        adapter = new RVAdapter(viewModel.getAllTasks());
        LinearLayoutManager llm = new LinearLayoutManager(this);
        RecyclerView view = findViewById(R.id.tasks);
        view.setLayoutManager(llm);
        view.setAdapter(adapter);
        Observer<List<Task>> tasks = new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                adapter.setTasks(tasks);
            }
        };
        Observer<Boolean> settings = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean)
                    startActivity(new Intent(ToDoListActivity.this, SettingsActivity.class));
            }
        };
        Observer<Boolean> add = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    Calendar calendar = Calendar.getInstance();
                    year = calendar.get(Calendar.YEAR);
                    mounth = calendar.get(Calendar.MONTH);
                    day = calendar.get(Calendar.DAY_OF_MONTH);
                    hour = calendar.get(Calendar.HOUR_OF_DAY);
                    minute = calendar.get(Calendar.MINUTE);
                    final LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.alert_dialog_add_task, null);
                    AlertDialog.Builder addDialog = new AlertDialog.Builder(ToDoListActivity.this);
                    addDialog.setView(layout);
                    addDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    addDialog.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, mounth, day, hour, minute);
                            Date date = calendar.getTime();
                            EditText name = (EditText) layout.getChildAt(0);
                            EditText info = (EditText) layout.getChildAt(1);
                            Log.i("TAG", "onClick: " + name.getText());
                            viewModel.addTaskPressed(name.getText().toString(), info.getText().toString(), date);
                        }
                    });
                    addDialog.create().show();
                }
            }
        };
        viewModel.getIsAddBtnPressed().observe(this, add);
        viewModel.getSettingsBtnPressed().observe(this, settings);
        viewModel.getTasksData().observe(this, tasks);
    }

    public void goToSettingsPressed(View view) {
        viewModel.goToSettingButtonPressed();
    }

    public void addButtonPressed(View view) {
        viewModel.addButtonPressed();
    }

    public void timePicker(View view) {
        new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hour = i;
                minute = i1;
            }
        }, hour, minute, true).show();
    }

    public void datePicker(View view) {
        new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                year = i;
                mounth = i1;
                day = i2;
            }
        }, year, mounth, day).show();
    }
}