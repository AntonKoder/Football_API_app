package com.example.footballapiapp.screens.information

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.footballapiapp.R
import com.example.footballapiapp.databinding.InfoFragmentBinding
import com.example.footballapiapp.databinding.TeamsFragmentBinding
import com.example.footballapiapp.screens.teams.TeamsViewModel

class InfoFragment : Fragment() {

    private lateinit var viewModel: InfoViewModel

    private var nullableBinding: InfoFragmentBinding? = null
    private val binding get() = nullableBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = InfoFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        nullableBinding = null
    }

}