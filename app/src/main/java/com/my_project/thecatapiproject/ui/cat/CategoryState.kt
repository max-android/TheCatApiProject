package com.my_project.thecatapiproject.ui.cat

import com.my_project.thecatapiproject.cat_api_sample.pojo.Category

/**
 * Created Максим on 16.09.2019.
 * Copyright © Max
 */
sealed class CategoryState
  class Result(val list:List<Category>): CategoryState()
  class Error(val error:String): CategoryState()