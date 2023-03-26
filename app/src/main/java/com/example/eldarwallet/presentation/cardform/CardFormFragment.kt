package com.example.eldarwallet.presentation.cardform

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.eldarwallet.R
import com.example.eldarwallet.core.domain.CardHolderResponse
import com.example.eldarwallet.core.domain.SaveInAppResponse
import com.example.eldarwallet.databinding.FragmentCardFormBinding

class CardFormFragment : Fragment() {
    private var _binding: FragmentCardFormBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CardFormViewModel> { CardFormViewModelFactory(requireActivity().application) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardFormBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etCardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                return
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(text: Editable?) {
                if (text?.length != 0) {
                    val cardImage = when (text?.get(0)?.toString()) {
                        "3" -> R.drawable.amex
                        "4" -> R.drawable.visa
                        "5" -> R.drawable.mastercard
                        else -> R.drawable.baseline_add_card_24
                    }
                    binding.imageViewCardImage.visibility = View.VISIBLE
                    binding.imageViewCardImage.setImageResource(cardImage)
                } else {
                    binding.imageViewCardImage.visibility = View.INVISIBLE
                }
            }
        })

        binding.buttonAddNewCard.setOnClickListener {
            val surname = binding.etCardSurname.text.toString()
            val name = binding.etCardName.text.toString()
            viewModel.validateCardHolderData(surname, name)
        }

        viewModel.userDataVerificationStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                CardHolderResponse.SUCCESS -> saveNewCreditCard()
                else -> {
                    Toast.makeText(requireActivity(), "Error al ingresar datos", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.navigateToDashboard.observe(viewLifecycleOwner, Observer {
            when (it) {
                SaveInAppResponse.SUCCESS -> navigateToDashboard()
                else -> {
                    Toast.makeText(requireActivity(), "Error al grabar in App", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun navigateToDashboard() {
        findNavController().navigate(R.id.action_cardFormFragment_to_dashboardFragment)
    }

    private fun saveNewCreditCard() {
        val cardNumber = binding.etCardNumber.text.toString()
        val surname = binding.etCardSurname.text.toString()
        val name = binding.etCardName.text.toString()
        val expiry = binding.etExpiry.text.toString()
        val cvv = binding.etCvv.text.toString()

        viewModel.saveNewCreditCard(surname, name, cardNumber, expiry, cvv)
    }
}