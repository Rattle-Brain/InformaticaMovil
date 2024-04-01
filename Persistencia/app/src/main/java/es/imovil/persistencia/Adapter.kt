package es.imovil.persistencia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.imovil.practicapersistencia.R
import es.imovil.practicapersistencia.databinding.ItemViewBinding

class Adapter (private val books: List<Book>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var b: Book = books[position]
        holder.bind(b)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    class ViewHolder(val itemViewBinding: ItemViewBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(book: Book) {
            with(itemViewBinding) {
                itemViewBinding.author.text = book.author
                itemViewBinding.isbn.text = book.isbn
                itemViewBinding.editor.text = book.editorial
                itemViewBinding.title.text = book.title
                itemViewBinding.price.text = book.price.toString()
            }
        }
    }
}