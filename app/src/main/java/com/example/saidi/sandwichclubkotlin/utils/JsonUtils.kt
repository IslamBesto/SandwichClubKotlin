package com.example.saidi.sandwichclubkotlin.utils

import com.example.saidi.sandwichclubkotlin.models.Sandwich
import org.json.JSONException
import org.json.JSONObject

const val KEY_NAME = "name"
const val KEY_MAIN_NAME = "mainName"
const val NO_AVAILABLE_TEXT = "NA"
const val KEY_ALSO_KNOWN_AS = "alsoKnownAs"
const val KEY_PLACE_OF_ORIGIN = "placeOfOrigin"
const val KET_DESCRIPTION = "description"
const val KEY_IMAGE = "image"
const val KEY_INGREDIENTS = "ingredients"

object JsonUtils {

    fun parseSandwichJson(json: String): Sandwich? {
        val sandwich = Sandwich()
        try {
            val sandwichJsonObject = JSONObject(json)
            val sandwichNameJsonObject = sandwichJsonObject.getJSONObject(KEY_NAME)
            val sandwichName = sandwichNameJsonObject.optString(KEY_MAIN_NAME, NO_AVAILABLE_TEXT)
            val alsoKnownAs = sandwichNameJsonObject.getJSONArray(KEY_ALSO_KNOWN_AS)
            val alsoKnownAsList: MutableList<String> = ArrayList()
            for (i in 0 until alsoKnownAs.length()) {
                val alsoKnownAsString = alsoKnownAs.getString(i)
                alsoKnownAsList.add(alsoKnownAsString)
            }

            val placeOfOrigin = sandwichJsonObject.getString(KEY_PLACE_OF_ORIGIN)
            val description = sandwichJsonObject.getString(KET_DESCRIPTION)
            val image = sandwichJsonObject.getString(KEY_IMAGE)

            val ingredients = sandwichJsonObject.getJSONArray(KEY_INGREDIENTS)
            val ingredientsList: MutableList<String> = ArrayList()
            for (i in 0 until ingredients.length()) {
                val ingredientString = ingredients.getString(i)
                ingredientsList.add(ingredientString)
            }

            sandwich.alsoKnwonAs = alsoKnownAsList
            sandwich.description = description
            sandwich.image = image
            sandwich.ingredients = ingredientsList
            sandwich.mainName = sandwichName
            sandwich.placeOfOrigin = placeOfOrigin

        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
        return sandwich
    }

}