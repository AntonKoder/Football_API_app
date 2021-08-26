package com.example.footballapiapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.example.footballapiapp.di.components.DaggerApplicationComponent
import com.example.footballapiapp.di.modules.AppModule
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import java.io.IOException
import java.util.*
import javax.inject.Singleton
import kotlin.collections.HashMap
import android.preference.PreferenceManager
import android.R.string








@Singleton
class MyApplication : Application() {

    private lateinit var referrerClient: InstallReferrerClient

    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    companion object {
        var rootURL = "http://46.161.53.135/rcwsmW/"

        var conversionDataMap_ = mutableMapOf<String, String>()

        var uId = "none"
        var source = "none"
        var medium = "none"
        var campaign = "none"
        var GAID = "none"
        var appsFlyerID = "none"

    }

    val appComponent = DaggerApplicationComponent.builder()
        .appModule(AppModule(this))
        .build()

    override fun onCreate() {
        super.onCreate()
        AppsFlyerLib.getInstance().init("wWJSRfmZqWQFFFod4YfTR", conversionListener, this)
        AppsFlyerLib.getInstance().start(this)
        appsFlyerID = AppsFlyerLib.getInstance().getAppsFlyerUID(this)
        preferences = PreferenceManager
            .getDefaultSharedPreferences(this)
    }

    private fun getLink(): String? {
        Log.d("debug","${preferences.getString("asd",null)}")
        return preferences.getString(GLOBAL_LINK, null)
    }

    private fun saveLink(link: String) {
        with(preferences.edit()) {
            putString(GLOBAL_LINK, link)
            apply()
        }
    }

    private fun saveUId() {
        with(preferences.edit()) {
            putString(GLOBAL_UID, uId)
            apply()
        }
    }

    fun initReferer(app: Application) {
        referrerClient = InstallReferrerClient.newBuilder(app).build()
        referrerClient.startConnection(object : InstallReferrerStateListener {

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        // Connection established.
                        obtainReferrerDetails()
                    }
                }
            }

            override fun onInstallReferrerServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun obtainReferrerDetails() {
        val response: ReferrerDetails = referrerClient.installReferrer

        val referrerUrl: String = response.installReferrer

        val resultMap = mutableMapOf<String, String>()
        referrerUrl.split("&").associateTo(resultMap) {
            val (left, right) = it.split("=")
            left to right
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            resultMap.putIfAbsent("utm_source", "none")
            resultMap.putIfAbsent("utm_medium", "none")
            resultMap.putIfAbsent("utm_campaign", "none")
        }

        source = resultMap["utm_source"] ?: "none"
        medium = resultMap["utm_medium"] ?: "none"
        campaign = resultMap["utm_campaign"] ?: "none"

        val link = createMyLink()
        saveLink(link)
    }

    private fun createMyLink(): String {
        var completeURL = ""
        val status: String = Objects.requireNonNull(conversionDataMap_["af_status"]).toString()
        if (status == "Organic") {
            completeURL =
                "$rootURL?uid=$uId&af_campaign_id=none&utm_source=$source&utm_medium=$medium&utm_campaign=$campaign&bundle=${this.applicationContext.packageName}"
            Log.d(LOG_TAG, "CompleteURL:: $completeURL")
        } else {
            // Business logic for Non-organic conversion goes here.
            var campaignId = "none"
            if (conversionDataMap_.containsKey("campaign_id")) {
                if (conversionDataMap_.get("campaign_id") != null) campaignId =
                    conversionDataMap_["campaign_id"].toString()
            }



            completeURL =
                "$rootURL?uid=$uId&af_campaign_id=$campaignId&utm_source=$source&utm_medium=$medium&utm_campaign=$campaign&bundle=${this.applicationContext.packageName}"

            Log.d(LOG_TAG, "CompleteURL:: $completeURL")
        }
        Log.d("link", completeURL)
        return completeURL
    }

    private fun createUId() {
        GAID = getGAID(this)
        if (GAID != ""){
            uId = GAID
        } else {
            if (appsFlyerID != null || appsFlyerID != ""|| appsFlyerID != "none"){
                uId = appsFlyerID
            } else {
                uId = UUID.randomUUID().toString()
            }
        }

    }


