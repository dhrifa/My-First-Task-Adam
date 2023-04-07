package com.example.myfirsttaskadam.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myfirsttaskadam.databinding.FragmentPeopleDetailBinding
import com.example.myfirsttaskadam.util.MediaLoading.loadingImage

private const val TAG = "PeopleDetailFragment"
class PeopleDetailFragment : Fragment() {
    private var _binding: FragmentPeopleDetailBinding? = null
    private val binding get() = _binding

   private val viewModel: PeopleViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val person = viewModel.selectedPeopleIndex.value
        _binding = FragmentPeopleDetailBinding.inflate(inflater, container, false)
        //how can i get the selectedItem with its position?
        //viewModel.selectedPeopleIndex -> index

        binding?.apply {
            "${person?.firstName} ${person?.lastName}"
                .also { tvTitle.text = it }

            tvEmail.text = person?.email
            tvDesc.text = person?.jobtitle
            ivProfilePic.loadingImage(requireContext(),person?.avatar)
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}