package com.tencalculus.bibliogram

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tencalculus.bibliogram.databinding.BooksListItemBinding
import com.tencalculus.bibliogram.models.BooksData

class BooksAdapter(val clickListener: BooksClickListener): RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    var data = listOf<BooksData>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val currentBook = data[position]
        holder.bind(currentBook, clickListener)
    }


    class BooksViewHolder(binding: BooksListItemBinding): RecyclerView.ViewHolder(binding.root){
        val thumbnailImage = binding.thumbnailImage
        val titleText = binding.titleText
        val pagesText = binding.pagesText
        val languageText = binding.languageText
        val previewButton = binding.previewButton

        fun bind(currentBook: BooksData, clickListener: BooksClickListener) {
            languageText.text = currentBook.language
            pagesText.text = currentBook.pageCount
            titleText.text = currentBook.shortTitle
            thumbnailImage.load(currentBook.thumbnail)

            previewButton.setOnClickListener {
                clickListener.onClick(currentBook)
            }
        }

        companion object {
            fun from(parent: ViewGroup): BooksViewHolder {
                val binding = BooksListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return BooksViewHolder(binding)
            }
        }
    }

    class BooksClickListener(val clickListener: (BooksData)-> Unit){
        fun onClick(booksData: BooksData) = clickListener(booksData)
    }
}