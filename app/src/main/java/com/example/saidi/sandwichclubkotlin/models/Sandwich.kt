package com.example.saidi.sandwichclubkotlin.models

data class Sandwich(var mainName: String? = null,
                    var alsoKnwonAs: MutableList<String>? = null,
                    var placeOfOrigin: String? = null,
                    var description: String? = null,
                    var image: String? = null,
                    var ingredients: MutableList<String>? = null)

