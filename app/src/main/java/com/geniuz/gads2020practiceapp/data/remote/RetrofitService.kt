package com.geniuz.gads2020practiceapp.data.remote

import com.geniuz.gads2020practiceapp.data.ApiResult
import com.geniuz.gads2020practiceapp.data.models.HoursRankingResponse
import com.geniuz.gads2020practiceapp.data.models.SkillIqRankingResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitService {

    private val retrofit = Retrofit
        .Builder()
        .baseUrl("https://gadsapi.herokuapp.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)


    suspend fun fetchSkillRanking(): ApiResult<SkillIqRankingResponse> {
        return try {
            val response = apiService.skillIqRanking()

            if (response.isSuccessful) {

                response.body()?.let { responseBody ->
                    ApiResult.success(
                        data = responseBody
                    )
                } ?: throw Exception()

            } else ApiResult.error(
                "",
                null
            )
        } catch (e: Exception) {

            ApiResult.error(
                "",
                null
            )
        }

    }

    suspend fun fetchHourRanking(): ApiResult<HoursRankingResponse> {

        return try {
            val response = apiService.hoursRanking()

            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    ApiResult.success(
                        data = responseBody
                    )
                } ?: throw Exception()

            } else ApiResult.error(
                "",
                null
            )
        } catch (e: Exception) {

            ApiResult.error(
                "",
                null
            )
        }
    }

}