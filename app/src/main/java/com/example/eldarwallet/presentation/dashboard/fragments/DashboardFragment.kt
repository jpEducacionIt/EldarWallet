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
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardAdapter = DashboardAdapter()
        binding.recyclerViewCards.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = dashboardAdapter
        }

        viewModel.onShow()

        viewModel.userVerificationData.observe(viewLifecycleOwner, Observer {
            dashboardAdapter.submitList(it.data)
        })

        binding.buttonAddCard.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_cardFormFragment2)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}