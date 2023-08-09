package com.example.newsapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class News(
    val status:String?,
    val totalResult:Int?,
    val articles:ArrayList<Articles>,
) : Parcelable


