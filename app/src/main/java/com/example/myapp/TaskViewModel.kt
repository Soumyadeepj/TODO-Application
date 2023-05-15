package com.example.myapp
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class TaskViewModel(private val repository: TaskItemRepository):ViewModel() {
    var taskItems: LiveData<List<TaskItem>?> = repository.allTaskItems.asLiveData()

    fun addTaskItem(newTask: TaskItem) = viewModelScope.launch {
        repository.insertTaskItem(newTask)
    }

    fun updateTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        repository.updateTaskItem(taskItem)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCompleted(taskItem: TaskItem) = viewModelScope.launch {
        if (!taskItem.isCompleted())
            taskItem.completedDateString = TaskItem.dateFormatter.format(LocalDate.now())
        repository.updateTaskItem(taskItem)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteTaskItem(taskItem:TaskItem)= viewModelScope.launch {
        repository.deleteTaskItem(taskItem)
    }

  }
    class TaskItemModelFactory(private val repository: TaskItemRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TaskViewModel::class.java))
                return TaskViewModel(repository) as T

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
//    init{
//        taskItems.value= mutableListOf()
//    }
//    fun addTaskItem(newTask:TaskItem)
//    {
//        val list =taskItems.value
//        list!!.add(newTask)
//        taskItems.postValue(list)
//    }
//
//    fun updateTaskItem(id: UUID,name:String,desc:String,duetime:LocalTime?,completedDate:LocalDate?) {
//        val list = taskItems.value
//        val task = list!!.find { it.id == id }!!
//        task.name = name
//        task.desc = desc
//        task.dueTime = duetime
//        taskItems.postValue(list)
//    }
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun setComplete(taskItem:TaskItem)
//    {
//        val list =taskItems.value
//        val task = list!!.find { it.id == taskItem.id}!!
//        if(task.completedDateString ==null)
//            task.completedDateString = LocalDate.now()
//
//        taskItems.postValue(list)
//    }
//    @RequiresApi(Build.VERSION_CODES.N)
//    fun deleteTaskItem(taskItem:TaskItem) {
//        val list = taskItems.value
//        list!!.removeIf { it.id ==taskItem.id }
//        taskItems.postValue(list)
//    }


