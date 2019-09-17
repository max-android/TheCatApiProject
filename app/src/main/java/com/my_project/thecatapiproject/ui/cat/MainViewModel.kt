package com.my_project.thecatapiproject.ui.cat

import android.annotation.SuppressLint
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
class MainViewModel:ViewModel() {

@Inject lateinit var  api:CatService

private val compositeDisposable = CompositeDisposable()

    val catLiveData = MutableLiveData<CategoryState>()

    init {
        App.appComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    fun categories(){
         Timber.tag("--CAT").i("REQUEST SERVER categories()")
        api.categories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {

                    catLiveData.value = Result(it)

            },{
                    catLiveData.value = Error(it.message.toString())
            }
            ).addTo(compositeDisposable)

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}