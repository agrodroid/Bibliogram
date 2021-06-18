package com.tencalculus.bibliogram.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.snackbar.Snackbar

import com.tencalculus.bibliogram.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    private var _binding: FragmentDetailsBinding?  = null
            val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailsReceived = args.bookDetails
        _binding?.detailsThumbnail?.load(detailsReceived.thumbnail)
        _binding?.detailsTitle?.text = detailsReceived.title
        _binding?.detailsLanguage?.text = detailsReceived.language

        _binding?.readButton?.setOnClickListener {
            if(detailsReceived.previewLink != "") {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(detailsReceived.previewLink)
                startActivity(intent)
            }else{
                val contextView = _binding?.readButton
                if (contextView != null) {
                    Snackbar.make(contextView, "Unavailable", Snackbar.LENGTH_SHORT)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}