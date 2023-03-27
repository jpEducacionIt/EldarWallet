package com.example.eldarwallet.presentation.cardform

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.eldarwallet.R
import com.example.eldarwallet.core.cardform.mvp.CardFormContracts
import com.example.eldarwallet.core.cardform.mvp.CardFormModel
import com.example.eldarwallet.core.cardform.mvp.CardFormPresenter
import com.example.eldarwallet.core.domain.CardHolderResponse
import com.example.eldarwallet.core.domain.SaveInAppResponse
import com.example.eldarwallet.databinding.FragmentCardFormBinding

class CardFormFragment : Fragment(), CardFormContracts.View {
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
        val presenter = CardFormPresenter(this)

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
            val cardNumber = binding.etCardNumber.text.toString()
            val surname = binding.etCardSurname.text.toString()
            val name = binding.etCardName.text.toString()
            val expiry = binding.etExpiry.text.toString()
            val cvv = binding.etCvv.text.toString()

            it.isClickable = false

            presenter.validateData( name, surname, cardNumber, expiry, cvv)
        }


        viewModel.userDataVerificationStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                CardHolderResponse.SUCCESS -> saveNewCreditCard()
                else -> {
                    Toast.makeText(requireActivity(), "Sus datos no son validos", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.navigateToDashboard.observe(viewLifecycleOwner, Observer {
            when (it) {
                SaveInAppResponse.SUCCESS -> navigateToDashboard()
                else -> {
                    Toast.makeText(requireActivity(), "Error en conexion, intente nuevamente", Toast.LENGTH_SHORT).show()
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

    override fun saveUserCreditData(
        userName: String,
        surname: String,
        cardNumber: String,
        expiry: String,
        cvv: String
    ) {
        viewModel.validateCardHolderData(surname, userName)
    }
    override fun setCardNumberError() {
        binding.etCardNumber.error = "Ingrese su numero de tarjeta"
        binding.buttonAddNewCard.isClickable = true
    }

    override fun setNameError() {
        binding.etCardName.error = "Debe ingresar su nombre"
        binding.buttonAddNewCard.isClickable = true
    }

    override fun setSurnameError() {
        binding.etCardSurname.error = "Debe ingresar su apellido"
        binding.buttonAddNewCard.isClickable = true
    }

    override fun setExpiryError() {
        binding.etExpiry.error = "Ingrese fecha de vencimiento de la tarjeta"
        binding.buttonAddNewCard.isClickable = true
    }

    override fun setCvvError() {
        binding.etCvv.error = "Ingrese el cvv de la tarjeta"
        binding.buttonAddNewCard.isClickable = true
    }
}