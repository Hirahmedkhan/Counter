package com.example.counter.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelValues(val num: Int) : Parcelable