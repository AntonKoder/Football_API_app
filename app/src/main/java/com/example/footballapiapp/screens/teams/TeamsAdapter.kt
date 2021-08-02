package com.example.footballapiapp.screens.teams

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballapiapp.R
import com.example.footballapiapp.models.ui.TeamUI

class TeamsAdapter : RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    private var listTeams = emptyList<TeamUI>()

    fun setData(list: List<TeamUI>) {
        listTeams = list
        notifyDataSetChanged()
    }

    class TeamsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = itemView.findViewById<TextView>(R.id.team_name)
        val logo: ImageView = itemView.findViewById<ImageView>(R.id.team_logo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false)
        return TeamsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.name.text = listTeams[position].name
        Glide.with(holder.logo.context).load(listTeams[position].logo)
            .into(holder.logo)
    }


    override fun getItemCount(): Int {
        return listTeams.size
    }
}