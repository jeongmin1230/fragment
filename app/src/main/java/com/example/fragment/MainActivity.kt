package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragment.fragments.AddFragment
import com.example.fragment.fragments.HomeFragment
import com.example.fragment.fragments.ListFragment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_list.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationAction()
    }
/* 일반 함수 */
    // bottomNavigation 함수
    private fun bottomNavigationAction() {
        bottomnavigation.run { setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    val homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.flMain, homeFragment).commit()
                }
                R.id.add -> {
                    val addFragment = AddFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.flMain, addFragment).commit()
                }
                R.id.list -> {
                    val listFragment = ListFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.flMain, listFragment).commit()
                }
            }
            true
        }
        selectedItemId = R.id.home
        }
    }
}