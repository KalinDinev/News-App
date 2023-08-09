package com.example.newsapp.customAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.models.Topic
import com.example.newsapp.views.NewsTopicFragmentDirections

class TopicAdapter(private var topicsList: List<Topic>) :RecyclerView.Adapter<TopicAdapter.ViewHolder>() {


    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        //implement current view
        val topic = itemView.findViewById<TextView>(R.id.topic)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context =parent.context
        val inflater =LayoutInflater.from(context)
        val cardView =inflater.inflate(R.layout.topic_card,parent,false)

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentItem =topicsList[position]
        val currentTopic =currentItem.currentTopic
        viewHolder.topic.text =currentTopic

        viewHolder.itemView.setOnClickListener {
            val action =NewsTopicFragmentDirections.actionNewsTopicToArticles(currentTopic)
            viewHolder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return topicsList.size
    }

}