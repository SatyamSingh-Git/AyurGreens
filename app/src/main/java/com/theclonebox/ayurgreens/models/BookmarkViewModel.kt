package com.theclonebox.ayurgreens.models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.theclonebox.ayurgreens.data.EachPlantDescriptionData

class BookmarkViewModel : ViewModel()  {
    val bookmarkedPlants = mutableStateListOf<EachPlantDescriptionData>()

    fun addBookmark(plant : EachPlantDescriptionData){

        if (!bookmarkedPlants.contains(plant)){
            if (bookmarkedPlants.size >=3){
                bookmarkedPlants.removeAt(0)
            }
            bookmarkedPlants.add(plant)
        }
    }
}