package com.theclonebox.ayurgreens.data

import com.theclonebox.ayurgreens.R

data class Plant(val id: String, val name: String, val imageResourceId: Int)

val plantsMainScreenList = listOf(
    Plant("1","Peperomia Houseplant", R.drawable.plant1),
    Plant("2","Crassula Houseplant", R.drawable.plant3),
    Plant("3","Peperomia Houseplant", R.drawable.plant2),
    Plant("4","Crassula Houseplant", R.drawable.plant3),
    Plant("5","Asplenium Nidus", R.drawable.plant4),
    Plant("6","Asplenium Nidus", R.drawable.plant1),
    Plant("7","Asplenium Nidus", R.drawable.plant2),
    Plant("8","Asplenium Nidus", R.drawable.plant3),
    Plant("9","Asplenium Nidus", R.drawable.plant4),
    Plant("10","Asplenium Nidus", R.drawable.plant1),

)
