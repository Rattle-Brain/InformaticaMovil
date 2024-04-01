package es.imovil.persistencia

import androidx.recyclerview.widget.DiffUtil

data class Book(var title:String,
                var author:String,
                var isbn:String? = null,
                var editorial:String? = null,
                var price:Float) {

    object DIFF_CALLBACK: DiffUtil.ItemCallback<Book>() {

        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.isbn == newItem.isbn
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            var sameAuthor: Boolean = oldItem.author == newItem.author
            var sameTitle: Boolean = oldItem.title == newItem.title
            var sameEditorial: Boolean =oldItem.editorial == newItem.editorial

            return sameAuthor && sameTitle && sameEditorial
        }

    }
}