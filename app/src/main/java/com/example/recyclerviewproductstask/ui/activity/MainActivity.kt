package com.example.recyclerviewproductstask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewproductstask.R
import com.example.recyclerviewproductstask.databinding.ActivityMainBinding
import com.example.recyclerviewproductstask.ui.fragment.productsList.ProductListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = ProductListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}