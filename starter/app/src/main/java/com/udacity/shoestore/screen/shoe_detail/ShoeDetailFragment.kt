package com.udacity.shoestore.screen.shoe_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screen.MainActivityViewModel
import com.udacity.shoestore.screen.onboarding.instruction_screen.InstructionsFragmentDirections

class ShoeDetailFragment : Fragment() {
    private var _binding: FragmentShoeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListenerForViews()
    }

    private fun setupListenerForViews() {
        with(binding) {
            shoe = Shoe("", 0.0, "", "")
            saveButton.setOnClickListener {
                val name = shoe?.name ?: ""
                if (name.isEmpty()) {
                    showToast("Please fill the name field")
                    return@setOnClickListener
                }
                val size = shoe?.size?.toString() ?: ""
                if (size.isEmpty()) {
                    showToast("Please fill the size field")
                    return@setOnClickListener
                }
                val company = shoe?.company ?: ""
                if (company.isEmpty()) {
                    showToast("Please fill the company field")
                    return@setOnClickListener
                }
                val description = shoe?.description ?: ""
                if (description.isEmpty()) {
                    showToast("Please fill the description field")
                    return@setOnClickListener
                }
                viewModel.addShoe(name, size.toDouble(), company, description)
                findNavController().navigateUp()
            }

            cancelButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}