package es.imovil.arquitectura.utils

import androidx.recyclerview.widget.DiffUtil

class Utils {
    companion object DIFF_CALLBACK: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}