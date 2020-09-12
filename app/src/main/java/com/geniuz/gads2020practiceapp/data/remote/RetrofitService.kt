package com.geniuz.gads2020practiceapp.data.remote

import com.geniuz.gads2020practiceapp.data.ApiResult
import com.geniuz.gads2020practiceapp.data.models.HoursRankingItem
import com.geniuz.gads2020practiceapp.data.models.SkillIqResponseItem
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object RetrofitService {

    private val okHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }.build()

    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(
            GsonConverterFactory.create(
               GsonBuilder()
                   .setLenient()
                   .create()
            )
        )
        .client(okHttpClient)

    private val apiService = retrofit
        .baseUrl("https://gadsapi.herokuapp.com")
        .build()
        .create(ApiService::class.java)


    private val submissionApiService = retrofit
        .baseUrl("https://docs.google.com/forms/d/e/")
        .build()
        .create(SubmissionApiService::class.java)


    suspend fun fetchSkillRanking(): ApiResult<List<SkillIqResponseItem>> {
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
            Timber.e(e)

            ApiResult.error(
                "",
                null
            )
        }

    }

    suspend fun fetchHourRanking(): ApiResult<List<HoursRankingItem>> {

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
            Timber.e(e)

            ApiResult.error(
                "",
                null
            )
        }
    }


    fun submitForm(name: String, lastName: String, email: String, link : String): Call<Void>? {
        return try {
                submissionApiService.submitForm(
                name= name,
                lastName = lastName,
                email = email,
                link = link
            )
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }

}