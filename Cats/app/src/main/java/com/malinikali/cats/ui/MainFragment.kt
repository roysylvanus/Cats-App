package com.malinikali.cats.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.malinikali.cats.databinding.FragmentMainBinding
import com.malinikali.cats.viewmodel.CatsViewModel
import com.malinikali.cats.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private  var _binding: FragmentMainBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            btnFacts.setOnClickListener {
                val factsACtion = MainFragmentDirections.actionMainFragmentToFactListFragment()
                findNavController().navigate(factsACtion)
            }
            btnRandomFact.setOnClickListener {
                randomFactDialog()
            }
            btnBreeds.setOnClickListener {
                val breedACtion = MainFragmentDirections.actionMainFragmentToBreedsFragment()
                findNavController().navigate(breedACtion)
            }
        }

    }

    private fun randomFactDialog() {
        viewModel.generateFact()
        val factDialog =  AlertDialog.Builder(this.requireContext())
        factDialog.setTitle("Fact")
        val item = viewModel.fact.observe(this.viewLifecycleOwner,{
                fact -> fact.toString()
        })

        factDialog.apply {
            setMessage(item.toString())
           setPositiveButton("OK") { dialog, button ->
               dialog.dismiss()
           }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}