package com.my_project.thecatapiproject.ui.cat


import com.my_project.thecatapiproject.cat_api_sample.pojo.ItemCategory

/**
 * Created Максим on 16.09.2019.
 * Copyright © Max
 */

sealed class ItemCategoryState
class ResultItem(val list:List<ItemCategory>): ItemCategoryState()
class ErrorItem(val error:String): ItemCategoryState()