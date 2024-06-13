package com.capstone.compassionly.presentation.feature.show_recommendation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.compassionly.databinding.FragmentJurusanBinding
import com.capstone.compassionly.presentation.adapter.ListMajorRecAdapter
import com.capstone.compassionly.presentation.feature.show_recommendation.datadummy.DataDummyUtil
import com.capstone.compassionly.presentation.feature.show_recommendation.datadummy.Major
import com.capstone.compassionly.presentation.feature.show_recommendation.viewmodel.JurusanFragmentViewModel
import com.capstone.compassionly.repository.di.CommonInjector

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JurusanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JurusanFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentJurusanBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JurusanFragmentViewModel by viewModels {
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
        setListMajor()
        showRecyclerView()
    }

    private fun showRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.rvMajors.layoutManager = layoutManager
    }


    private fun setListMajor() {
        val adapter = ListMajorRecAdapter()
        binding.rvMajors.adapter = adapter

        viewModel.getMajorRecResult()
        viewModel.majorrec.observe(viewLifecycleOwner) { majors ->
            binding.progressBar.visibility = View.GONE
            adapter.submitList(majors)
            Log.d("Major Rec Fragment", "result : $majors")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJurusanBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JurusanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}



