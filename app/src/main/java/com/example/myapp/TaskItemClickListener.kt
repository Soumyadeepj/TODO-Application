package com.example.myapp
//has only two basic function on complete and edit
interface TaskItemClickListener {
    fun editTaskItem(taskItem: TaskItem)
    fun completeTaskItem(taskItem: TaskItem)
    fun deleteTaskItem(taskItem: TaskItem)
}