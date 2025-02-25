package es.imovil.recyclerviewprac

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.imovil.recyclerviewprac.databinding.ItemLayoutBinding

class RecyclerViewAdapter(private val courses: List<Course>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Obtener el curso Actual
        var curso: Course = courses[position]
        // Colocar el nombre del curso y el profesor en el viewHolder
        holder.bind(curso)
    }

    override fun getItemCount() = courses.size

    class ViewHolder(val itemBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(course: Course) {
            // Inicializar los dos TextView
            itemBinding.profesor.text = course.profesor
            itemBinding.asignatura.text = course.asignatura
        }
    }
}