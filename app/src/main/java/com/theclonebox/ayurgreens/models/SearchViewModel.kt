package com.theclonebox.ayurgreens.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theclonebox.ayurgreens.data.SearchBarDataList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SearchViewModel : ViewModel(){

        private val _searchText = MutableStateFlow("")
        val searchText = _searchText.asStateFlow()

        private val _isSearching = MutableStateFlow(false)
        val isSearching = _isSearching.asStateFlow()

        private val _plants = MutableStateFlow(SearchBarDataList)
        val plants = searchText.combine(_plants){
                text, plants ->
                if(text.isEmpty()){
                        plants
                } else {
                        plants.filter {
                                it.doesMatchDearchQuery(text)

                        }
                }
        }.stateIn(viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                _plants.value
        )


        fun onSearchTextChange(text: String){
            _searchText.value = text
                _isSearching.value = text.isNotEmpty()
        }
}