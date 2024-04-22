package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RestrictTo
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.db.MyDatabase
import com.example.myapplication.db.StringDao
import com.example.myapplication.db.StringEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var adapter: Adapter
    private lateinit var viewModel: MainViewModel
    private lateinit var db: MyDatabase
    private lateinit var stringDao: StringDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        db = Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java, "string-database"
        ).build()
        stringDao = db.stringDao()

        viewModel = MainViewModel(stringDao)

        adapter = Adapter { model ->
            deleteItem(model)
        }
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter


        showList()
        saveItem()
    }

    private fun deleteItem(model: StringEntity) {
        viewModel.deleteItem(model)
    }

    private fun saveItem() {
        binding.buttonSave.setOnClickListener {
            val string = binding.et.text.toString().trim()
            if (string.isNotEmpty()) viewModel.saveItem(string)
            binding.et.text.clear()
        }
    }


    private fun showList() {
        binding.buttonShow.setOnClickListener {
            viewModel.getList()
            viewModel.showList.observe(this) {
                adapter.submitList(it)
            }
        }
    }

}