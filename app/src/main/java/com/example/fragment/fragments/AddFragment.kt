package com.example.fragment.fragments

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.children
import com.example.fragment.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()    // Firestore 인스턴스 선언

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        etPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        btnAdd.setOnClickListener {
            Log.d("jeongmin", "in onActivityCreated -> click btnAdd")
            val data = hashMapOf(
                "name" to etName.text.toString(),
                "phone" to etPhone.text.toString()
            )
            db.collection("fragmentContacts").document(etName.text.toString() + "_" + etPhone.text.toString()) // 이름_번호 로 컬렉션 이름 지정
                .set(data)
                .addOnSuccessListener {
                    // 성공할 경우
                    Toast.makeText(context, "번호 추가", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    // 실패할 경우
                    Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
                }
        }

        // AddFragment 에서 사용하는 번호 저장 함수
        fun saveNum() {

        }
    }
}