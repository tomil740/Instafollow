package com.example.instafollow.followers

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instafollow.network.AccountDetails
import com.example.instafollow.network.loadJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

enum class WhichLst { FOLLOWERS1, FOLLOWER_NO2,FOLLOWING_NO1,MUTAL }


class FollowersViewModel(val application: Application) : ViewModel() {

    private val _properties = MutableLiveData<List<AccountDetails>>()

        val properties: LiveData<List<AccountDetails>>
            get() = _properties

    private val _test = MutableLiveData<String>()

    val test: LiveData<String>
        get() = _test

        private val _navToDetails = MutableLiveData<AccountDetails?>()
        val navToDetails: LiveData<AccountDetails?>
            get() = _navToDetails

        private var viewModelJob = Job()
        private val coroutineScope = CoroutineScope(viewModelJob+ Dispatchers.Main)

       fun updateList(list:WhichLst) {
           getAccountsDetails(list)
       //  val theLst = listFilter(followers, following,list)

        // _properties.value = theLst

       }

        //
        init {
            //first of all try to get the data from the web
            getAccountsDetails(WhichLst.FOLLOWERS1)
        }


        //this function using retrofit library function to get data from the web server
        //the method automatically will run on coroutines
        private fun getAccountsDetails(list: WhichLst) {
            coroutineScope.launch {
                try {
                    //this is only for practice to do web connections
                   // var listResult = FollowersObj.retrofitService.getProperties()
                    //the translate to kotlin data objects from the locals xml file
                    val jasonFollowers = loadJson(application,1)
                    val jasonFollowing = loadJson(application,2)
                   // val a = sortStr(jasonFollowing!!)

                   // Log.i("123456789101112",a)
                   // val followers = sortStr(jasonFollowers!!)
                    val following = sortStr(jasonFollowing!!)
                    val followers = sortStr(jasonFollowers!!)

                     val theLst = listFilter(followers, following,list)
                    _properties.value = theLst

                } catch (e: Exception) {

                    _properties.value = listOf(AccountDetails("k","d",9.2))
                }
            }
        }

    fun displayPropertyDetails(AccountDetails: AccountDetails) {
        _navToDetails.value = AccountDetails
    }

    fun displayPropertyDetailsComplete() {
        _navToDetails.value = null
    }


    }
//in general we will get
    fun listFilter(followers:List<AccountDetails>, following:List<AccountDetails>, whichLst:WhichLst):List<AccountDetails>{

        var isShared:Boolean = false
        var isSwitch:Boolean = false

        val SharedItems = mutableListOf<AccountDetails>()
        val onlyInLong = mutableListOf<AccountDetails>()
        val onlyIn = mutableListOf<AccountDetails>()
        val long = mutableListOf<AccountDetails>()
        val short = mutableListOf<AccountDetails>()

        long.addAll(followers)
        short.addAll(following)

        if(long.size < short.size){
            long.clear()
            short.clear()
            short.addAll(followers)
            long.addAll(following)
            isSwitch = true
        }

        for(i in long){
            for(j in short){
                if(i.value == j.value)
                {
                    SharedItems.add(i)
                    isShared = true
                }
            }
                if(!isShared)
                    onlyInLong.add(i)
                    isShared=false
        }



    short.removeAll(SharedItems)

    var onlyFollowing = short
    var onlyFollowers = onlyInLong

    if(isSwitch){
        onlyFollowers = short
        onlyFollowing = onlyInLong
    }
    return (
    when(whichLst){
        WhichLst.FOLLOWER_NO2 -> onlyFollowers
        WhichLst.FOLLOWING_NO1-> onlyFollowing
        WhichLst.MUTAL-> SharedItems
        else -> followers
    }
            )

    }

    //val JasonStr = loadJson(application)

     fun sortStr(JasonStr:String):List<AccountDetails> {
         var targetStr: String = ""
         val theLst = mutableListOf<AccountDetails>()

         var currentObj: String = ""
        var theStr: String = JasonStr!!
        var theInd: Int = theStr.indexOf('{') + 1

        do {
            theStr = theStr.substring(theInd)
            currentObj = theStr.substring(theStr.indexOf('{'), theStr.indexOf('}') + 1)
            theLst.add(getObj(currentObj))
            targetStr += "@@@!!this is object $currentObj the end of this object !!!@@"
            theStr = theStr.substring(theStr.indexOf('}'))
            theInd = theStr.indexOf("string_list_data")
        } while (theInd != -1)
        var testObj: String = ""
        var index: Int = 0

        for (i in theLst) {
            index++
            testObj += "object $index : name to inst:${i.href} , the theLink is: ${i.value} , and the time shit is ${i.timestamp}\n\n\n"
        }


         return theLst

    }





fun getObj(theStr1:String):AccountDetails {

    var theStr = theStr1
    val key1: String = theStr.substring(theStr.indexOf(":") + 3, theStr.indexOf(',') - 1)
    theStr = theStr.substring(theStr.indexOf(",") + 1)
    val key2: String = theStr.substring(theStr.indexOf(":") + 3, theStr.indexOf(',') - 1)
    theStr = theStr.substring(theStr.indexOf(",") + 1)
    val key3: String = theStr.substring(theStr.indexOf(":") + 2, theStr.indexOf("}") - 2)

    //return info("key1","key2",5)

    return AccountDetails(key1,key2,5.9)
}

