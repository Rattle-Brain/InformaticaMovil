package es.imovil.arquitectura.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.imovil.arquitectura.CourseApp
import es.imovil.arquitectura.databinding.ListItemBinding
import es.imovil.arquitectura.adapters.CourseListAdapter.CourseViewHolder

class CourseListAdapter : ListAdapter<String, CourseViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemViewBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val courseName = getItem(position)
        holder.bind(courseName)
    }

    companion object DIFF_CALLBACK: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.equals(newItem)
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    class CourseViewHolder(private val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(name: String) {
            val course = CourseApp().repository.getCourseByName(name).asLiveData().value!!
            with(itemBinding)
            {
                courseName.text = name
                teacherName.text = course.teacher
                description.text = course.description
            }
        }
    }
}