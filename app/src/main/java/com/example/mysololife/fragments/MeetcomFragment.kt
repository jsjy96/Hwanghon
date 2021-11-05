package com.example.mysololife.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.mysololife.R
import com.example.mysololife.board.BoardListLVAdaptor
import com.example.mysololife.board.BoardModel
import com.example.mysololife.board.BoardWriteActivity
import com.example.mysololife.databinding.FragmentMeetcomBinding


class MeetcomFragment : Fragment() {

    private lateinit var binding : FragmentMeetcomBinding

    private val boardDataList = mutableListOf<BoardModel>()

    private val boardKeyList = mutableListOf<String>()

    private val TAG = MeetcomFragment::class.java.simpleName

    private lateinit var boardLVadapter : BoardListLVAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meetcom, container, false)


        binding.writeBtn.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        binding.chattingTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_meetcomFragment_to_chattingFragment)
        }

        binding.classTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_meetcomFragment_to_classFragment)
        }

        binding.gameTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_meetcomFragment_to_gameFragment)
        }

        binding.freecom.setOnClickListener {

            it.findNavController().navigate(R.id.action_meetcomFragment_to_communityFragment)
        }

        binding.mycom.setOnClickListener {

            it.findNavController().navigate(R.id.action_meetcomFragment_to_mycomFragment)
        }


        return binding.root

    }


}