package com.geniuz.gads2020practiceapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LBData (
    val badgeUrl: String,
    val name: String,
    val score: String
): Parcelable