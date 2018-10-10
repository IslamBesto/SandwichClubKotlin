package com.example.saidi.sandwichclubkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sandwich = resources.getStringArray(R.array.sandwich_names)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, sandwich)
        with(sandwiches_listview) {
            setAdapter(adapter)
            onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
                launchDetailActivity(i)
            }
        }
    }

    private fun launchDetailActivity(position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_POSITION, position)
        startActivity(intent)
    }

    fun ArrayList<Int>.addTwoNumber(n1: Int, n2: Int): Int {
        return n1 + n2
    }
}
