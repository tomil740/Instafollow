package com.example.instafollow.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

val idLst = mutableListOf<Map<String,Int>>(mapOf("theLink" to 0))
var j = 1

fun idGenerator (name:String):Int {
    val theId = j
     for(i in idLst)
         if(i.containsKey(name))
             return i.get(name)!!
         else {
             j++
             idLst.add(mapOf(name to theId))
         }
    return theId
}

@Parcelize
data class AccountDetails(
    val href: String,
    @Json(name = "theLink") val value: String,
    val timestamp: Double
): Parcelable {
    val id
        get() = idGenerator(value)
    }


