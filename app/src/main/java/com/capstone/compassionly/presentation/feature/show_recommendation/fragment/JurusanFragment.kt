package com.capstone.compassionly.presentation.feature.show_recommendation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityShowRecommendationBinding
import com.capstone.compassionly.databinding.FragmentJurusanBinding
import com.capstone.compassionly.presentation.adapter.ListMajorRecAdapter
import com.capstone.compassionly.presentation.feature.show_recommendation.datadummy.DataDummyUtil
import com.capstone.compassionly.presentation.feature.show_recommendation.datadummy.Major

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listMajor = DataDummyUtil.getMajors()
        setListMajor(listMajor)
        showRecyclerView()
    }

    private fun showRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.rvMajors.layoutManager = layoutManager
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setListMajor(majors: List<Major>) {
        val adapter = ListMajorRecAdapter()
        adapter.submitList(majors)
        binding.rvMajors.adapter = adapter
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