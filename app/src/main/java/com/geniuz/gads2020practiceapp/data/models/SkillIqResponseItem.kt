package com.geniuz.gads2020practiceapp.data.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class SkillIqResponseItem(
    val badgeUrl: String,
    val country: String,
    val name: String,
    val score: Int
): Parcelable