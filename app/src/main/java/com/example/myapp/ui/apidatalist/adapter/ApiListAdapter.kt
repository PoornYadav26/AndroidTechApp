package com.example.myapp.ui.apidatalist.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.clicklistener.RecyclerviewListener
import com.example.myapp.databinding.RowApiListBinding
import com.example.myapp.ui.apidatalist.model.EntryModel
import java.util.Locale

class ApiListAdapter(private var originalList: List<EntryModel>,
                     private val listener: RecyclerviewListener
) :
    RecyclerView.Adapter<ApiListAdapter.APIListViewHolder>(), Filterable {

    private var filteredList: List<EntryModel> = originalList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): APIListViewHolder {
        val binding = RowApiListBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return APIListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: APIListViewHolder, position: Int) {
        val item = filteredList[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    fun submitList(newList: List<EntryModel>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return filteredList.size
            }

            override fun getNewListSize(): Int {
                return newList.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return filteredList[oldItemPosition].aPI == newList[newItemPosition].aPI
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return filteredList[oldItemPosition] == newList[newItemPosition]
            }
        })
        filteredList = newList
        originalList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredResults = mutableListOf<EntryModel>()
                if (constraint.isNullOrBlank()) {
                    filteredResults.addAll(originalList)
                } else {
                    val filterPattern = constraint.toString().trim().lowercase(Locale.ROOT)
                    for (item in originalList) {
                        if (item.aPI!!.lowercase(Locale.ROOT).contains(filterPattern)) {
                            filteredResults.add(item)
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredResults
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredList = results?.values as List<EntryModel>
                notifyDataSetChanged()
            }
        }
    }

    inner class APIListViewHolder(private val binding: RowApiListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EntryModel) {
            binding.apply {
                tvName.text = item.aPI
                tvCategory.text = item.category
                tvDes.text = item.description
                tvLink.text = item.link
                tvLink.paintFlags = tvLink.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                tvLink.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(item)
                    }
                }
            }
        }
    }

   }
