package com.tencalculus.bibliogram.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tencalculus.bibliogram.models.Books
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://www.googleapis.com/books/v1/"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BooksApi{
    @GET("volumes")
    suspend fun getBooksData(@Query("q") searchQuery: String): Books
    }

object BooksApiService {
    val retrofitService: BooksApi by lazy {
        retrofit.create(BooksApi::class.java)
    }
}
