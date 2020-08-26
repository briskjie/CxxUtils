package com.briskjie.cxx

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OverRecyclerViewActivity : Activity() {
    private var list: ArrayList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bounced_recyclerview)
        initData()
        initView()
    }

    private fun initData() {
        list = ArrayList();
        for (i in 1..10) {
            list!!?.add("我是条目$i")
        }
    }

    private fun initView() {
        val mRecycleView = findViewById<RecyclerView>(R.id.recycler)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mRecycleView.layoutManager = layoutManager;
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        mRecycleView.addItemDecoration(itemDecoration)
        mRecycleView.itemAnimator = DefaultItemAnimator()
        val adapter = OverSrollRecyclerAdapter(this, list)
        mRecycleView.adapter = adapter
        adapter.setOnItemClicklistener(object : OverSrollRecyclerAdapter.ItemClickListener {
            override fun onItemClickListener(position: Int) {
                Toast.makeText(applicationContext, list!![position], Toast.LENGTH_LONG).show()
            }
        })
    }
}