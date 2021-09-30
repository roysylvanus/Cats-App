package com.malinikali.cats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.malinikali.cats.adapter.FactsAdapter
import com.malinikali.cats.databinding.FragmentFactListBinding
import com.malinikali.cats.viewmodel.CatsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FactListFragment : Fragment() {

    private var _binding: FragmentFactListBinding? = null
    private val binding get() = _binding
    private val viewModel : CatsViewModel by viewModels()
    private lateinit var factsAdapter:FactsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFactListBinding.inflate(inflater,container,false)
        setUpRv()
        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv()
    }

    private fun setUpRv() {
        factsAdapter = FactsAdapter()
        binding?.rcFacts?.apply {

            adapter = factsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)


        }

        lifecycleScope.launch {
            viewModel.factListData.collect {
                pagingData ->
                factsAdapter.submitData(pagingData)
            }
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}