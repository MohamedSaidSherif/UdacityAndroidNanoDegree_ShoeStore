package com.udacity.shoestore.models

import android.os.Parcelable
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Shoe(
    var name: String,
    var size: Double,
    var company: String,
    var description: String,
    val images: List<String> = mutableListOf()
) : Parcelable, BaseObservable()

@BindingAdapter("app:text")
fun setSizeText(appCompatTextView: AppCompatTextView, size: Double) {
    appCompatTextView.text = size.toString()
}