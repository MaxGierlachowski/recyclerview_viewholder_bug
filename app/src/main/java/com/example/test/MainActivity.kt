package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test.databinding.ActivityMainBinding
import com.example.test.recyclerview.Type1
import com.example.test.recyclerview.Type2
import com.example.test.recyclerview.TypedRecyclerViewItem
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong

class MainActivity : AppCompatActivity() {

    private val nextId = AtomicLong(0)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            addMessages(Type1.State(nextId.getAndIncrement(), "Hey"), 2000L)
            addMessages(Type1.State(nextId.getAndIncrement(), "Hey2"), 3000L)
        }
    }

    private var messages = mutableListOf<TypedRecyclerViewItem>()

    private suspend fun addMessages(message: TypedRecyclerViewItem, delay: Long) {
        messages.add(Type2.State(nextId.getAndIncrement(), "Test"))
        updateMessages()
        delay(delay)
        messages = messages.filter { message -> message.getItemType() == 1 }.toMutableList()
        updateMessages()
        delay(800)
        messages.add(message)
        updateMessages()
        delay(800)
    }

    private fun updateMessages() {
        Log.e("MESSAGES", "------------------ NEW MESSAGES ------------------")
        messages = messages.mapIndexed { index, typedRecyclerViewItem ->
            typedRecyclerViewItem.getCopy(index == messages.size - 1)
        }.toMutableList()
        binding.list.setItems(messages)
    }

}