package com.udacity.shoestore.screen.shoeList

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe


//A TextView label and EditView for the Shoe Name, Company, Shoe Size and Description
//A Cancel button with an action to navigate back to the shoe list screen
//A Save button with an action to navigate back to the shoe list screen and add a new Shoe to the Shoe View Model.

class ShoeDetailFragment : Fragment() {


    private var _binding: FragmentShoeDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val model: ShoeListViewModel by activityViewModels()
    val mutableList = mutableListOf<Shoe>()

    @SuppressLint("LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentShoeDetailBinding.inflate(inflater, container, false)

        binding.fragment = this




        binding.saveBtn.setOnClickListener { view ->
            mutableList.add(Shoe("name",255.0,"company"
                ,"description","img source"))
            model.shoes_list.value = mutableList
            Log.d("ShoeDetailFragment", ""+ model.shoes_list.value)

            view.findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        Log.i("ShoeDetailFragment", "onStopCalled")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.i("ShoeDetailFragment", "onDestroyCalled")
    }

}