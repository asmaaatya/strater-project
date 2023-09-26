package com.example.straterproject.ui.quran.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.models.Aya
import com.example.straterproject.databinding.QuranSearchItemBinding
import com.example.straterproject.ui.quran.search.interfaces.IoClickSearchItem

class QuranSearchAdapter(var ioclicksearch: IoClickSearchItem?) :
    RecyclerView.Adapter<QuranSearchAdapter.QuranSearchHolder>() {
    var searchList = ArrayList<Aya>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuranSearchHolder {
        val binding =
            QuranSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuranSearchHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: QuranSearchHolder, position: Int) {
        holder.bind(searchList.get(position))
        holder.itemView.setOnClickListener {
            ioclicksearch?.onSearchItemClick(searchList.get(position))
        }
    }

    fun updateListSearchResult(listSearch: ArrayList<Aya>) {
        searchList = listSearch
        notifyDataSetChanged()
    }

    class QuranSearchHolder(val binding: QuranSearchItemBinding) : ViewHolder(binding.root) {
        fun bind(aya: Aya) {
            binding.apply {
                soraName.text = aya.sora_name_ar
                ayaNo.text = aya.aya_no.toString()
                ayaContent.text = aya.aya_text
            }

        }

    }
}