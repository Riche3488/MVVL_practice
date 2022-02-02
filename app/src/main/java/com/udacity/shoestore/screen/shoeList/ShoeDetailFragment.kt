package com.udacity.shoestore.screen.shoeList

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private var mutableList =
        mutableListOf<Shoe>()// = mutableListOf(Shoe("noData",0.0,"noData","noData","noData"))

    @SuppressLint("LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoeDetailBinding.inflate(inflater, container, false)

        binding.fragment = this

        binding.saveBtn.setOnClickListener { view ->
            if (model.shoes_list.value != null) {
                mutableList = model.shoes_list.value!!
                addShoeItem(view, mutableList)
            } else {
                addShoeItem(view, mutableList)
            }
        }
        binding.cancelBtn.setOnClickListener { view ->
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

    fun addShoeItem(view: View, list: MutableList<Shoe>) {
        if ((binding.shoeNameEditText.text.toString() != "") &&
            (binding.shoeSizeEditText.text.toString() != "") &&
            (binding.shoeCompanyEditText.text.toString() != "") &&
            binding.shoeDescriptionEditText.text.toString() != "" &&
            binding.shoeImgEditText.text.toString() != ""
        ) {
            list.add(
                Shoe(
                    binding.shoeNameEditText.text.toString(),
                    binding.shoeSizeEditText.text.toString().toDouble(),
                    binding.shoeCompanyEditText.text.toString(),
                    binding.shoeDescriptionEditText.text.toString(),
                    binding.shoeImgEditText.text.toString()
                )
            )

            model.shoes_list.value = list
            view.findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        } else {
            Toast.makeText(context, "Fill the text", Toast.LENGTH_LONG).show()
            return
        }
    }
}