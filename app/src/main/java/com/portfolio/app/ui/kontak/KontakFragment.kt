package com.portfolio.app.ui.kontak

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.portfolio.app.R
import com.portfolio.app.data.DatabaseHelper
import com.portfolio.app.data.Profile

class KontakFragment : Fragment() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kontak, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DatabaseHelper(requireContext())

        val profile = dbHelper.getProfile()

        if (profile != null) {
            // Bind data to views
            view.findViewById<TextView>(R.id.tv_email).text = profile.email
            view.findViewById<TextView>(R.id.tv_phone).text = profile.phone
            view.findViewById<TextView>(R.id.tv_address).text = profile.address
            view.findViewById<TextView>(R.id.tv_github).text = if (profile.github.isNullOrBlank()) "Belum diatur" else profile.github

            // Set click listeners
            setupClickListeners(view, profile)
        } else {
            Toast.makeText(context, "Profil tidak ditemukan di database.", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupClickListeners(view: View, profile: Profile) {
        // Email
        view.findViewById<CardView>(R.id.card_email).setOnClickListener {
            handleAction("email", profile.email)
        }

        // WhatsApp
        view.findViewById<CardView>(R.id.card_phone).setOnClickListener {
            handleAction("whatsapp", profile.phone)
        }

        // Alamat
        view.findViewById<CardView>(R.id.card_address).setOnClickListener {
            handleAction("url", profile.addressUrl)
        }

        // GitHub
        view.findViewById<CardView>(R.id.card_github).setOnClickListener {
            handleAction("url", profile.github)
        }
    }

    private fun handleAction(type: String, data: String?) {
        if (data.isNullOrBlank()) {
            Toast.makeText(context, "Informasi tidak tersedia", Toast.LENGTH_SHORT).show()
            return
        }

        var url = data
        val dialogMessage = when (type) {
            "email" -> "Aplikasi ini akan membuka aplikasi email Anda untuk mengirim ke \"$data\", Lanjutkan?"
            "whatsapp" -> {
                val waNumber = if (data.startsWith("08")) "62" + data.substring(1) else data
                url = "https://wa.me/$waNumber"
                "Aplikasi ini akan mengarahkan Anda ke WhatsApp ke nomor \"$data\", Lanjutkan?"
            }
            else -> "Aplikasi ini akan mengarahkan anda ke browser anda ke url \"$data\", Lanjutkan?"
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Aksi")
            .setMessage(dialogMessage)
            .setPositiveButton("Ya") { _, _ ->
                try {
                    val intent = when (type) {
                        "email" -> Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$url"))
                        else -> Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    }
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, "Gagal membuka aplikasi. Pastikan aplikasi terinstal.", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Tidak", null)
            .show()
    }
}
