package com.my_project.thecatapiproject.cat_api_sample.pojo

import com.google.gson.annotations.Expose
import java.io.Serializable

/**
 * Created Максим on 16.09.2019.
 * Copyright © Max
 */

data class ItemCategory(
    val breeds:List<Any>,
    @Expose
    val categories:List<Category>?,
    @Expose
    val id:String,
    @Expose
    val url:String,
    @Expose
    val width:Int,
    @Expose
    val height:Int
):Serializable
