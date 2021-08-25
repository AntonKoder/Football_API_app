package com.example.footballapiapp

import android.app.Application
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
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*
import javax.inject.Singleton


@Singleton
class MyApplication : Application() {

//    private lateinit var referrerClient: InstallReferrerClient
//
//    lateinit var GAID: String

    val appComponent = DaggerApplicationComponent.builder()
        .appModule(AppModule(this))
        .build()

//    override fun onCreate() {
//        super.onCreate()
//        AppsFlyerLib.getInstance().init("wWJSRfmZqWQFFFod4YfTR", conversionListener, this)
//        AppsFlyerLib.getInstance().start(this)
//
//
//        referrerClient = InstallReferrerClient.newBuilder(this).build()
//        referrerClient.startConnection(object : InstallReferrerStateListener {
//
//            @RequiresApi(Build.VERSION_CODES.N)
//            override fun onInstallReferrerSetupFinished(responseCode: Int) {
//                when (responseCode) {
//                    InstallReferrerClient.InstallReferrerResponse.OK -> {
//                        // Connection established.
//                        obtainReferrerDetails()
//                    }
//                }
//            }
//
//            override fun onInstallReferrerServiceDisconnected() {
//                // Try to restart the connection on the next request to
//                // Google Play by calling the startConnection() method.
//            }
//        })

//        invokeSuspend(this)


    }


    //        -----------------------------------------------------------|
//    @RequiresApi(Build.VERSION_CODES.N)
//    private fun obtainReferrerDetails() {
//        val response: ReferrerDetails = referrerClient.installReferrer
//
//        val referrerUrl: String = response.installReferrer
//
//        val resultMap = mutableMapOf<String, String>()
//        referrerUrl.split("&").associateTo(resultMap) {
//            val (left, right) = it.split("=")
//            left to right
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            resultMap.putIfAbsent("utm_source", "none")
//            resultMap.putIfAbsent("utm_medium", "none")
//            resultMap.putIfAbsent("utm_campaign", "none")
//        }
//        Log.d("tag", resultMap.toString())
//    }
//}


//val conversionListener: AppsFlyerConversionListener = object : AppsFlyerConversionListener {
//    override fun onConversionDataSuccess(conversionDataMap: Map<String, Any>) {
//        for (attrName in conversionDataMap.keys) Log.d(
//            LOG_TAG,
//            "Conversion attribute: " + attrName + " = " + conversionDataMap[attrName]
//        )
//        val status: String = Objects.requireNonNull(conversionDataMap["af_status"]).toString()
//        if (status == "Organic") {
//            // Business logic for Organic conversion goes here.
//        } else {
//            // Business logic for Non-organic conversion goes here.
//        }
//    }
//
//    override fun onConversionDataFail(errorMessage: String) {
//        Log.d(LOG_TAG, "error getting conversion data: $errorMessage")
//    }
//
//    override fun onAppOpenAttribution(attributionData: Map<String, String>) {
//        // Must be overriden to satisfy the AppsFlyerConversionListener interface.
//        // Business logic goes here when UDL is not implemented.
//    }
//
//    override fun onAttributionFailure(errorMessage: String) {
//        // Must be overriden to satisfy the AppsFlyerConversionListener interface.
//        // Business logic goes here when UDL is not implemented.
//        Log.d(LOG_TAG, "error onAttributionFailure : $errorMessage")
//    }
//
//
//}

//Вызывать асинхронно

//
///**
// * return String?
// * Google Advertising ID
// */
//private suspend fun getGAID(app: Application): String? {
//    var idInfo: AdvertisingIdClient.Info? = null
//    try {
//        idInfo = AdvertisingIdClient.getAdvertisingIdInfo(app.applicationContext)
//    } catch (e: GooglePlayServicesNotAvailableException) {
//        e.printStackTrace()
//    } catch (e: GooglePlayServicesRepairableException) {
//        e.printStackTrace()
//    } catch (e: IOException) {
//        e.printStackTrace()
//    }
//    var advertId: String? = null
//    try {
//        if (idInfo != null) {
//            advertId = idInfo.id
//        }
//    } catch (e: NullPointerException) {
//        e.printStackTrace()
//    }
//
//    return advertId
//}
//
//
//const val LOG_TAG = "log_tag"





// к этим полям додбавить uid

//        Его заполнить так:
//        1)Нужно взять GAID и туда записать
//        2)Проверить,если GAID пустой или ещё почему-то ничему не равен,то => идём в пункт 3. Если заполнен.То останавливаемся
//        3)Берём AppsflayerID,так-же проверяем,заполнился он или нет
//        4)Если и его нету,то создаём GUID
//        (https://developer.android.com/training/articles/user-data-ids#kotlin тут есть о GUID строка)
//
//        Получается,пробуем записать GAID->AppsflayerID->GUID
//        но если хоть одно уже что-то вернуло,то продолжаем с тем,А остальные пропускаем
//
//        Смотри,у тебя получается так: Апсфлаер асинхронно возвращает значение
//        и Install Referrer вызывается асинхронно.
//        поэтому,тебе нужно сделать так,чтобы метод вызвался,а ты параметры хранишь во внешних переменных
//
//        как пример:
//        ждёшь OnConversion от Listener и потом вызываешь InstallReferrer а в его колбеке вызовешь метод,который будет формировать строку

//        сделай метод,который будет генерировать ссылку,которая будет грузиться
//        в этом методе ты уже знаешь значения таких параметров как:
//        source,medium,campaign,uid
//        так-же имеешь словарь,который хранит значения апсфлаера
//        conversion_attribute это выше у тебя было

//                https://pastebin.com/pEaXQhz8


//        conversionDataDictionary ,это словарь appsflayer-а, потом я проверяю,органика ли это
//        если да,то формирую строку через string.format
//
//        URL - это ссылка,которую тебе дают в прилу изначально
//
//        Application.Identifier, это пакет прилы,пример: com.adad.asdad
//
//        остальные параметры мы уже вроде как обсудили.
//
//        потом,если это неорганика.То я проверяю в словаре значение campaign_id
//        и так-же формирую строку
//        ну и вариант,Если нету вообще параметра af_status (такого быть не должно,но обрабатываю на всякий случай)
//
//        Debug.log - просто вывод в консоль
//                PlayerPrefs - это я сохраняю значение под ключом чтобы при перезапуске его оттуда прочитать
//
//        StartWB.instance.StartInit(); - это начинаю загрузку страницы в WebView