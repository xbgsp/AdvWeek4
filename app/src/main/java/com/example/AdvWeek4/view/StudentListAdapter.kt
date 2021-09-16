package com.example.AdvWeek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.AdvWeek4.R
import com.example.AdvWeek4.model.Student
import com.example.AdvWeek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studenList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    class StudentViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateStudentList(newStudentList: List<Student>) {
        studenList.clear()
        studenList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.txtId.text = studenList[position].id
        holder.view.txtName.text = studenList[position].name

        holder.view.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionDetail()
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.imageView.loadImage(studenList[position].photoUrl,
            holder.view.progressBar)
    }

    override fun getItemCount(): Int {
        return studenList.size
    }
}

