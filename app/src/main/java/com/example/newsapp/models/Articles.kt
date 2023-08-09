package com.example.newsapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Articles(
    val source:Source?,
    val author:String?,
    val title:String?,
    val description:String?,
    val url:String?,
    val urlToImage:String?,
    val publishAt:String?,
    val content:String?,
) : Parcelable
