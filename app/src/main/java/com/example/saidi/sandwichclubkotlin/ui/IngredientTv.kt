package com.example.saidi.sandwichclubkotlin.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.example.saidi.sandwichclubkotlin.R

class IngredientTv @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    private var mIngredientTv: TextView

    init {
        LayoutInflater.from(context).inflate(getLayoutId(), this)
        mIngredientTv = findViewById(R.id.ingredients_tv)
    }

    private fun getLayoutId(): Int {
        return R.layout.ingredient_view
    }

    fun setIngredientTv(ingredientText: String) {
        mIngredientTv.text = ingredientText
    }
}