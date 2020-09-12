package com.geniuz.gads2020practiceapp

import android.view.View
import com.geniuz.gads2020practiceapp.data.models.HoursRankingItem
import com.geniuz.gads2020practiceapp.data.models.LBData
import com.geniuz.gads2020practiceapp.data.models.SkillIqResponseItem
import com.google.android.material.snackbar.Snackbar
import retrofit2.HttpException
import java.io.IOException

fun HoursRankingItem.toLBData() : LBData{
    return LBData(
        badgeUrl=badgeUrl,
        name = name,
        score = "$hours learning hours, $country."
    )
}

fun SkillIqResponseItem.toLBData() : LBData{
    return LBData(
        badgeUrl=badgeUrl,
        name = name,
        score = "$score skill IQ Score, $country."
    )
}

fun View.showErrorMessage(error: String){

    Snackbar.make(this, error, Snackbar.LENGTH_LONG).show()

}

fun Throwable.resolvedMessage(): String {
    return when(this){
        is HttpException -> "Sorry, a connection error occurred"
        else -> "Sorry, an unexpected error occurred"
    }
}