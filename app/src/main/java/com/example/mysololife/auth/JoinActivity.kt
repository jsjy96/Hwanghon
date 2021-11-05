package com.example.mysololife.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mysololife.MainActivity
import com.example.mysololife.R
import com.example.mysololife.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        binding.passwordArea2.setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent ->
            // enter키
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                // 키보드 내리기
                val imm: InputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.passwordArea2.windowToken, 0)
                // register
                register()

                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        binding.joinBtn.setOnClickListener {
            register()
        }
    }

    private fun register() {


        var isGoToJoin = true

        val email = binding.emailArea.text.toString()
        val password1 = binding.passwordArea1.text.toString()
        val password2 = binding.passwordArea2.text.toString()
        val emailRegex = """^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z\d]+$""".toRegex()

        //값이 비워져있는지 확인
        if(email.isEmpty()) {
            Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show()
            isGoToJoin = false
        }
        if(emailRegex.matchEntire(email) == null) {
            Toast.makeText(this, "이메일 안됨", Toast.LENGTH_LONG).show()
            isGoToJoin = false
        }
        if(password1.isEmpty()) {
            Toast.makeText(this, "Password1을 입력해주세요", Toast.LENGTH_LONG).show()
            isGoToJoin = false
        }
        if(password2.isEmpty()) {
            Toast.makeText(this, "Password2을 입력해주세요", Toast.LENGTH_LONG).show()
            isGoToJoin = false
        }
        //비밀번호가 같은지 확인
        if(!password1.equals(password2)) {
            Toast.makeText(this, "비밀번호를 똑같이 입력해주세요", Toast.LENGTH_LONG).show()
            isGoToJoin = false
        }
        //비밀번호가 6자 이상인지
        if(password1.length < 6) {
            Toast.makeText(this, "비밀번호를 6자리 이상으로 입력해주세요", Toast.LENGTH_LONG).show()
            isGoToJoin = false
        }
        if(isGoToJoin) {
            auth.createUserWithEmailAndPassword("$email@test.com", password1)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()

                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    }
                }
                .addOnFailureListener {
                    val error = it.toString().split(": ")[0].split('.').last()
                    if(error == "FirebaseAuthUserCollisionException")
                        Toast.makeText(this, "별명이 중복됩니다.", Toast.LENGTH_LONG).show()
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                }
        }
    }
}