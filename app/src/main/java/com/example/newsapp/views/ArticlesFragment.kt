package com.example.newsapp.views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.customAdapter.ArticlesAdapter
import com.example.newsapp.databinding.FragmentArticlesBinding
import com.example.newsapp.models.News
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException


class ArticlesFragment : Fragment() {

    private val args by navArgs<ArticlesFragmentArgs>()
    private lateinit var binding: FragmentArticlesBinding
    private lateinit var recyclerView: RecyclerView
    private var responseJson: String? = null
    private lateinit var sharedPreferences: SharedPreferences
    private var counter: Int = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArticlesBinding.inflate(inflater, container, false)
        val root = binding.root

        run()
        binding.counter.text ="Read article's ($counter)"

        return root
    }

    private val client = OkHttpClient()

    private fun run() {
        val currentTopic = args.currentTopic
        val url ="https://newsapi.org/v2/top-headlines?country=us&category=$currentTopic&apiKey=b27c2c2641ba4772b1fbc42bb09f9f84"

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        // Handle the error here, you might want to log it
                        Log.e("API_ERROR", "API request was not successful: ${response.code}")
                        return
                    }

                    responseJson = response.body!!.string()

                    // Parse JSON and update UI on the main thread
                    val news = Gson().fromJson(responseJson, News::class.java)
                    requireActivity().runOnUiThread {
                        updateUIWithNews(news)
                    }
                }
            }
        })

        sharedPreferences = requireContext().getSharedPreferences("CounterPrefs", Context.MODE_PRIVATE)
        counter = sharedPreferences.getInt("counter", 0)
    }

    private fun updateUIWithNews(news: News) {
        recyclerView = binding.articlesViews
        val news = Gson().fromJson(responseJson, News::class.java)
        // Pass the entire News object
        val adapter = ArticlesAdapter(news) {
            incrementCounter()
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }


    private fun incrementCounter() {
        counter++
        // Update the counter TextView in the toolbar
        binding.counter.text = "Read article's ($counter)"

        // Save the counter value in SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("CounterPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("counter", counter).apply()
    }

}