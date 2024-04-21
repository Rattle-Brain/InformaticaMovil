package es.imovil.arquitectura.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.imovil.arquitectura.databinding.ListItemBinding
import es.imovil.arquitectura.adapters.CourseListAdapter.CourseViewHolder
import es.imovil.arquitectura.utils.Utils

class CourseListAdapter(private val onNameSelected: (String) -> Unit) : ListAdapter<String, CourseViewHolder>(
    Utils.DIFF_CALLBACK)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemBinding) { onNameSelected(getItem(it)) }
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val nombre = this.getItem(position)
        holder.bind(nombre)
    }

    class CourseViewHolder(private val itemBinding: ListItemBinding, private val onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(itemBinding.root)
    {
        init {
            itemBinding.root.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(nombre: String) {
            itemBinding.courseName.text = nombre
        }
    }
}