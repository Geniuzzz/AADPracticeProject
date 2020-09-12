package com.geniuz.gads2020practiceapp.presentation.leaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geniuz.gads2020practiceapp.data.models.LeaderBoard

class LeaderBoardViewPagerAdapter(private val leaderBoard: LeaderBoard, fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return LeaderBoardDetailsFragment().apply {
            //add arguments
            arguments = Bundle()
                .also {
                    it.putInt(POSITION_KEY, position)
                    it.putParcelable(LEADER_BOARD, leaderBoard)
                }

        }
    }
}

const val POSITION_KEY = "position"
const val LEADER_BOARD = "leader_b"