package com.medibank.shop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.medibank.shop.R
import com.medibank.shop.databinding.ItemSavedBinding
import com.medibank.shop.model.SourcedsData
import com.medibank.shop.viewmodel.SelectedSavedData


class SavedAdapter(
    private val news: List<SourcedsData>,
    private val callback: SelectedSavedData? = null
) :
    RecyclerView.Adapter<SavedAdapter.SavedViewHolder>() {

    inner class SavedViewHolder(
        private val itemBinding: ItemSavedBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: SourcedsData) {
                itemBinding.title.text = item.title
                itemBinding.description.text = item.description
                itemBinding.author.text = "Author: "+item.author
                itemBinding.image.load(item.urlToImage) {
                    crossfade(true)
                    placeholder(R.drawable.outline_article_24)
                    transformations(CircleCropTransformation())
                }
            itemBinding.tile.setOnClickListener { item.source?.id?.let { sourceId ->
                item.url?.let { url ->
                    callback?.onSavedData(
                        url
                    )
                }
            } }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SavedViewHolder(
            ItemSavedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) =
        holder.bind(news[position])

    override fun getItemCount() = news.size

}