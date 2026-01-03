package com.portfolio.app.ui.tentang

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
            val biodataContainer = view.findViewById<LinearLayout>(R.id.container_biodata)
            biodataContainer.removeAllViews() // Bersihkan view lama jika ada

            val biodataMap = linkedMapOf(
                "Tempat, Tanggal Lahir :" to it.tempatTanggalLahir,
                "Status :" to it.statusPernikahan,
                "Kewarganegaraan :" to it.kewarganegaraan,
                "Agama :" to it.agama,
                "Jenis Kelamin :" to it.jenisKelamin,
                "Nomor Telepon :" to it.phone,
                "Email :" to it.email
            )

            for ((label, value) in biodataMap) {
                if (!value.isNullOrBlank()) {
                    addBiodataRow(biodataContainer, label, value)
                }
            }
        }
    }

    private fun addBiodataRow(container: LinearLayout, label: String, value: String) {
        val context = requireContext()

        // 1. Label TextView
        val labelTextView = TextView(context).apply {
            text = label
            setTextAppearance(android.R.style.TextAppearance_Material_Body2)
            // Atur margin bawah untuk memberi spasi sebelum nilai
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 2 }
        }

        // 2. Value TextView
        val valueTextView = TextView(context).apply {
            text = value
            setTextAppearance(android.R.style.TextAppearance_Material_Body1)
            setTypeface(typeface, Typeface.BOLD)
            // Atur margin bawah untuk memberi spasi antar pasangan biodata
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 16 } // Spasi 16dp
        }
        
        container.addView(labelTextView)
        container.addView(valueTextView)
    }
}
