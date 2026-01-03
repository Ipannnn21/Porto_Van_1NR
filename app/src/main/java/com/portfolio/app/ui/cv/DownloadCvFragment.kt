package com.portfolio.app.ui.cv

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.portfolio.app.R
import com.portfolio.app.util.PdfGenerator

class DownloadCvFragment : Fragment() {

    private val cvDesignUrl = "https://raw.githubusercontent.com/Ipannnn21/database-android/refs/heads/main/cv/CV%2CIrfan%20Harits%20Hamman%20Akbar.pdf"
    private val appFolderName = "Porto Irfan"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_download_cv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.card_download_design).setOnClickListener {
            showConfirmationDialog("mengunduh CV Desain") { 
                downloadCvFromUrl()
            }
        }

        view.findViewById<View>(R.id.card_generate_ats).setOnClickListener {
            showConfirmationDialog("mengunduh CV ATS-Friendly") { 
                generateAtsCv()
            }
        }
    }

    private fun downloadCvFromUrl() {
        try {
            val fileName = "CV_Irfan_Harits_Desain.pdf"
            
            // PERBAIKAN: Menggunakan argumen yang benar untuk setDestinationInExternalPublicDir
            val request = DownloadManager.Request(Uri.parse(cvDesignUrl))
                .setTitle(fileName)
                .setDescription("Mengunduh CV Desain...")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, appFolderName + "/" + fileName)
            
            val downloadManager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)

            Toast.makeText(requireContext(), "Mulai mengunduh... Cek notifikasi.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Gagal memulai unduhan: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace() // Untuk debugging
        }
    }

    private fun generateAtsCv() {
        try {
            val pdfGenerator = PdfGenerator(requireContext())
            pdfGenerator.generateAndSaveAtsCv()
            Toast.makeText(requireContext(), "CV ATS berhasil disimpan di folder Downloads/$appFolderName", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Gagal membuat CV ATS: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun showConfirmationDialog(actionText: String, onConfirm: () -> Unit) {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Unduhan")
            .setMessage("Anda akan $actionText. Lanjutkan?")
            .setPositiveButton("Ya") { _, _ -> onConfirm() }
            .setNegativeButton("Tidak", null)
            .show()
    }
}
