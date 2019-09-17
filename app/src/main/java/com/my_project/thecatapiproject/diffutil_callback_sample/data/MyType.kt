package com.my_project.thecatapiproject.diffutil_callback_sample.data

/**
 * Created Максим on 13.09.2019.
 * Copyright © Max
 */
sealed class MyType {
    fun getItemId() = when (this) {
        is MyType1 -> this.id
        is MyType2 -> this.id
        is MyType3 -> this.id
    }
}

data class MyType1(val id: Int, val title: String, val description: String) : MyType()
data class MyType2(val id: Int, val url2: String, val info2: String) : MyType()
data class MyType3(val id: Int, val url3: String, val info3: String) : MyType()




