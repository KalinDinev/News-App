package com.example.newsapp.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.newsapp.databinding.FragmentArticleDetailsBinding


class ArticleDetailsFragment : Fragment() {


    private val args by navArgs<ArticleDetailsFragmentArgs>()
    private lateinit var binding: FragmentArticleDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        val root = binding.root

        val image = args.currentArticle.urlToImage
        val author = args.currentArticle.author
        val title = args.currentArticle.title
        val description = args.currentArticle.description
        val publishAt = args.currentArticle.publishAt
        val content = args.currentArticle.content
        val urlToWeb = args.currentArticle.url
        val openBtn = binding.openBtn

        //We are checking for null value's from the API and set default value's if so...
        checkValues(author, title, description, publishAt, content)
        binding.imageView.load(image)

        //onClick open that article in browser..
        openBtn.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(urlToWeb)
            startActivity(openURL)
        }

        return root
    }


    private fun checkValues(
        author: String?, title: String?, description: String?, publishAt: String?, content: String?
    ) {
        if (author == null) {
            binding.authorTextView.text = "Author:Unknown"
        } else {
            binding.authorTextView.text = author
        }
        if (title == null) {
            binding.titleTextView.text = "Title:Unknown"
        } else {
            binding.titleTextView.text = title
        }
        if (description == null) {
            binding.descriptionTextView.text = "Description:Unknown"
        } else {
            binding.descriptionTextView.text = description
        }
        if (publishAt == null) {
            binding.publishedAtTextView.text = "PublishAt:Unknown"
        } else {
            binding.publishedAtTextView.text = publishAt
        }
        if (content == null) {
            binding.contentTextView.text = "Content:Unknown"
        } else {
            binding.contentTextView.text = content
        }
    }

}