package com.portfolio.app.ui.skill

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.portfolio.app.R

class SkillAdapter(private val items: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_CHIP_GROUP = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is String -> TYPE_HEADER
            is List<*> -> TYPE_CHIP_GROUP
            else -> throw IllegalArgumentException("Invalid type of data at position $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_skill_header, parent, false)
                HeaderViewHolder(view)
            }
            TYPE_CHIP_GROUP -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_skill_chipgroup, parent, false)
                ChipGroupViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.bind(items[position] as String)
            }
            is ChipGroupViewHolder -> {
                holder.bind(items[position] as List<String>)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.tv_skill_category)
        fun bind(category: String) {
            categoryTextView.text = category
        }
    }

    class ChipGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val chipGroup: ChipGroup = itemView.findViewById(R.id.chip_group_skills)
        fun bind(skills: List<String>) {
            chipGroup.removeAllViews()
            for (skillName in skills) {
                val chip = Chip(chipGroup.context).apply {
                    text = skillName
                }
                chipGroup.addView(chip)
            }
        }
    }
}
