package com.example.lastterm.Server


import com.example.lastterm.Model.AlbumItem
import com.example.lastterm.Model.PlaylistItem
import com.example.lastterm.Model.SliderItem
import com.example.lastterm.Model.SongItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface DataService {
    @GET("songslider.php")
    fun getSlider(): Call<List<SongItem>>
    @GET("Album.php")
    fun getAlbum(): Call<List<AlbumItem>>
    @FormUrlEncoded
    @POST("playlist_album.php")
    fun getlistAlbum(@Field("singerName") singerName: String): Call<List<SongItem>>
    @GET("Newsongupdate.php")
    fun getnewupdate(): Call<List<SongItem>>
    @GET("playlist%20.php")
    fun getPlaylist(): Call<List<PlaylistItem>>
    @FormUrlEncoded
    @POST("BXH.php")
    fun getlistBXH(@Field("playlistId") id: String): Call<List<SongItem>>
    @FormUrlEncoded
    @POST("Search.php")
    fun GetSearchSong(@Field("keyword") keyword: String): Call<List<SongItem>>
}