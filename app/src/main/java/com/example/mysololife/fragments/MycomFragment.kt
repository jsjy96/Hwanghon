package com.example.mysololife.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.mysololife.board.BoardinsideActivity
import com.example.mysololife.databinding.FragmentCommunityBinding
import com.example.mysololife.databinding.FragmentMycomBinding
import com.example.mysololife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class MycomFragment : Fragment() {

    private lateinit var binding : FragmentMycomBinding

    private val boardDataList = mutableListOf<BoardModel>()

    private val boardKeyList = mutableListOf<String>()

    private val TAG = MycomFragment::class.java.simpleName

    private lateinit var boardLVadapter : BoardListLVAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mycom, container, false)


        binding.writeBtn.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        binding.chattingTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_mycomFragment_to_chattingFragment)
        }

        binding.classTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_mycomFragment_to_classFragment)
        }

        binding.gameTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_mycomFragment_to_gameFragment)
        }

        binding.freecom.setOnClickListener {

            it.findNavController().navigate(R.id.action_mycomFragment_to_communityFragment)
        }

        binding.meetcom.setOnClickListener {

            it.findNavController().navigate(R.id.action_mycomFragment_to_meetcomFragment)
        }


        return binding.root

    }

}