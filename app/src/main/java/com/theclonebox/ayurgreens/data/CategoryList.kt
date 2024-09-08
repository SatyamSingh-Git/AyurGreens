package com.theclonebox.ayurgreens.data

data class CategoryList(val name: String)

val plantCategoryList = listOf("Medicinal", "Aromatic", "Spices", "Nutritional", "Adaptogenic")
val plantCategoryDataList = plantCategoryList.map { CategoryList(it) }