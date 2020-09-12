package com.geniuz.gads2020practiceapp.presentation.leaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.geniuz.gads2020practiceapp.R
import java.util.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.geniuz.gads2020practiceapp.data.models.LBData
import com.geniuz.gads2020practiceapp.data.models.LeaderBoard
import com.geniuz.gads2020practiceapp.toLBData
import kotlinx.android.synthetic.main.fragment_leader_board_details.*
import timber.log.Timber


class LeaderBoardDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leader_board_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            val position = bundle.get(POSITION_KEY) as Int
            val leaderBoard = bundle.get(LEADER_BOARD) as LeaderBoard

            Timber.d("XXX POSITION $position")

           val data =  if (position == 0){
                leaderBoard.hours.map { it.toLBData() }
            } else
               leaderBoard.skills.map { it.toLBData() }

            with(leader_board_recycler){
                        adapter = LeaderBoardRecyclerAdapter(data)
                        layoutManager = LinearLayoutManager(requireContext())
                    }


//            viewModel.state.observe(viewLifecycleOwner, Observer { state ->
//                state.run {
//
//                    val data: List<LBData> =
//                        isFetchedHoursLeaderBoard.latestValue().takeIf { it && position == 0 }
//                            ?.let {
//                                hoursRankingItem.map {
//                                    it.toLBData()
//                                }
//                            }
//                            ?: isFetchedSkillsLeaderBoard.latestValue()
//                                .takeIf { it && position == 1 }?.let {
//                                skillIqResponseItem.map {
//                                    it.toLBData()
//                                }
//                            } ?: emptyList()
//
//                    with(leader_board_recycler){
//                        adapter = LeaderBoardRecyclerAdapter(data)
//                        layoutManager = LinearLayoutManager(requireContext())
//                    }
//
//                }
//            })
        }
    }

}