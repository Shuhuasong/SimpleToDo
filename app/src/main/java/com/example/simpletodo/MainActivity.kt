package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    val listOfTasks = mutableListOf<String>("Go to Laundry", "Walk Dod", "Practivce LeetCode")
    lateinit var adapter : TaskItemAdapter // var ???
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //implement the interface from adapter
        val onLongClickLinear = object : TaskItemAdapter.onLongClickListener {
            override fun onItemLongClicked(position: Int) {
                //Remove the data on the taskList
                listOfTasks.removeAt(position)
                //notify the adapter to update the screen
                adapter.notifyDataSetChanged()
            }

        }
        //1. let's detect when user clicks on the add button
       /* findViewById<Button>(R.id.button).setOnClickListener {
            //excuted when the user clicks on the button
            Log.i("Shuhua", "I am click the buttom")
        } */

        //look up recyclerView in layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // Create adapter passing in the sample user data
        adapter = TaskItemAdapter(listOfTasks, onLongClickLinear)
        // Attach the adapter to the recyclerview to populate the data
        recyclerView.adapter = adapter;
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)


        // Set up the buttom and input field, so that the user can entrer a task and add it to the list
        val inputTextField = findViewById<EditText>(R.id.addTaskField)
        //Get a reference of button
        //and then set an onClickListter
        findViewById<Button>(R.id.button).setOnClickListener {
            //Take the (text) content from user input
            // the 'text' here is a char sequence
            val userInput = inputTextField.text.toString()
            //Add the content into list of tasks: listOfTasks
            listOfTasks.add(userInput)
            //Notify the adapter that bottom data model have modified, need to update them on the screen
            adapter.notifyItemInserted(listOfTasks.size-1)
            //Clear the test field
            inputTextField.setText("")
        }
    }
}

//21:31
//https://courses.codepath.org/snippets/android_university_kotlin/prework

//https://www.youtube.com/watch?v=iJ8jtKqFnJQ&list=PLrT2tZ9JRrf66CxLXpMcX5HTvb5gIYG4D&index=4

//https://guides.codepath.com/android/using-the-recyclerview