package com.tencalculus.bibliogram.models

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize


@Parcelize
data class BooksData(
        val id: String,
        var language: String,
        val title: String,
        val thumbnail: String,
        var pageCount: String,
        val previewLink: String
) : Parcelable {
    @IgnoredOnParcel
    var shortTitle: String? = null
    init {
        when {
            title.length > 34 -> shortTitle = title.substring(0, 34) + "..."
            else -> shortTitle = title
        }
        when (pageCount) {
            "-1" -> pageCount = "Unknown"
            else -> pageCount = "$pageCount Pages"
        }
        when (language) {
            "en" -> language = "English"
            else -> language = "Unknown"
        }
    }
}

fun asBooksData(books: Books): List<BooksData> {
    val itemList = books.items
    val itemSize = itemList.size - 1
    val booksData = mutableListOf<BooksData>()
    for (i in 0 until itemList.size){
        val id: String = itemList[i].id
        val language: String = itemList[i].volumeInfo.language
        val title: String = itemList[i].volumeInfo.title
        val thumbnail: String = itemList[i].volumeInfo.imageLinks.thumbnail
        val pageCount: String = itemList[i].volumeInfo.pageCount.toString()
        val previewLink: String = itemList[i].volumeInfo.previewLink
        val newBook = BooksData(id, language, title, thumbnail, pageCount, previewLink)
        booksData.add(newBook)
    }
    return booksData
}

enum class LoadStatus{
    LOADING,
    SUCCESS,
    FAILED
}