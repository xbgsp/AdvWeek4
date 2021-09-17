package com.example.AdvWeek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.AdvWeek4.R
import com.example.AdvWeek4.util.loadImage
import com.example.AdvWeek4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*

class StudentDetailFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null){
            val id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
            viewModel.detail(id)

            viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
                txtId.setText(it[0].id)
                txtName.setText(it[0].name)
                txtBod.setText(it[0].bod)
                txtPhone.setText(it[0].phone)
                imageViewStudentDetail.loadImage(it[0].photoUrl.toString(), progressBarStudentDetail)
            })

        }

        btnUpdate.setOnClickListener {
            val action = StudentDetailFragmentDirections.actionStudentList()
            Navigation.findNavController(it).navigate(action)
        }
    }
}