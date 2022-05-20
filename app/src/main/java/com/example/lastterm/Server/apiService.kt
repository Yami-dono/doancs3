package com.example.lastterm.Server

import retrofit2.create

object apiService {
    var baseURL = "https://yamimusicplayer.000webhostapp.com/Server/"
    fun getService(): DataService{
        return apiRetrofit.getClient(baseURL).create(DataService::class.java)
    }


}