package com.example.eldarwallet.presentation.dashboard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eldarwallet.R
import com.example.eldarwallet.core.action.UserDataDecrypt
import com.example.eldarwallet.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var dashboardAdapter: DashboardAdapter
    private var isPaymentSelected: Boolean = false
    private var usersDataDecrypt: MutableList<UserDataDecrypt> = mutableListOf()
    private lateinit var userData: UserDataDecrypt

    private val viewModel by viewModels<DashboardViewModel> {
        DashboardViewModelFactory(
            requireActivity().application
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardAdapter = DashboardAdapter()
        binding.recyclerViewCards.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = dashboardAdapter
        }

        viewModel.userCreditData.observe(viewLifecycleOwner, Observer {
            usersDataDecrypt = viewModel.decryptUserData(it)
            dashboardAdapter.submitList(usersDataDecrypt)
        })

        dashboardAdapter.onItemClickListener = {
            selectPaymentMethod(it)
        }

        binding.buttonAddCard.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_cardFormFragment2)
        }

        binding.buttonQr.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_qrCodeFragment)
        }

        binding.buttonPaymentMethod.setOnClickListener {
            if (isPaymentSelected) {
                findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToPaymentFragment(userData))
            } else {
                Toast.makeText(
                    requireActivity(),
                    "Debe seleccionar una tarjeta",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun selectPaymentMethod(data: UserDataDecrypt) {
        usersDataDecrypt.map {
            it.isSelected = false
        }
        dashboardAdapter.notifyDataSetChanged()
        !isPaymentSelected

        usersDataDecrypt.indexOfFirst { userData ->
            userData.id == data.id
        }.let { position ->
            usersDataDecrypt[position].apply {
                isSelected = true
                isPaymentSelected = true
                usersDataDecrypt[position] = this
                userData = this
            }
            dashboardAdapter.notifyItemChanged(position)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}