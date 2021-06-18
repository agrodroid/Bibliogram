package com.tencalculus.bibliogram.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.tencalculus.bibliogram.*
import com.tencalculus.bibliogram.databinding.HomeFragmentBinding
import com.tencalculus.bibliogram.models.LoadStatus

class HomeFragment : Fragment() {

    private lateinit var homeFragmentViewModel: HomeFragmentViewModel

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: HomeFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeFragmentViewModelFactory = HomeFragmentViewModelFactory(args.searchInput)
        homeFragmentViewModel = ViewModelProvider(this, homeFragmentViewModelFactory).get(HomeFragmentViewModel::class.java)

        val adapter = BooksAdapter(BooksAdapter.BooksClickListener {
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it))
        })

        _binding?.booksRecyclerView?.adapter = adapter

        _binding?.searchFab?.setOnClickListener {
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }

        homeFragmentViewModel.status.observe(viewLifecycleOwner, Observer {
            if (it == LoadStatus.LOADING) {
                _binding?.progressBar?.visibility = View.VISIBLE
                _binding?.errorTextView?.visibility = View.GONE
                _binding?.retryButton?.visibility = View.GONE
            }
            else if (it == LoadStatus.SUCCESS) {
                _binding?.progressBar?.visibility = View.GONE
                _binding?.errorTextView?.visibility = View.GONE
                _binding?.retryButton?.visibility = View.GONE
            }
            else if (it == LoadStatus.FAILED) {
                _binding?.progressBar?.visibility = View.GONE
                _binding?.errorTextView?.visibility = View.VISIBLE
                _binding?.retryButton?.visibility = View.VISIBLE
                _binding?.retryButton?.setOnClickListener {
                    homeFragmentViewModel.getBooks(args.searchInput)
                }
            }
        })

        homeFragmentViewModel.receivedResponse.observe(viewLifecycleOwner, Observer {
                adapter.data = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}