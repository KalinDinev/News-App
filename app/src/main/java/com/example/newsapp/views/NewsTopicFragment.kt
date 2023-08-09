package com.example.newsapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.customAdapter.TopicAdapter
import com.example.newsapp.databinding.FragmentNewsTopicBinding
import com.example.newsapp.models.Topic


class NewsTopicFragment : Fragment() {

    private lateinit var recyclerView:RecyclerView
    private lateinit var binding:FragmentNewsTopicBinding

    private val listTopic= listOf(
        Topic("business"),
        Topic("entertainment"),
        Topic("general"),
        Topic("health"),
        Topic("science"),
        Topic("sports"),
        Topic("technology")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentNewsTopicBinding.inflate(inflater,container,false)
        val root =binding.root
        val adapter =TopicAdapter(listTopic)
        recyclerView=binding.topicViews
        recyclerView.layoutManager =LinearLayoutManager(requireContext())
        recyclerView.adapter =adapter

        return  root
    }

}