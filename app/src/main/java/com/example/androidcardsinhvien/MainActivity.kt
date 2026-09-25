package com.example.androidcardsinhvien

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcardsinhvien.databinding.ActivityMainBinding
import com.example.androidcardsinhvien.model.Student

class MainActivity : AppCompatActivity() {

    // Khai báo ViewBinding
    private lateinit var binding: ActivityMainBinding

    // Thông tin sinh viên
    private var currentStudent = Student(
        id = "2415053122343",
        name = "Luu Ngoc Tran",
        className = "24T3",
        email = "2415053122343@sv.ute.udn.vn",
        gpa = 3.8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hiển thị thông tin sinh viên
        bindStudentData(currentStudent)

        // Xử lý nút cập nhật GPA
        binding.btnUpdateGpa.setOnClickListener {

            val newGpa = binding.edtNewGpa.text.toString().toDoubleOrNull()

            if (newGpa == null || newGpa < 0.0 || newGpa > 4.0) {
                Toast.makeText(
                    this,
                    "GPA phải nằm trong khoảng 0.0 - 4.0",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Tạo sinh viên mới với GPA mới
            currentStudent = currentStudent.copy(gpa = newGpa)

            // Hiển thị lại thông tin
            bindStudentData(currentStudent)

            Toast.makeText(
                this,
                "Đã cập nhật GPA",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Hàm hiển thị thông tin sinh viên lên giao diện
    private fun bindStudentData(student: Student) {

        binding.tvName.text = student.name

        binding.tvStudentId.text = "MSSV: ${student.id}"

        binding.tvClassName.text = "Lớp: ${student.className}"

        binding.tvEmail.text = student.email

        binding.tvGpaBadge.text = "GPA: ${student.gpa}"

        binding.edtNewGpa.setText(student.gpa.toString())
    }
}
