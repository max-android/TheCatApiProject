package com.my_project.thecatapiproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.arrayMapOf
import androidx.core.content.ContextCompat
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.my_project.thecatapiproject.R
import kotlinx.android.synthetic.main.activity_material_components.*
import timber.log.Timber

class MaterialComponentsActivity : AppCompatActivity() {

    //https://material.io/develop/android/components/dialog/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_components)


       // initBottomMenu()
       // showMaterialDialog()
       // initChips()
        setArrayMap()
    }


    private fun showMaterialDialog(){
        MaterialAlertDialogBuilder(this,R.style.DialogTheme)
            .apply {
            setTitle("Title")
            setMessage("Message")
            setPositiveButton("OК", null)
            show()
        }
    }


    private fun initBottomMenu(){
        //https://medium.com/over-engineering/hands-on-with-material-components-for-android-bottom-navigation-aae2aa9066be
        bottomNavigation.setOnNavigationItemSelectedListener {
                item ->
            when(item.itemId) {
                R.id.idHome -> {
                    // Do something for navigation item 1
                    true
                }
                R.id.idMail -> {
                    // Do something for navigation item 2
                    true
                }
                R.id.idVideocam -> {
                    // Do something for navigation item 2
                    true
                }

                else -> false
            }
        }

        bottomNavigation.getOrCreateBadge(R.id.idMail)

        val badge = bottomNavigation.getBadge(R.id.idMail)

        badge?.apply {
            backgroundColor = ContextCompat.getColor(this@MaterialComponentsActivity,R.color.colorAccent)
            number = 10
            badgeGravity = BadgeDrawable.TOP_END
        }

    }


    private fun initChips(){

        chip1.setOnClickListener {
            Timber.tag("--Chips-1").i("setOnClickListener")
        }

        chip1.setOnCloseIconClickListener {
            Timber.tag("--Chips-1").i("setOnCloseIconClickListener")
        }

        chip2.setOnCheckedChangeListener{comp,boolean->
            Timber.tag("--Chips-2").i(boolean.toString())
        }




        chipGroup.setOnCheckedChangeListener { group, checkedId ->
           // Timber.tag("--Chips").i("filterChip"+checkedId.toString())
            when(checkedId){
                R.id.filterChip ->{
                    chipGroup.removeView(filterChip)
                    Timber.tag("--Chips").i("filterChip"+checkedId.toString())
                }
                R.id.choiceChip ->{
                    chipGroup.removeView(choiceChip)
                    Timber.tag("--Chips").i("choiceChip"+checkedId.toString())
                }
                R.id.actionChip ->{
                    chipGroup.removeView(actionChip)
                    Timber.tag("--Chips").i("actionChip"+checkedId.toString())
                }
                R.id.entryChip ->{
                    chipGroup.removeView(entryChip)
                    Timber.tag("--Chips").i("entryChip"+checkedId.toString())
                }
            }
        }

    }


    private fun setArrayMap(){

        val hashMap =  hashMapOf<Int,String>(Pair(1,"1"),Pair(2,"2"),Pair(3,"3"))

        hashMap.put(4,"4")

//        hashMap.forEach {
//            Timber.tag("--Chips-1").i( it.key.toString())
//            Timber.tag("--Chips-1").i( it.value)
//
//        }



       // val arrayMap = ArrayMap<Int,String>()
        val arrayMap = arrayMapOf<Int,String>()

        arrayMap.put(4,"44444")
        arrayMap.put(3,"3333")
        arrayMap.put(8,"8888")
        arrayMap.put(8,"8888")

//        Timber.tag("--Chips-2").i(arrayMap.keyAt(0).toString())
//        Timber.tag("--Chips-2").i(arrayMap.valueAt(0))
//        Timber.tag("--Chips-2").i(arrayMap.keyAt(1).toString())
//        Timber.tag("--Chips-2").i(arrayMap.valueAt(1))


        arrayMap.forEach {

            Timber.tag("--Chips-2").i( it.key.toString())
            Timber.tag("--Chips-2").i( it.value)
        }


    }


}
