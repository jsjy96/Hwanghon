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
import com.example.mysololife.board.BoardWriteActivity
import com.example.mysololife.databinding.FragmentClassBinding


class ClassFragment : Fragment() {

    private lateinit var binding : FragmentClassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_class, container, false)



        binding.chattingTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_classFragment_to_chattingFragment)
        }

        binding.communityTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_classFragment_to_communityFragment)
        }

        binding.gameTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_classFragment_to_gameFragment)
        }



        return binding.root// Inflate the layout for this fragment

    }


}