    val conversionListener: AppsFlyerConversionListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(conversionDataMap: Map<String, Any>) {
            if (getLink() == null) {
                Log.d("debug","link is null")
                conversionDataMap_ = conversionDataMap as MutableMap<String, String>

                createUId()

                initReferer(this@MyApplication)

            } else {
                Log.d("link", "${getLink()}")
            }
        }

        override fun onConversionDataFail(errorMessage: String) {
            Log.d(LOG_TAG, "error getting conversion data: $errorMessage")

            if (getLink() == null) {
                Log.d("debug","link is null")

                createUId()

                initReferer(this@MyApplication)

            } else {
                Log.d("link", "${getLink()}")
            }

        }

        override fun onAppOpenAttribution(attributionData: Map<String, String>) {
            // Must be overriden to satisfy the AppsFlyerConversionListener interface.
            // Business logic goes here when UDL is not implemented.
        }

        override fun onAttributionFailure(errorMessage: String) {
            // Must be overriden to satisfy the AppsFlyerConversionListener interface.
            // Business logic goes here when UDL is not implemented.
            Log.d(LOG_TAG, "error onAttributionFailure : $errorMessage")
        }


    }

}


/**
 * return String?
 * Google Advertising ID
 */
private fun getGAID(app: Application): String {
    var idInfo: AdvertisingIdClient.Info? = null
    try {
        idInfo = AdvertisingIdClient.getAdvertisingIdInfo(app.applicationContext)
    } catch (e: GooglePlayServicesNotAvailableException) {
        e.printStackTrace()
    } catch (e: GooglePlayServicesRepairableException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    var advertId: String? = null
    try {
        if (idInfo != null) {
            advertId = idInfo.id
        }
    } catch (e: NullPointerException) {
        e.printStackTrace()
    }

    return advertId ?: ""
}


const val LOG_TAG = "log_tag"


//пример словаря
//didReceiveConversionData: {"retargeting_conversion_type":"none","af_sub1":null,"af_reengagement_window":"30d","af_sub5":null,"is_first_launch":false,"click_time":"2021-08-20 09:22:48.420","network_account_id":null,"agency":null,"af_adset_id":"126310014699","af_ad_id":"","gclid":null,"adgroup_id":null,"http_referrer":null,"install_time":"2021-08-20 09:25:49.725","cost_cents_USD":"0","af_channel":"ACI_Display","af_click_lookback":"30d","orig_cost":"0.0","af_sub4":null,"click-timestamp":"1629451368420","click_url":null,"af_sub3":null,"af_c_id":"14339524501","media_source":"googleadwords_int","video_id":null,"campaign_id":"14339524501","af_ad":"","match_type":"srn","af_cpi":null,"adgroup":null,"af_sub2":null,"gbraid":null,"adset_id":null,"af_viewthrough_lookback":"1d","af_siteid":null,"network":"Display","adset":null,"af_adset":"126310014699","campaign":"TestAF","external_account_id":9493207935,"af_keywords":null,"af_status":"Non-organic","af_prt":null,"ad_event_id":"ClxDajBLQ1Fqd3BmMklCaERrQVJJc0FHVm8wRDJlcWZlSkZVOUdraW9BRU9QSE9uTHluT3dod0laR1F5d2pMcS1mbzJyU1pLcGI1V2NkdThJYUFyVFVFQUx3X3djQhITCLbCy4akv_ICFdOW7QodSx4OwhiM_rW8AiACKJWPz7U1MlpDajhLRVFqd3BmMklCaENFbTkyWXZwaTR3ZVlCRWlZQTl3SGpJUE5TdzVfRmZRRmctNkFZcG9ITEQ3RFNQdDhoUFUzWGVON0hkNGlCRFk4TFJob0NkUFVZQVE","iscache":true,"af_ad_type":"ClickToDownload","lat":"0"}


// рабочая ссылка для проверки есть ли регистрация или нет

//var URL: string = string.Format(
//    "https://sport-privacy.ru/apyag4/check.php?uid={0}&bundle={1}",
//    uid,
//    Application.identifier
//)