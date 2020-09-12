package com.geniuz.gads2020practiceapp.presentation.submission

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.geniuz.gads2020practiceapp.R
import com.geniuz.gads2020practiceapp.showErrorMessage
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_submission.*


class SubmissionFragment : Fragment() {

    private val viewModel: SubmissionViewModel by viewModels()
    private var dialog: AlertDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            state.run {
                isError.getValueIfFirstTme().takeIf { it == true }?.let {
                    submission_progressbar.visibility = GONE
                    requireView().showErrorMessage(errorMessage)
                }

                isSubmitted.getValueIfFirstTme().takeIf { it == true }?.let {
                    requireView().showErrorMessage(errorMessage)
                    submission_progressbar.visibility = GONE
                    showSuccessSubmission()
                }

                isSubmissionFailed.getValueIfFirstTme().takeIf { it == true }?.let {
                    submission_progressbar.visibility = GONE
                    requireView().showErrorMessage(errorMessage)
                    showFailedSubmission()
                }
            }
        })


        return inflater.inflate(R.layout.fragment_submission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submit_btn.setOnClickListener {
            val firstName = fName_input.text?.toString()
            val lName = lName_input.text?.toString()
            val email = email_input.text?.toString()
            val link =  link_input.text?.toString()

            val inputs = arrayOf(firstName, lName, email, link)

            if (inputs.any { it.isNullOrEmpty() }) {
                requireView().showErrorMessage(
                    "Please complete all fields before submitting"
                )
                return@setOnClickListener
            }


            dialog = AlertDialog.Builder(requireContext())
                .setView(
                    LayoutInflater.from(requireContext()).inflate(
                        R.layout.layout_submit_confirmation, null
                    )
                ).create()

            dialog?.show()

            dialog?.findViewById<MaterialButton>(R.id.yes_button)?.setOnClickListener {
                dialog?.dismiss()
                viewModel.submitForm(
                    firstName = firstName!!,
                    lastName = lName!!,
                    email = email!!,
                    submissionLink = link!!
                )
            }

            submission_progressbar.visibility = VISIBLE
        }

        submission_toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }


    private fun showSuccessSubmission() {
//        dialog =
            AlertDialog.Builder(requireContext())
            .setView(
                LayoutInflater.from(requireContext()).inflate(
                    R.layout.layout_success, null
                )
            ).create()
                .show()


//        dialog?.show()
    }

    private fun showFailedSubmission() {
//        dialog =

            AlertDialog.Builder(requireContext())
            .setView(
                LayoutInflater.from(requireContext()).inflate(
                    R.layout.layout_not_successful, null
                )
            ).create().show()


//        dialog?.show()
    }

    override fun onDestroyView() {
        dialog?.run {
            if (isShowing) dismiss()
        }
        super.onDestroyView()

    }

}