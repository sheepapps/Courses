package com.sheepapps.courses.sample

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sheepapps.courses.sample.databinding.ActivityMainBinding
import com.sheepapps.courses.sample.fragments.Fragment1
import com.sheepapps.courses.sample.fragments.Fragment2

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        binding.viewModel = mViewModel
        mViewModel.itemId.observe(this, Observer { processBottomClick(it) })

    }

    private fun processBottomClick(@IdRes itemId: Int) {
        when (itemId) {
            R.id.navigation_1 -> {
                selectFragment(Fragment1.newInstance())
            }
            R.id.navigation_2 -> {
                selectFragment(Fragment2.newInstance())
            }
            R.id.navigation_3 -> {
                startActivity(Intent(this, MapsActivity::class.java))
                //selectFragment(Fragment3.newInstance())
            }

        }
    }

    private fun selectFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
