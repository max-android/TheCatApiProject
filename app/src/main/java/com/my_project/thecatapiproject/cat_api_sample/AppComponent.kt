package com.my_project.thecatapiproject.cat_api_sample

import com.my_project.thecatapiproject.ui.cat.CategoryViewModel
import com.my_project.thecatapiproject.ui.cat.MainActivity
import com.my_project.thecatapiproject.ui.cat.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
    (
    modules = [
        NetworkModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainViewModel: MainViewModel)
    fun inject(categoryViewModel: CategoryViewModel)
}