package com.example.saidi.sandwichclubkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View.GONE
import android.widget.Toast
import com.example.saidi.sandwichclubkotlin.models.Sandwich
import com.example.saidi.sandwichclubkotlin.ui.IngredientTv
import com.example.saidi.sandwichclubkotlin.utils.JsonUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

const val EXTRA_POSITION = "extra_position"
const val DEFAULT_POSITION = -1

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        intent ?: closeOnError()
        val position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION)
        if (position == DEFAULT_POSITION) {
            closeOnError()
            return
        }
        val sandwiches = resources.getStringArray(R.array.sandwich_details)
        val json = sandwiches[position]
        val sandwich = JsonUtils.parseSandwichJson(json)
        sandwich ?: run {
            closeOnError()
            return
        }
        populateUI(sandwich)

    }

    private fun closeOnError() {
        finish()
        Toast.makeText(this, getString(R.string.detail_error_message), Toast.LENGTH_LONG).show()
    }

    private fun populateUI(sandwich: Sandwich?) {
        sandwich?.let {
            title = sandwich.mainName
            val placeOfOrigin = sandwich.placeOfOrigin
            if (!placeOfOrigin.isNullOrEmpty()) {
                origin_tv.text = placeOfOrigin
            } else {
                place_of_origin_layout.visibility = GONE
            }

            val alsoKnownAs = sandwich.alsoKnwonAs
            if (alsoKnownAs != null) {
                val alsoKnownAsString = TextUtils.join(",", alsoKnownAs)
                also_known_tv.text = alsoKnownAsString
            } else {
                also_known_layout.visibility = GONE
            }

            val description = sandwich.description
            if (!description.isNullOrEmpty()) {
                description_tv.text = description
            } else {
                description_layout.visibility = GONE
            }
            Picasso.with(this).load(sandwich.image).into(image_iv)
            name_tv.text = sandwich.mainName
            sandwich.ingredients?.forEach { ingredient ->
                val ingredientTv = IngredientTv(this)
                ingredientTv.setIngredientTv(ingredient)
                container.addView(ingredientTv)
            }
        }
    }
}
