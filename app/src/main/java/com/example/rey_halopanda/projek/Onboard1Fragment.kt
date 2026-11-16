package com.example.rey_halopanda.projek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rey_halopanda.databinding.FragmentOnboardItemBinding

class Onboard1Fragment : Fragment() {
    private var _binding: FragmentOnboardItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOnboardItemBinding.inflate(inflater, container, false)
        binding.tvTitle.text = "Belanja Material Mudah"
        binding.tvDesc.text = "Cari semen, pasir, besi, dan material bangunan lainnya hanya dalam genggaman Anda."
        binding.ivOnboardImage.setImageResource(android.R.drawable.ic_menu_search)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}