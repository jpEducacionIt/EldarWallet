package com.example.eldarwallet.presentation.dashboard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eldarwallet.R
import com.example.eldarwallet.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var dashboardAdapter: DashboardAdapter

    private val viewModel by viewModels<DashboardViewModel> { DashboardViewModelFactory(requireActivity().application) }

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
            val userDataDecrypt = viewModel.decryptUserData(it)
            dashboardAdapter.submitList(userDataDecrypt)
        })

        binding.buttonAddCard.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_cardFormFragment2)
        }

        binding.buttonQr.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_qrCodeFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}