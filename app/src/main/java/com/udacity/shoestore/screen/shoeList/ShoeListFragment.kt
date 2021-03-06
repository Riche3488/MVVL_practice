package com.udacity.shoestore.screen.shoeList

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.item_view.view.*


class ShoeListFragment : Fragment() {

    private var _binding: FragmentShoeListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val model: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)

        binding.fragment = this

        binding.floatingActionButton.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        val shoeObserver = Observer<MutableList<Shoe>>{ new_shoes ->
            for (i: Int in 0..(new_shoes.size-1))
                binding.shoeListLayout.addView(updateView (new_shoes[i]))
        }
        model.shoes_list.observe(viewLifecycleOwner, shoeObserver)
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        Log.i("ShoeListFragment","onStop Called")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("ShoeListFragment","onDestroy Called")
        _binding = null
    }

    fun updateView(new_shoes_item: Shoe):View {
        val inflater = LayoutInflater.from(activity)
        val itemView = inflater.inflate(R.layout.item_view,null)
        itemView.name_text.text = new_shoes_item.name
        itemView.size_text.text = new_shoes_item.size.toString()
        itemView.company_text.text = new_shoes_item.company
        //itemView.description_text.text = new_shoes_item.description
        //itemView.img_text.text = new_shoes_item.images
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.setMargins(0,16,0,16)
        itemView.layoutParams = lp
        return itemView
    }
    fun updateView_base(new_shoes_item: Shoe):View {
        val text = TextView(activity)
        text.text = new_shoes_item.name
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        text.layoutParams = lp
        return text
    }

}