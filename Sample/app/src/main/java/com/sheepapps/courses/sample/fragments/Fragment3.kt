package com.sheepapps.courses.sample.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sheepapps.courses.sample.R

class Fragment3 : Fragment() {

    companion object {
        fun newInstance() = Fragment3()
    }

    private lateinit var viewModel3: ViewModel3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel3 = ViewModelProviders.of(this).get(ViewModel3::class.java)
        // TODO: Use the ViewModel
    }

}
