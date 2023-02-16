package com.example.myfirsttaskadam.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirsttaskadam.R
import com.example.myfirsttaskadam.data.model.people.PeopleModel
import com.example.myfirsttaskadam.databinding.FragmentPeopleBinding
import com.example.myfirsttaskadam.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment() {

    private var _binding: FragmentPeopleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //viewmodel by delegation
//    val viewModel: PeopleViewModel by viewModels<PeopleViewModel>()
    private val viewModel: PeopleViewModel by activityViewModels<PeopleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val peopleViewModel =  ViewModelProvider(this)[PeopleViewModel::class.java]

        _binding = FragmentPeopleBinding.inflate(inflater, container, false)

        binding.let { ui ->
            val textView: TextView = ui.textHome
            viewModel.peopleList.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Loading -> {
                        Toast.makeText(context, "Loading...!", Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Success -> {
                        Toast.makeText(context, "data...!", Toast.LENGTH_SHORT).show()
                        initView(it.data)
                    }
                    is NetworkResult.Error -> {
                        textView.text = it.message
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            viewModel.getPeopleList()
        }
        return binding.root
    }

    private fun initView(data: PeopleModel?) {
        data?.let {
            binding.rvPeople.layoutManager = LinearLayoutManager(context)
            binding.rvPeople.adapter = PeopleAdapter(data) {
                println(it)
                Toast.makeText(context, "${it.firstName} clicked!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_navigation_people_to_peopleDetailFragment)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}