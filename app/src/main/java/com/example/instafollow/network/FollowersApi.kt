package com.example.instafollow.network

import android.content.Context
import com.example.instafollow.followers.WhichLst
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.InputStream


//get the data from the local files


private const val BASE_URL = "https://mars.udacity.com/"

    private val moshi = Moshi.Builder()
        .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
        .build()

    //create the retrofit builder specified the convertor from jason (in that case) to String and send the url of the server
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface FollowersApi {
    //specified interaction with the server in order to get data use annoation with parameter the
    //destination in the server url and genral function to be used get an call object
    @GET("realestate")
    suspend fun getProperties(): List<MarsProperty>
}

//general public object for geting to those functions with another object create with
//retrofit library function to create this object
object FollowersObj {
    val retrofitService : FollowersApi by lazy { retrofit.create(FollowersApi::class.java) }
}

fun loadJson(context: Context,whichFile:Int): String? {
    val file = mapOf<Int,String>(1 to "followers.json",2 to "following.json")
    var input: InputStream? = null
    var jsonString: String

    try {
        // Create InputStream
        input = file[whichFile]?.let { context.assets.open(it) }

        val size = input!!.available()

        // Create a buffer with the size
        val buffer = ByteArray(size)

        // Read data from InputStream into the Buffer
        input.read(buffer)

        // Create a json String
        jsonString = String(buffer)
        return jsonString;
    } catch (ex: Exception) {
        ex.printStackTrace()
    } finally {
        // Must close the stream
        input?.close()
    }

    return null
}






