package com.sheepapps.courses.sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityViewModel : ViewModel() {

    val itemId = MutableLiveData<Int>().apply {
        value = R.id.navigation_1
    }

    val bottomNavigationViewListener = BottomNavigationView.OnNavigationItemSelectedListener {
        itemId.postValue(it.itemId)
        true
    }
}