package com.geniuz.gads2020practiceapp.presentation.submission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geniuz.gads2020practiceapp.Event
import com.geniuz.gads2020practiceapp.data.remote.RetrofitService
import com.geniuz.gads2020practiceapp.resolvedMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

class SubmissionViewModel : ViewModel() {

    private var _state: MutableLiveData<SubmissionState> = MutableLiveData(SubmissionState())
    val state: LiveData<SubmissionState> = _state
    private val retrofitService = RetrofitService


    fun submitForm(
        firstName: String,
        lastName: String,
        email: String,
        submissionLink: String
    ) {

        viewModelScope.launch(Dispatchers.IO) {

            try {

                val submissionCall = retrofitService.submitForm(
                    name = firstName,
                    lastName = lastName,
                    email = email,
                    link = submissionLink
                )


                submissionCall?.enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        _state.postValue(
                            _state.value?.copy(
                                isSubmissionFailed = Event(true),
                                errorMessage = t.resolvedMessage()
                            )
                        )
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {

                        if (response.isSuccessful)

                        _state.postValue(
                            _state.value?.copy(
                                isSubmitted = Event(true)
                            )
                        )

                        else
                            _state.postValue(
                            _state.value?.copy(
                                isSubmissionFailed = Event(true),
                                errorMessage = response.message()
                            )
                        )

                    }

                })



            } catch (e: Exception) {
                Timber.e(e)
                _state.postValue(
                    _state.value?.copy(
                        isSubmissionFailed = Event(true),
                        errorMessage = e.resolvedMessage()
                    )
                )
            }
        }

    }

}

data class SubmissionState(
    val isError: Event<Boolean> = Event(false),
    val errorMessage: String = "",
    val isLoading: Event<Boolean> = Event(false),
    val isSubmitted: Event<Boolean> = Event(false),
    val isSubmissionFailed: Event<Boolean> = Event(false)

)