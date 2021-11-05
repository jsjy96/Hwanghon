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
import com.example.mysololife.MainActivity
import com.example.mysololife.R
import com.example.mysololife.board.BoardListLVAdaptor
import com.example.mysololife.board.BoardModel
import com.example.mysololife.board.BoardWriteActivity
import com.example.mysololife.board.BoardinsideActivity
import com.example.mysololife.contentsList.ContentListActivity
import com.example.mysololife.contentsList.ContentModel
import com.example.mysololife.databinding.FragmentCommunityBinding
import com.example.mysololife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class CommunityFragment : Fragment() {

    private lateinit var binding : FragmentCommunityBinding

    private val boardDataList = mutableListOf<BoardModel>()

    private val boardKeyList = mutableListOf<String>()

    private val TAG = CommunityFragment::class.java.simpleName

    private lateinit var boardLVadapter : BoardListLVAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false)

//        binding.category1.setOnClickListener {
//
//            val intent = Intent(context, ContentListActivity::class.java)
//            intent.putExtra("catagory", "catagory1")
//            startActivity(intent)
//
//        }
//
//        binding.category2.setOnClickListener {
//            val intent = Intent(context, ContentListActivity::class.java)
//            intent.putExtra("catagory", "catagory2")
//            startActivity(intent)
//        }

//        val boardList = mutableListOf<BoardModel>()
//        boardList.add(BoardModel("a","b","c","d"))

        boardLVadapter = BoardListLVAdaptor(boardDataList)
        binding.boardListView.adapter = boardLVadapter

        binding.boardListView.setOnItemClickListener { parent, view, position, id ->

            //첫번째 방법은 listview에 있는 데이터 다 다른 액티비티로 전달해서 만들기
//            val intent = Intent(context, BoardinsideActivity::class.java)
//            intent.putExtra("title", boardDataList[position].title)
//            intent.putExtra("content", boardDataList[position].content)
//            intent.putExtra("time", boardDataList[position].time)
//            startActivity(intent)

            //두번째 방법은 Firebase에 있는 board에 대한 데이터의 id를 기반으로 다시 데이터를 받아오는 방법
            val intent = Intent(context, BoardinsideActivity::class.java)
            intent.putExtra("key", boardKeyList[position])
            startActivity(intent)

        }
        binding.writeBtn.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        binding.chattingTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_communityFragment_to_chattingFragment)
        }

        binding.classTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_communityFragment_to_classFragment)
        }

        binding.gameTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_communityFragment_to_gameFragment)
        }

        binding.meetcom.setOnClickListener {

            it.findNavController().navigate(R.id.action_communityFragment_to_meetcomFragment)
        }

        binding.mycom.setOnClickListener {

            it.findNavController().navigate(R.id.action_communityFragment_to_mycomFragment)
        }

        getFBBoardData()

        return binding.root

    }

    private fun getFBBoardData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                //중복돼서 나올 때 초기화
                boardDataList.clear()

                for (dataModel in dataSnapshot.children) {

                    Log.d(TAG, dataModel.toString())


                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())

                }

                boardKeyList.reverse()
                boardDataList.reverse()
                boardLVadapter.notifyDataSetChanged()

                Log.d(TAG, boardDataList.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)

    }

}