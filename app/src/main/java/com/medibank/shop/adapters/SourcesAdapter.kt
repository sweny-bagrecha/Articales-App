package com.medibank.shop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.medibank.shop.databinding.ItemSourcesBinding
import com.medibank.shop.dto.SourcesDto
import com.medibank.shop.model.NewsData
import com.medibank.shop.model.SourcedsData

class SourcesAdapter(
    private val sources: List<SourcedsData>
) :
    RecyclerView.Adapter<SourcesAdapter.SourcesViewHolder>() {
    inner class SourcesViewHolder(
        private val itemBinding: ItemSourcesBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: SourcedsData) {
            itemBinding.sources.text = item.source?.name
            itemBinding.checkBox.setOnClickListener {}

            itemBinding.checkBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener
            { buttonView, isChecked ->
                if (isChecked){
                     val sourcedData =   SourcedsData(
                        SourcesDto(item.source?.id,item.source?.name),
                            item.author,
                            item.title,
                            item.description,
                            item.urlToImage,
                            item.url,
                            item.publishedAt,
                            item.content,
                            true,
                            false)
                }
            }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SourcesViewHolder(
            ItemSourcesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
        holder.bind(sources[position])
    }

    override fun getItemCount() = sources.size
}