package com.example.eldarwallet.presentation.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.eldarwallet.R
import com.example.eldarwallet.core.action.UserDataDecrypt
import com.example.eldarwallet.databinding.FragmentDashboardBinding
import com.example.eldarwallet.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {
    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    private lateinit var userData: UserDataDecrypt
    private val args: PaymentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userData = args.userdata
        binding.lastDigitsCardNumber.text = userData.number.takeLast(4)
        val image = when(userData.typeCard) {
            "3" -> R.drawable.amex
            "4" -> R.drawable.visa
            else -> {R.drawable.mastercard}
        }
        binding.itemListCarLogo.setImageResource(image)
    }
}