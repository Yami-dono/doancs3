package com.example.lastterm.Server

import com.google.gson.GsonBuilder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object apiRetrofit {

    var retrofit: Retrofit? = null

    fun getClient(baseURl: String): Retrofit {
       if(retrofit==null)
       {
           retrofit=Retrofit.Builder()
               .baseUrl(baseURl)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
       }

        return retrofit!!
    }
}