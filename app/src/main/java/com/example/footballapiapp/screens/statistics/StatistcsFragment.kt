package com.example.footballapiapp.screens.statistics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.footballapiapp.R

class StatistcsFragment : Fragment() {

    companion object {
        fun newInstance() = StatistcsFragment()
    }

    private lateinit var viewModel: StatistcsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.statistcs_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatistcsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}