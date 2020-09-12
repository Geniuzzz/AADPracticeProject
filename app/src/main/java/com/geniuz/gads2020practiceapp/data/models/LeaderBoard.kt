package com.geniuz.gads2020practiceapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data  class LeaderBoard (
    val skills: List<SkillIqResponseItem>,
    val hours: List<HoursRankingItem>
): Parcelable