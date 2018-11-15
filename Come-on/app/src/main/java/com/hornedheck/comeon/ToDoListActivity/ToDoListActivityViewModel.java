package com.hornedheck.comeon.ToDoListActivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.hornedheck.comeon.Task;
import com.hornedheck.comeon.Tasks;
import com.hornedheck.comeon.Utils.MySQLiteHelper;

import java.util.Date;
import java.util.List;

class ToDoListActivityViewModel extends AndroidViewModel {
    Tasks tasks;
    private MutableLiveData<Boolean> isSettingsBtnPressed;
    private MutableLiveData<Boolean> isAddBtnPressed;
    private MutableLiveData<List<Task>> tasksData;


    public ToDoListActivityViewModel(@NonNull Application application) {
        super(application);
        tasks = Tasks.getInstance(application.getApplicationContext());
    }

    List<Task> getAllTasks() {
        return tasks.getAllTasks();
    }

    LiveData<Boolean> getSettingsBtnPressed() {
        if (isSettingsBtnPressed == null) {
            isSettingsBtnPressed = new MutableLiveData<>();
            isSettingsBtnPressed.setValue(false);
        }
        return isSettingsBtnPressed;
    }

    LiveData<Boolean> getIsAddBtnPressed() {
        if (isAddBtnPressed == null) {
            isAddBtnPressed = new MutableLiveData<>();
            isAddBtnPressed.setValue(false);
        }
        return isAddBtnPressed;
    }

    LiveData<List<Task>> getTasksData() {
        if (tasksData == null) {
            tasksData = new MutableLiveData<List<Task>>();
            tasksData.setValue(tasks.getAllTasks());
        }
        return tasksData;
    }

    public void goToSettingButtonPressed() {
        isSettingsBtnPressed.setValue(true);
        isSettingsBtnPressed.setValue(false);
    }

    void addButtonPressed() {
        isAddBtnPressed.setValue(true);
        isAddBtnPressed.setValue(false);
    }

    void addTaskPressed(String name, String info, Date date) {
        tasks.addTask(new Task(name, info, false, date));
        tasksData.setValue(tasks.getAllTasks());
    }
}
