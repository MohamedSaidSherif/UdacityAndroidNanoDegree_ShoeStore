package com.udacity.shoestore.models

import android.os.Parcelable
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.google.android.material.textfield.TextInputEditText
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

@BindingAdapter("app:text")
fun setSizeText(textInputEditText: TextInputEditText, size: Double) {
    textInputEditText.setText(size.toString())
}

@InverseBindingAdapter(attribute = "app:text", event = "android:textAttrChanged")
fun getSizeText(textInputEditText: TextInputEditText): Double{
    return textInputEditText.text.toString().toDouble()
}

//@BindingAdapter("app:text")
//fun getSizeText(size: String): Double {
////    appCompatTextView.text = size.toString()
//    return size.toDouble()
//}