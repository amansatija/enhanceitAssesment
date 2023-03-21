package com.example.enhanceittechincaltest.utils.api

import com.example.enhanceittechincaltest.BuildConfig
import com.example.enhanceittechincaltest.base.app.AppID

import inc.credible.homerlibs.session.SessionProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * this class houses the singelton instance our of retrofit api client inteface
 */
class ApiClientProvider {
    companion object{
        private var instance: ApiClientProvider?=null ;
        fun getInstance(): ApiClientProvider {
            if(instance ==null){
                instance = ApiClientProvider();
            }
            return instance!!;
        }

        fun getApiClient(): ApiService {
            return getInstance().apiClient!!;
        }
    }

//    private  val baseUrlLocalHost = "http://localhost:3002"
    private  val baseUrlLocalHost = "https://api.tvmaze.com"

    private  val baseUrlProd = "https://api.tvmaze.com"
//        const val baseUrlDev = "http://localhost:3002"
//        const val baseUrlStaging = "http://localhost:3002"

    fun getBaseUrl():String{
        return if (BuildConfig.DEBUG) {
            baseUrlLocalHost;
        } else {
            baseUrlProd
        }
    }

    fun getBaseApiUrl():String{
        return getBaseUrl()+"/" //"/api/v1/";
    }

    fun getBaseAssetsUrl():String{
        return getBaseUrl()+"/assets";
    }

    private constructor() {
    }
    init {
        setupRestClient()
    }

    var apiClient: ApiService? = null
    lateinit var retrofit:Retrofit
    private fun setupRestClient() {

        val httpClientbuilder = OkHttpClient.Builder()
        httpClientbuilder.connectTimeout(40, TimeUnit.SECONDS)
        httpClientbuilder.writeTimeout(40, TimeUnit.SECONDS)
        httpClientbuilder.readTimeout(40, TimeUnit.SECONDS)
        ///add Custom Interceptor
        httpClientbuilder.addInterceptor(Interceptor { chain ->
            val original = chain.request()

            // Customize the request
            val mRequestBuilder: Request.Builder = original.newBuilder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
            //add authroization token from user data ....!!
            if (SessionProvider.isUserLoggedIn(AppID.instance)) {
                val mStrToken =
                    SessionProvider.getmUser(AppID.instance).getmToken()
                if (mStrToken != null && !mStrToken.isEmpty()) {
                    mRequestBuilder.header("Authorization", "Bearer $mStrToken")
                }
            }
            mRequestBuilder.method(original.method, original.body)
            val request: Request = mRequestBuilder.build()

            // Customize or return the response
            chain.proceed(request)
        })

        ///addd logging interceptor
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClientbuilder.addInterceptor(logging)
        val mOkHttpClient: OkHttpClient = httpClientbuilder.build()
        val builder = Retrofit.Builder()
            .baseUrl(getBaseApiUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
        retrofit = builder.build()
        apiClient= retrofit.create(ApiService::class.java)
    }



}