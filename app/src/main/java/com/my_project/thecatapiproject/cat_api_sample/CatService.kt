package com.my_project.thecatapiproject.cat_api_sample

import com.my_project.thecatapiproject.cat_api_sample.pojo.Category
import com.my_project.thecatapiproject.cat_api_sample.pojo.ItemCategory
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created Максим on 16.09.2019.
 * Copyright © Max
 */
interface CatService {

    //https://api.thecatapi.com/v1/categories
    @GET("categories")
    fun categories():Single<List<Category>>

   //https://api.thecatapi.com/v1/images/search?category_ids=6
    @GET("images/search?")
    fun itemCategory(@Query("category_ids")id:String):Single<List<ItemCategory>>

    //  https://api.thecatapi.com/v1/images/search?limit=100
    @GET("images/search?")
    fun images(@Query("limit")id:String):Single<List<ItemCategory>>
}