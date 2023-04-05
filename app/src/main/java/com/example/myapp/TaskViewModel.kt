package com.example.myapp
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class TaskViewModel:ViewModel()
{   var taskItems = MutableLiveData<MutableList<TaskItem>>()

    init{
        taskItems.value= mutableListOf()
    }
    fun addTaskItem(newTask:TaskItem)
    {
        val list =taskItems.value
        list!!.add(newTask)
        taskItems.postValue(list)
    }

    fun updateTaskItem(id: UUID,name:String,desc:String,duetime:LocalTime?,completedDate:LocalDate?) {
        val list = taskItems.value
        val task = list!!.find { it.id == id }!!
        task.name = name
        task.desc = desc
        task.dueTime = duetime
        taskItems.postValue(list)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun setComplete(taskItem:TaskItem)
    {
        val list =taskItems.value
        val task = list!!.find { it.id == taskItem.id}!!
        if(task.completedDate==null)
            task.completedDate = LocalDate.now()

        taskItems.postValue(list)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteTaskItem(taskItem:TaskItem) {
        val list = taskItems.value
        list!!.removeIf { it.id ==taskItem.id }
        taskItems.postValue(list)
    }
}