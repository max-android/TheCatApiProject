package com.my_project.thecatapiproject.cat_api_sample.pojo


import com.google.gson.annotations.Expose

import java.io.Serializable

/**
 * Created Максим on 16.09.2019.
 * Copyright © Max
 */

data class Category(
    @Expose
    val id: Int,
    @Expose
    val name: String
) :Serializable
