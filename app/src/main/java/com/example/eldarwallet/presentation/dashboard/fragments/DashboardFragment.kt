package com.example.eldarwallet.presentation.dashboard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eldarwallet.databinding.FragmentDashboardBinding
import com.example.eldarwallet.infrastructure.representation.UserCardsData

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var dashboardAdapter: DashboardAdapter

    private val viewModel by viewModels<DashboardViewModel>()

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

        dashboardAdapter.submitList(listOfCards())

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun listOfCards(): List<UserCardsData> {
        return listOf(
            UserCardsData(1, "3", "pepe", "grillo", "371180303257522", "1234", "1125"),
            UserCardsData(2, "4", "jose", "grillo", "4509953566233704", "123", "1125"),
            UserCardsData(3, "5", "pepe", "grillo", "5031755734530604", "123", "1125"),
        )
    }
}