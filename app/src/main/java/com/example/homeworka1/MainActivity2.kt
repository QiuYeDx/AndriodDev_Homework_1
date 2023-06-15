package com.example.homeworka1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.second_layout.back_button
import kotlinx.android.synthetic.main.second_layout.password_input
import kotlinx.android.synthetic.main.second_layout.time_text
import kotlinx.android.synthetic.main.second_layout.username_input
import kotlinx.android.synthetic.main.second_layout.username_show

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)

        val person = intent.getSerializableExtra("person_data") as MainActivity.Person
        username_input.setText(person.username)
        password_input.setText(person.password)
        username_show.setText(person.username)
//        val username = intent.getStringExtra("username")
//        username_input.setText(username)
//        val password = intent.getStringExtra("password")
//        password_input.setText(password)
//        username_show.setText(username)
        back_button.setOnClickListener{
            val intent = Intent()
//            intent.putExtra("data_return", username)
            intent.putExtra("data_return", person.username)
            setResult(RESULT_OK, intent)
            finish()
        }
        time_text.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }
    }
}