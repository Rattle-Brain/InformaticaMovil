package es.imovil.fiestasasturias.adapter

import android.view.ViewGroup

class FiestaAdapter {
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