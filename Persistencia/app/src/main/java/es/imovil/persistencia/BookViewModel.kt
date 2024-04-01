package es.imovil.persistencia

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class BookViewModel(private val application: Application) : AndroidViewModel(application) {
    var bookList = mutableListOf<Book>()

    fun getListSize(): Int
    {
        return bookList.count()
    }

    fun getBook(position: Int): Book
    {
        return bookList[position]
    }

    fun addBook(book: Book): Boolean {
        // Verificar si el libro ya existe en la lista
        if (bookList.none { it.isbn == book.isbn }) {
            bookList.add(book)
            return true
        } else {
            return false
        }
    }

}