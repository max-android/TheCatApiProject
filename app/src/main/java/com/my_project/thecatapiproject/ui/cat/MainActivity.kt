package com.my_project.thecatapiproject.ui.cat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.my_project.thecatapiproject.R
import com.my_project.thecatapiproject.cat_api_sample.pojo.Category
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        init()
        if (savedInstanceState == null) {
            viewModel.categories()
        }
        observeData()
    }

    private fun showData(list:List<Category>){
        list.forEach { addMenuToAdapter(it) }
    }

    private fun init(){
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun addMenuToAdapter(types: Category){
        Timber.tag("--CAT-addMenuToAdapter").i(types.name)
            viewPagerAdapter.addTypesMenu(
                CategoryFragment.newInstance(
                    types
                ),types.name)
            viewPagerAdapter.notifyDataSetChanged()
    }

    private fun observeData(){
        viewModel.catLiveData.observe(this, Observer {
                state->
            state?.let {
                when(it){
                    is Result -> showData(it.list)
                    is Error -> Timber.tag("--CAT-error").i(it.error)
                }
            }
        })
    }

}
