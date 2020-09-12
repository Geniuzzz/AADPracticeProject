package com.geniuz.gads2020practiceapp.presentation.leaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geniuz.gads2020practiceapp.Event
import com.geniuz.gads2020practiceapp.data.Error
import com.geniuz.gads2020practiceapp.data.Success
import com.geniuz.gads2020practiceapp.data.models.HoursRankingItem
import com.geniuz.gads2020practiceapp.data.models.SkillIqResponseItem
import com.geniuz.gads2020practiceapp.data.remote.RetrofitService
import com.geniuz.gads2020practiceapp.resolvedMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class LeaderBoardDetailsViewModel : ViewModel() {

    private var _state : MutableLiveData<LBState> = MutableLiveData(LBState())
    val state : LiveData<LBState> = _state
    private val retrofitService = RetrofitService


    fun fetchLeaderBoard()  {

        viewModelScope.launch(Dispatchers.IO) {
            try {

                when (val result = retrofitService.fetchSkillRanking()){
                    is Error -> {
                        Timber.e(result.errorMessage)
                        _state.postValue(
                            _state.value?.copy(
                                isError = Event(true),
                                errorMessage = result.errorMessage
                            )
                        )
                    }
                    is Success -> {
                        _state.postValue(
                            _state.value?.copy(
                                isFetchedSkillsLeaderBoard = Event(true),
                                skillIqResponseItem = result.data
                            )
                        )
                    }
                }
            }catch (e: Exception){
                Timber.e(e)
                _state.postValue(
                    _state.value?.copy(
                        isError = Event(true),
                        errorMessage = e.resolvedMessage()
                    )
                )
            }
        }
    }

    fun fetchHoursBoard()  {

        viewModelScope.launch(Dispatchers.IO) {
            try {

                when (val result = retrofitService.fetchHourRanking()){
                    is Error -> {
                        Timber.e(result.errorMessage)
                        _state.postValue(
                            _state.value?.copy(
                                isError = Event(true),
                                errorMessage = result.errorMessage
                            )
                        )
                    }
                    is Success -> {
                        _state.postValue(
                            _state.value?.copy(
                                isFetchedHoursLeaderBoard = Event(true),
                                hoursRankingItem = result.data
                            )
                        )
                    }
                }
            }catch (e: Exception){
                Timber.e(e)
                _state.postValue(
                    _state.value?.copy(
                        isError = Event(true),
                        errorMessage = e.resolvedMessage()
                    )
                )
            }
        }
    }
}

data class LBState(
    val isError: Event<Boolean> = Event(false),
    val errorMessage: String = "",
    val isLoading : Event<Boolean> = Event(false),
    val isFetchedHoursLeaderBoard : Event<Boolean> = Event(false),
    val hoursRankingItem: List<HoursRankingItem> = emptyList(),
    val isFetchedSkillsLeaderBoard : Event<Boolean> = Event(false),
    val skillIqResponseItem: List<SkillIqResponseItem> = emptyList()

)
