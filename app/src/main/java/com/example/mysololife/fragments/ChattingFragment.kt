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
import com.example.mysololife.auth.IntroActivity
import com.example.mysololife.databinding.FragmentChattingBinding
import com.example.mysololife.utils.FBAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChattingFragment : Fragment() {

    private  lateinit var binding : FragmentChattingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("HomeFragment", "OnCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chatting, container, false)

        binding.communityTap.setOnClickListener {

            Log.d("HomeFragment", "tipTap")
            it.findNavController().navigate(R.id.action_chattingFragment_to_communityFragment)
        }

        binding.classTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_chattingFragment_to_classFragment)
        }

        binding.gameTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_chattingFragment_to_gameFragment)
        }



//        binding.logoutBtn.setOnClickListener {
//            FBAuth.getAuth().signOut()
//            val intent = Intent(context, IntroActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//
//        }



        return binding.root
    }


}