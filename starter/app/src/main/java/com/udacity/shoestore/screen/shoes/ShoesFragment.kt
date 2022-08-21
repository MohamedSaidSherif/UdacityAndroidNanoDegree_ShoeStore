package com.udacity.shoestore.screen.shoes

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.screen.MainActivityViewModel

class ShoesFragment : Fragment() {

    private var _binding: FragmentShoesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListenerForViews()
        observeShoesList()
    }

    private fun setupListenerForViews() {
        with(binding) {
            floatingActionButton.setOnClickListener {
                findNavController().navigate(ShoesFragmentDirections.actionShoesFragmentToShoeDetailFragment())
            }
        }
    }

    private fun observeShoesList() {
        viewModel.shoes.observe(viewLifecycleOwner) { shoesList ->
            for (shoe in shoesList) {
                val shoeItemBinding: ShoeItemBinding =
                    DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.shoe_item,
                        binding.shoesList,
                        false
                    )
                shoeItemBinding.shoe = shoe
                binding.shoesList.addView(shoeItemBinding.root)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoes_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                findNavController().navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}