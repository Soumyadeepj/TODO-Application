package com.example.myapp
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),TaskItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val taskViewModel: TaskViewModel by viewModels{
        TaskItemModelFactory((application as TodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // taskViewModel=ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.newtaskbutton.setOnClickListener {
            NewTaskSheet(null).show(supportFragmentManager,"newTaskTag")
        }
        setRecyclerView()
    }
    private fun setRecyclerView()
    {
        val mainActivity = this
        taskViewModel.taskItems.observe(this) {
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter =TaskItemAdapter(it,mainActivity)
            }
        }


    }
    //edit button
    override fun editTaskItem(taskItem: TaskItem)
    {
      NewTaskSheet(taskItem).show(supportFragmentManager,"newTaskTag")
    }
    //complete button
    @RequiresApi(Build.VERSION_CODES.O)
    override fun completeTaskItem(taskItem: TaskItem) {
        taskViewModel.setCompleted(taskItem)
    }
    //adding delete button
    @RequiresApi(Build.VERSION_CODES.N)
    override fun deleteTaskItem(taskItem: TaskItem) {
        taskViewModel.deleteTaskItem(taskItem)
    }
}
