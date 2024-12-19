package com.theclonebox.ayurgreens.data

import com.theclonebox.ayurgreens.R

data class SearchBarData(
    val name : String,
    val img : Int,
    val description : String
){
    fun doesMatchDearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name,
            description,
            "$name $description",
            "$description $name",
            "${name.first()}",
            "${description.first()}",

        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

val SearchBarDataList = listOf(
    SearchBarData(
        name = "Ayur Greens",
        img = R.drawable.plant1,
        description = "Ayur Greens is a plant that is used for medicinal purposes."
    ),
    SearchBarData(
        name = "Aromatic Greens",
        img = R.drawable.plant1,
        description = "Aromatic Greens is a plant that is used for medicinal purposes."
    ),
    SearchBarData(
        name = "Nutritional Greens",
        img = R.drawable.plant1,
        description = "Nutritional Greens is a plant that is used for medicinal purposes."
    ),
    SearchBarData(
        name = "Dietary greens",
        img = R.drawable.plant1,
        description = "Dietary greens is a plant that is used for medicinal purposes."
    ),
    SearchBarData(
        name = "Tulsi",
        img = R.drawable.plant1,
        description = "It is a herbal plant and also has significance in hindu religion."
    ),
    SearchBarData(
        name = "Mango",
        img = R.drawable.plant1,
        description = "It is sweet and most loved fruit of india, found in summer season."
    ),
    SearchBarData(
        name = "Tea",
        img = R.drawable.plant1,
        description = "It is a plant who leaves are used to make tea."
    ),
    SearchBarData(
        name = "Cocoa",
        img = R.drawable.plant1,
        description = "It is a plant whose fruit is used to make chocolate."
    ),
    SearchBarData(
        name = "Coconut",
        img = R.drawable.plant1,
        description = "It is a fruit packed in hard shell usually found on the shores."
    ),
    SearchBarData(
        name = "Pineapple",
        img = R.drawable.plant1,
        description = "This fruit has no stem is directly grown on ground and has spiky leaves."
    )

)
