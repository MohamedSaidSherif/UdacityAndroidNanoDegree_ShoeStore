package com.udacity.shoestore.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainActivityViewModel : ViewModel() {

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    init {
        initList()
    }

    fun addShoe(
        name: String,
        size: Double,
        company: String,
        description: String
    ) {
        _shoes.value = _shoes.value?.plus(Shoe(name, size, company, description))
    }

    private fun initList() {
        _shoes.value = listOf()
        for (i in 2 downTo 1) {
            addShoe(
                name = "Shoe #$i",
                size = (i + 30).toDouble(),
                company = "Company #$i",
                description = "This is the shoe #$i in the list"
            )
        }
    }
}