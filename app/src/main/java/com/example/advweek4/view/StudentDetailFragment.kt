package com.example.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.advweek4.R
import com.example.advweek4.util.loadImage
import com.example.advweek4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import java.util.concurrent.TimeUnit
import android.view.Gravity




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

                var student = it[0]
                btnNotif.setOnClickListener {
                    val toast = Toast.makeText(context,
                        "Notification Success!\nCheck on your status bar!", Toast.LENGTH_SHORT)
                    toast.show()
                    Observable.timer(5, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            MainActivity.showNotification(student.name.toString(),
                                "A new notification created",
                                R.drawable.ic_baseline_person_24)
                        }
                }
            })

        }

        btnUpdate.setOnClickListener {
            val toast = Toast.makeText(context, "Update Success!", Toast.LENGTH_SHORT)
            toast.show()
            val action = StudentDetailFragmentDirections.actionStudentList()
            Navigation.findNavController(it).navigate(action)
        }
    }
}