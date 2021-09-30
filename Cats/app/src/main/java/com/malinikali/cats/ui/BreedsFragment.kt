package com.malinikali.cats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.malinikali.cats.adapter.BreedsAdapter
import com.malinikali.cats.databinding.FragmentBreedsBinding
import com.malinikali.cats.viewmodel.CatsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BreedsFragment : Fragment() {



    private var _binding: FragmentBreedsBinding? = null
    private val binding get() = _binding
    private lateinit var breedsAdapter :BreedsAdapter
    private val  sharedViewModel:CatsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        breedsAdapter = BreedsAdapter()

        binding?.rcBreeds?.apply {
            adapter = breedsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)

        }

        lifecycleScope.launch {
            sharedViewModel.breedListData.collect {
                pagingData ->
                breedsAdapter.submitData(pagingData)
            }
        }



        _binding = FragmentBreedsBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}