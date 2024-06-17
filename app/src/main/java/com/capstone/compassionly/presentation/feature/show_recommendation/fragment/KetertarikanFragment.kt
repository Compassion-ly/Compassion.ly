package com.capstone.compassionly.presentation.feature.show_recommendation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.compassionly.databinding.FragmentKetertarikanBinding
import com.capstone.compassionly.presentation.adapter.ListCategoryAdapter
import com.capstone.compassionly.presentation.feature.show_recommendation.datadummy.Category
import com.capstone.compassionly.presentation.feature.show_recommendation.datadummy.DataDummyUtil
import com.capstone.compassionly.presentation.feature.show_recommendation.viewmodel.JurusanFragmentViewModel
import com.capstone.compassionly.presentation.feature.show_recommendation.viewmodel.KetertarikanFragmentViewModel
import com.capstone.compassionly.repository.di.CommonInjector

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KetertarikanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KetertarikanFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentKetertarikanBinding? = null
    private val binding get() = _binding!!
    private val viewModel: KetertarikanFragmentViewModel by viewModels {
        CommonInjector.common(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getToken().observe(viewLifecycleOwner) { userToken ->
            if (userToken != null) {
                setListInterest()
                showRecyclerView()
                Log.d("Ketertarikan Fragment", "User Token: $userToken")
            } else {
                Toast.makeText(context, "Token not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.rvInterests.layoutManager = layoutManager
    }

    private fun setListInterest() {
        Log.d("QuickRecResult", "setListInterest()")

        val adapter = ListCategoryAdapter()
        binding.rvInterests.adapter = adapter
        binding.rvInterests.layoutManager = LinearLayoutManager(requireContext())
        binding.progressBar.visibility = View.VISIBLE


        viewModel.getFieldRecResult()
        viewModel.interests.observe(viewLifecycleOwner) { interests ->
            binding.progressBar.visibility = View.GONE
            adapter.submitList(interests)
            Log.d("FieldRecResult", "result : $interests")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKetertarikanBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment KetertarikanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KetertarikanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}