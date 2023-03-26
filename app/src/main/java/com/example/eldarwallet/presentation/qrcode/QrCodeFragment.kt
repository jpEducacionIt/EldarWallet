package com.example.eldarwallet.presentation.qrcode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.eldarwallet.core.domain.CardHolderResponse
import com.example.eldarwallet.databinding.FragmentQrCodeBinding
import com.example.eldarwallet.presentation.setBitmapFrom

class QrCodeFragment : Fragment() {

    private var _binding: FragmentQrCodeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<QrCodeViewModel> { QrCodeViewModelFactory(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQrCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userData.observe(viewLifecycleOwner, Observer {
            val name = it.name
            val surName = it.surname
            binding.imageViewQr.setBitmapFrom("https://quickchart.io/qr?text=$name$surName")
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
