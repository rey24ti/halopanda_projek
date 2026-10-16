package com.example.rey_halopanda.projek

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rey_halopanda.R

class ProfilDeveloperFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profil_developer, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<View>(R.id.btnGithub).setOnClickListener {
            openUrl("https://github.com/rey24ti")
        }

        view.findViewById<View>(R.id.btnInstagram).setOnClickListener {
            openUrl("https://www.instagram.com/rey.vinsen/")
        }

        view.findViewById<View>(R.id.btnYoutube).setOnClickListener {
            openUrl("https://www.youtube.com/@REYVINSHEN")
        }
    }


    private fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {

            Toast.makeText(requireContext(), "Tidak dapat membuka link: $url", Toast.LENGTH_SHORT).show()
        }
    }
}