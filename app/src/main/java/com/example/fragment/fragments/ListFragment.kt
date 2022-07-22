package com.example.fragment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragment.ListAdapter
import com.example.fragment.ListLayout
import com.example.fragment.MainActivity
import com.example.fragment.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()    // Firestore 인스턴스 선언

    private val itemList = arrayListOf<ListLayout>()    // 리스트 아이템 배열
    private val adapter = ListAdapter(itemList)         // 리사이클러 뷰 어댑터

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvList.adapter = adapter

        db.collection("fragmentContacts")
            .get()
            .addOnSuccessListener { result ->
                // 성공할 경우
                itemList.clear()
                for(document in result) { // 가져온 문서들은 result 에 들어감
                    val item = ListLayout(document["name"] as String, document["phone"] as String)
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged() // 리사이클러 뷰 갱신
            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.d("jeongmin", "Error getting documents : $exception")
            }
    }

}