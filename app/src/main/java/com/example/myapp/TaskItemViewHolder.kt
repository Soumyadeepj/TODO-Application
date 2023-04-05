package com.example.myapp

import android.content.Context
import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,

    private val clickListener: TaskItemClickListener // we have created as kotlin class
    ):RecyclerView.ViewHolder(binding.root)
{
        @RequiresApi(Build.VERSION_CODES.O)
        private val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        @RequiresApi(Build.VERSION_CODES.O)

        //delete edit added listener: OnDeleteClickListener
        fun bindTaskItem(taskItem: TaskItem)
        {
             binding.name.text = taskItem.name
             //binding.dueTime.text = taskItem.dueTime
            //will cut the name and time when completed
            if (taskItem.isCompleted()){
                binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            //adding complete button
            binding.completeButton.setImageResource(taskItem.imageResource())
            binding.completeButton.setColorFilter(taskItem.imageColor(context))

            binding.completeButton.setOnClickListener{
                clickListener.completeTaskItem(taskItem) //completeTaskItem from taskitemclicklistner
            }
            // adding delete button
            binding.deleteButton.setOnClickListener{
                clickListener.deleteTaskItem(taskItem)
            }
            //edit button
            binding.taskCallContainer.setOnClickListener{
                clickListener.editTaskItem(taskItem) //editTaskItem from taskitemclicklistner
            }



            if(taskItem.dueTime !=null)
                binding.dueTime.text = timeFormat.format(taskItem.dueTime)
                //binding.dueTime.text=String.format("%02d:%02d",taskItem.dueTime!!.hour,taskItem.dueTime!!.minute)
            else
                binding.dueTime.text=""
        }
}