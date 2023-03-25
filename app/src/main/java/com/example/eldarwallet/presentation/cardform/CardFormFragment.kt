package com.example.eldarwallet.presentation.cardform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.eldarwallet.databinding.FragmentCardFormBinding

class CardFormFragment : Fragment() {
    private var _binding: FragmentCardFormBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CarFormViewModel>()

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

        binding.buttonAddNewCard.setOnClickListener {

            val pattern1 = binding.etNumber1.text.toString()
            val pattern2 = binding.etNumber2.text.toString()
            val pattern3 = binding.etNumber3.text.toString()
            val pattern4 = binding.etNumber4.text.toString()
            val cardNumer = pattern1+pattern2+pattern3+pattern4
            val surname = binding.etCardSurname.text.toString()
            val name = binding.etCardName.text.toString()
            val monthExp = binding.etExpiryMonth.text.toString()
            val yearExp = binding.etExpiryYear.text.toString()
            val cvv = binding.etCvv.text.toString()

            //"$pattern1\t$pattern2\t$pattern3\t$pattern4".also { binding.textViewCreditCardNumber.text = it }

        }
    }
}