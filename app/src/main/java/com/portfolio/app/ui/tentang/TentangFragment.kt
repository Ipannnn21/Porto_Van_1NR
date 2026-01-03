package com.portfolio.app.ui.tentang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.portfolio.app.R
import com.portfolio.app.data.DatabaseHelper

class TentangFragment : Fragment() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tentang, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DatabaseHelper(requireContext())

        val profile = dbHelper.getProfile()

        profile?.let {
            view.findViewById<TextView>(R.id.tv_nama).text = it.nama
            view.findViewById<TextView>(R.id.tv_title).text = it.title
            view.findViewById<TextView>(R.id.tv_bio).text = it.bio
            view.findViewById<TextView>(R.id.tv_deskripsi_singkat).text = it.deskripsiSingkat

            // Populate Biodata
            val gridBiodata = view.findViewById<GridLayout>(R.id.grid_biodata)
            val biodataMap = mapOf(
                "Tempat, Tanggal Lahir" to it.tempatTanggalLahir,
                "Status" to it.statusPernikahan,
                "Kewarganegaraan" to it.kewarganegaraan,
                "Agama" to it.agama,
                "Jenis Kelamin" to it.jenisKelamin,
                "Nomor Telepon" to it.phone,
                "Email" to it.email
            )

            for ((label, value) in biodataMap) {
                if (!value.isNullOrBlank()) {
                    addBiodataRow(gridBiodata, label, value)
                }
            }
        }
    }

    private fun addBiodataRow(grid: GridLayout, label: String, value: String) {
        val inflater = LayoutInflater.from(context)
        
        // Label
        val labelView = inflater.inflate(R.layout.item_biodata_label, grid, false) as TextView
        labelView.text = label
        grid.addView(labelView)
        
        // Value
        val valueView = inflater.inflate(R.layout.item_biodata_value, grid, false) as TextView
        valueView.text = ": $value"
        grid.addView(valueView)
    }
}
