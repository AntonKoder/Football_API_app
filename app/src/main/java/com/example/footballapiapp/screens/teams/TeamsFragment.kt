package com.example.footballapiapp.screens.teams

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.footballapiapp.R
import com.example.footballapiapp.databinding.CountriesFragmentBinding
import com.example.footballapiapp.databinding.TeamsFragmentBinding
import com.example.footballapiapp.screens.countries.COUNTRY

class TeamsFragment : Fragment() {

    private lateinit var viewModel: TeamsViewModel

    private var nullableBinding: TeamsFragmentBinding? = null
    private val binding get() = nullableBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = TeamsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        var txt: String? = ""
        if (bundle != null) {
            txt = bundle.getString(COUNTRY)
        }
        binding.teamNameTv.text = txt
    }

    override fun onDestroy() {
        super.onDestroy()
        nullableBinding = null
    }


}