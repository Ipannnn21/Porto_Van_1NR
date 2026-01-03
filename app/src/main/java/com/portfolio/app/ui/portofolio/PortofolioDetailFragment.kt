package com.portfolio.app.ui.portofolio

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.portfolio.app.R
import com.portfolio.app.data.DatabaseHelper

class PortofolioDetailFragment : Fragment() {

    private val args: PortofolioDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_portofolio_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = DatabaseHelper(requireContext())
        val portfolioItem = db.getPortfolioById(args.portfolioId)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_detail)
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        portfolioItem?.let { item ->
            // Set data to views
            toolbar.title = item.judul
            view.findViewById<TextView>(R.id.tv_detail_title).text = item.judul
            view.findViewById<TextView>(R.id.tv_detail_description).text = item.deskripsi

            // Load image from URL using Coil
            val imageView = view.findViewById<ImageView>(R.id.iv_detail_hero)
            imageView.load(item.image) {
                crossfade(true)
                placeholder(R.drawable.ic_placeholder) // Gambar sementara saat loading
                error(R.drawable.ic_placeholder_error) // Gambar jika terjadi error
            }

            // Populate ChipGroup
            val chipGroup = view.findViewById<ChipGroup>(R.id.chip_group_tech)
            chipGroup.removeAllViews()
            item.teknologi.split(",").forEach { techName ->
                val chip = Chip(requireContext()).apply {
                    text = techName.trim()
                }
                chipGroup.addView(chip)
            }

            // --- Logika Tombol Cerdas ---
            val btnViewCode = view.findViewById<Button>(R.id.btn_view_code)
            val btnLiveDemo = view.findViewById<Button>(R.id.btn_live_demo)

            // Cek URL GitHub
            if (item.linkGithub.isNullOrBlank()) {
                btnViewCode.isEnabled = false
            } else {
                btnViewCode.isEnabled = true
                btnViewCode.setOnClickListener {
                    showConfirmationDialog(item.linkGithub)
                }
            }

            // Cek URL Demo
            if (item.linkGeneral.isNullOrBlank()) {
                btnLiveDemo.isEnabled = false
            } else {
                btnLiveDemo.isEnabled = true
                btnLiveDemo.setOnClickListener {
                    showConfirmationDialog(item.linkGeneral)
                }
            }
        }
    }

    private fun showConfirmationDialog(url: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Navigasi")
            .setMessage("Aplikasi ini akan mengarahkan anda ke browser anda ke url \"$url\", Lanjutkan?")
            .setPositiveButton("Ya") { _, _ ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
            .setNegativeButton("Tidak", null)
            .show()
    }
}
