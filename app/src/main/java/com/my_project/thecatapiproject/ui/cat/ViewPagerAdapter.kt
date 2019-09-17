package com.my_project.thecatapiproject.ui.cat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created Максим on 16.09.2019.
 * Copyright © Max
 */
class ViewPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragmentsList = mutableListOf<Fragment>()
    private val tabTitlesList = mutableListOf<String>()

    override fun getItem(position: Int): Fragment {
        return  fragmentsList[position]
    }

    override fun getCount() = tabTitlesList.size

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitlesList[position]
    }

    fun addTypesMenu(fragment: Fragment, title: String) {
        fragmentsList.add(fragment)
        tabTitlesList.add(title)
    }
}