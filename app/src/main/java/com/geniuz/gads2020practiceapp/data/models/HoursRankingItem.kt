package com.geniuz.gads2020practiceapp.data.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class HoursRankingItem(
    val badgeUrl: String,
    val country: String,
    val hours: Int,
    val name: String
): Parcelable