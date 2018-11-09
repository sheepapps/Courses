package com.hornedheck.comeon.ToDoListActivity;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.hornedheck.comeon.Task;
import com.hornedheck.comeon.Tasks;
import com.hornedheck.comeon.Utils.MySQLiteHelper;

import java.util.List;

class ToDoListActivityViewModel extends ViewModel {
    Tasks tasks;
    private MutableLiveData<Boolean> isSettingsBtnPressed;
    private MutableLiveData<List<Task>> tasksData;
    private MySQLiteHelper dbHelper;

    ToDoListActivityViewModel(Context context) {
        dbHelper = new MySQLiteHelper(context);
        tasks = Tasks.getInstance(context);
    }

    MutableLiveData<Boolean> getSettingsBtnPressed() {
        if (isSettingsBtnPressed == null) {
            isSettingsBtnPressed = new MutableLiveData<>();
            isSettingsBtnPressed.setValue(false);
        }
        return isSettingsBtnPressed;
    }

    MutableLiveData<List<Task>> getTasksData() {
        if (tasksData == null) {
            tasksData = new MutableLiveData<List<Task>>();
            tasksData.setValue(tasks.getAllTasks());
        }
        return tasksData;
    }
}
