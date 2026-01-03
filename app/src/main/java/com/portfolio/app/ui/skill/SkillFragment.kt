package com.portfolio.app.ui.skill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.portfolio.app.R
import com.portfolio.app.data.DatabaseHelper

class SkillFragment : Fragment() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_skill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DatabaseHelper(requireContext())
        recyclerView = view.findViewById(R.id.rv_skills)
        recyclerView.layoutManager = LinearLayoutManager(context)

        loadSkills()
    }

    private fun loadSkills() {
        val skills = dbHelper.getSkills()
        val groupedSkills = skills.groupBy { it.kategori }

        val adapterItems = mutableListOf<Any>()
        groupedSkills.forEach { (kategori, skillList) ->
            adapterItems.add(kategori) // Tambahkan header kategori
            adapterItems.add(skillList.map { it.nama }) // Tambahkan daftar nama skill
        }

        recyclerView.adapter = SkillAdapter(adapterItems)
    }
}
