package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FileUtils.writeLines
import java.io.File
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {
   //val = value, var = variable
    var listOfTasks = mutableListOf<String>()
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
                saveItems()
            }

        }
        //1. let's detect when user clicks on the add button
       /* findViewById<Button>(R.id.button).setOnClickListener {
            //excuted when the user clicks on the button
            Log.i("Shuhua", "I am click the buttom")
        } */

        loadItems()

        //look up recyclerView in layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // Create adapter passing in the sample user data
        adapter = TaskItemAdapter(listOfTasks, onLongClickLinear)
        // Attach the adapter to the recyclerview to populate the data
        recyclerView.adapter = adapter;
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)


        // Set up the buttom and input field, so that the user can enter a task and add it to the list
        val inputTextField = findViewById<EditText>(R.id.addTaskField)
        //Get a reference of button
        //and then set an onClickListter
        findViewById<Button>(R.id.button).setOnClickListener {
            //1. Take the (text) content from user input
            // the 'text' here is a char sequence
            val userInput = inputTextField.text.toString()
            //2. Add the content into list of tasks: listOfTasks
            listOfTasks.add(userInput)
            //Notify the adapter that bottom data model have modified, need to update them on the screen
            adapter.notifyItemInserted(listOfTasks.size-1)
            //3. Clear the text field by reset it
            inputTextField.setText("")
            saveItems()
        }
    }

    //Save the data that the user inputted
    //save the data by writing and reading from a file

    //Get the file reference by creating it
    fun getDataFile() : File{
        //every line is represented a specific task in the list of filetasks
        return File(filesDir, "data.txt")
    }

    //create a method to get the data we need
    //load the items by reading every line in the file
    fun loadItems() {
        try{
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException){
            ioException.printStackTrace()
        }

    }
    //Save items by writing them into data file
    fun saveItems(){
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException){
            ioException.printStackTrace()
        }
    }
}

