package com.my_project.thecatapiproject.cat_api_sample
import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule(private val context:Context) {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .addInterceptor(Interceptor { chain ->
//                if (!NetworkUtil.isOnline(context)) {
//                    throw ConnectException()
//                }
//                val builder = chain.request().newBuilder()
//                val responce = chain.proceed(builder.build())
//                responce
//            })
            .build()

    @Provides
    @Singleton
    fun provideGsonBuilder(): GsonBuilder {
        return GsonBuilder()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonBuilder: GsonBuilder): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CatService = retrofit.create(CatService::class.java)

}