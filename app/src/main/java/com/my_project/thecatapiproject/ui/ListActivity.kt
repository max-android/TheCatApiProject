package com.my_project.thecatapiproject.ui

import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my_project.thecatapiproject.R
import com.my_project.thecatapiproject.diffutil_callback_sample.adapters.NotyAdapter
import com.my_project.thecatapiproject.diffutil_callback_sample.adapters.NotyAdapter2
import com.my_project.thecatapiproject.diffutil_callback_sample.adapters.TypesAdapter
import com.my_project.thecatapiproject.diffutil_callback_sample.data.*
import kotlinx.android.synthetic.main.activity_list.*
import timber.log.Timber

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initAdapter()
        //init()
       // init2()
    }

    private fun init() {
        val myAdapter = NotyAdapter()

        typesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListActivity, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@ListActivity, LinearLayout.VERTICAL))
            adapter = myAdapter
        }

        myAdapter.swapItems(
            listOf(
                Noty(1, "name-1", 111),
                Noty(2, "name-2", 222),
                Noty(3, "name-3", 333),
                Noty(4, "name-4", 444)
                //,
//                Noty(5, "name-5", 555),
//                Noty(6, "name-6", 666),
//                Noty(7, "name-7", 777)
            )
        )

        Handler().postDelayed({

            myAdapter.swapItems(
                listOf(
                    Noty(1, "name-11-update", 11111111),
                    Noty(2, "name-22-update", 22222222),
                    Noty(3, "name-3", 333),
                    Noty(4, "name-4", 444)
//                    Noty(5, "name-5", 555),
//                    Noty(6, "name-6", 666),
//                    Noty(7, "name-7", 777)
                )
            )
        }, 7000)

    }

    private fun init2() {

        val myAdapter = NotyAdapter2()

        typesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListActivity, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@ListActivity, LinearLayout.VERTICAL))
            adapter = myAdapter
        }

        myAdapter.submitList(
            listOf(
                Noty(1, "name-1", 111),
                Noty(2, "name-2", 222),
                Noty(3, "name-3", 333),
                Noty(4, "name-4", 444)
            )
        )

        Handler().postDelayed({
            myAdapter.submitList(
                listOf(
                    Noty(1, "name-1-update", 111),
                    Noty(2, "name-2-update", 222),
                    Noty(3, "name-3", 333),
                    Noty(8, "name-8-new", 888)
                )
            )
        }, 7000)

    }

    private fun initAdapter() {
        val myAdapter = TypesAdapter()

        typesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListActivity, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@ListActivity, LinearLayout.VERTICAL))
            adapter = myAdapter
        }

        myAdapter.onItemClick { showDetail(it) }
        myAdapter.swapItems(createData())

        Handler().postDelayed({

            myAdapter.swapItems(createData2())
        }, 7000)
    }

    private fun showDetail(type: MyType) {

        when (type) {
            is MyType1 -> {
                Timber.tag("--SAMPLE").i(type.title)
                Timber.tag("--SAMPLE").i(type.description)
            }

            is MyType2 -> {
                Timber.tag("--SAMPLE").i(type.info2)
            }

            is MyType3 -> {
                Timber.tag("--SAMPLE").i(type.info3)
            }
        }
    }

    private fun createData(): List<MyType> {

        val api = listOf(
            TypeApi(1, "title-1", "description-1", null, null, null, null),
            TypeApi(2, "title-2", "description-2", null, null, null, null),
            TypeApi(
                3,
                null,
                null,
                null,
                null,
                "https://mtdata.ru/u28/photo01A0/20115904037-0/original.jpeg",
                "info-3"
            ),
            TypeApi(
                4,
                null,
                null,
                null,
                null,
                "https://pinterest.ru.com/images/2018/10/24/KRASIVOE-OZERO.jpg",
                "info-4"
            ),
            TypeApi(
                5,
                null,
                null,
                "https://www.nastol.com.ua/pic/201605/1920x1200/nastol.com.ua-175271.jpg",
                "info-5",
                null,
                null
            ),
            TypeApi(
                6,
                null,
                null,
                "https://pinterest.ru.com/images/2018/10/29/nz2.jpg",
                "info-6",
                null,
                null
            )
        )

        return api.map { TypeMapper.mapApiToUI(it) }
    }


    private fun createData2(): List<MyType> {

        val api = listOf(
            TypeApi(1, "title-1-update", "description-1", null, null, null, null),
            TypeApi(2, "title-2-update", "description-2", null, null, null, null),
            TypeApi(
                3,
                null,
                null,
                null,
                null,
                "https://mtdata.ru/u28/photo01A0/20115904037-0/original.jpeg",
                "info-3"
            ),
            TypeApi(
                4,
                null,
                null,
                null,
                null,
                "https://www.nastol.com.ua/pic/201605/1920x1200/nastol.com.ua-175271.jpg",
                "info-4"
            ),
            TypeApi(
                5,
                null,
                null,
                "https://www.nastol.com.ua/pic/201605/1920x1200/nastol.com.ua-175271.jpg",
                "info-5",
                null,
                null
            ),
            TypeApi(
                6,
                null,
                null,
                "https://pinterest.ru.com/images/2018/10/29/nz2.jpg",
                "info-6",
                null,
                null
            )
        )

        return api.map { TypeMapper.mapApiToUI(it) }
    }


}
