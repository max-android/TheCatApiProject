package com.my_project.thecatapiproject.ui.cat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my_project.thecatapiproject.App
import com.my_project.thecatapiproject.cat_api_sample.CatService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created Максим on 16.09.2019.
 * Copyright © Max
 */
class CategoryViewModel:ViewModel() {


    @Inject lateinit var api:CatService
    private val compositeDisposable = CompositeDisposable()
    val itemsCatLiveData = MutableLiveData<ItemCategoryState>()

    init {
        App.appComponent.inject(this)
    }


    fun itemCategory(id:String){
        Timber.tag("--CAT").i("REQUEST SERVER itemCategory()")
        api.itemCategory(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    itemsCatLiveData.value = ResultItem(it)
                },{
                    itemsCatLiveData.value = ErrorItem(it.message.toString())
                }
            ).addTo(compositeDisposable)
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}