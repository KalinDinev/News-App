package com.example.newsapp.customAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.R
import com.example.newsapp.models.Articles
import com.example.newsapp.models.News
import com.example.newsapp.models.Source
import com.example.newsapp.views.ArticlesFragmentDirections


class ArticlesAdapter(
    private var news: News,
    private val onItemClick: () -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //implement current view
        val articleTitle = itemView.findViewById<TextView>(R.id.articleTitle)
        val articleImage = itemView.findViewById<ImageView>(R.id.articleImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val cardView = inflater.inflate(R.layout.articles_card, parent, false)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentItem = news.articles[position]
        val loadImage = currentItem.urlToImage

        viewHolder.articleImage.load(loadImage)
        viewHolder.articleTitle.text = currentItem.title

        val author = currentItem.author
        val title = currentItem.title
        val description = currentItem.description
        val publishAt = currentItem.publishAt
        val content = currentItem.content
        val url = currentItem.url
        val urlToImage = currentItem.urlToImage

        val id = currentItem.source?.id
        val name = currentItem.source?.name

        val currentSource = Source(id, name)
        val currentArticle =
            Articles(currentSource, author, title, description, url, urlToImage, publishAt, content)



        viewHolder.itemView.setOnClickListener {

            onItemClick.invoke() // Invoke the onItemClick callback

            val action = ArticlesFragmentDirections.actionArticlesToArticleDetails(currentArticle)
            viewHolder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return news.articles.size
    }

}