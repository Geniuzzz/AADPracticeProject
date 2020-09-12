package com.geniuz.gads2020practiceapp.presentation.leaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geniuz.gads2020practiceapp.R
import com.geniuz.gads2020practiceapp.data.models.LBData
import com.geniuz.gads2020practiceapp.data.models.LeaderBoard
import com.geniuz.gads2020practiceapp.showErrorMessage
import com.geniuz.gads2020practiceapp.toLBData
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_leader_board_details.*
import kotlinx.android.synthetic.main.fragment_leaderboard.*

class LeaderBoardFragment : Fragment() {
    private val viewModel: LeaderBoardDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            state.run {
                if (isFetchedHoursLeaderBoard.latestValue() &&  isFetchedSkillsLeaderBoard.latestValue())
                {
                    leaderboard_progressbar.visibility = GONE
                    setupViewPager(
                        LeaderBoard(
                            skills = skillIqResponseItem,
                            hours = hoursRankingItem
                        )
                    )
                }

                isError.getValueIfFirstTme()?.takeIf { it }?.let {
                    requireView().showErrorMessage(errorMessage)
                }
            }
        })
        viewModel.fetchLeaderBoard()
        viewModel.fetchHoursBoard()

        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit_navigation_btn.setOnClickListener {
            findNavController()
                .navigate(
                    LeaderBoardFragmentDirections.actionLeaderboardFragmentToSubmissionFragment()
                )
        }

    }

    
    private fun setupViewPager(leaderBoard: LeaderBoard){
        leader_board_viewpager.adapter = LeaderBoardViewPagerAdapter( leaderBoard,this)
        TabLayoutMediator(tab_layout, leader_board_viewpager){tab, position ->
            val title = resources.getStringArray(R.array.leader_board_type)[position]
            tab.text = title
        }.attach()
    }
}