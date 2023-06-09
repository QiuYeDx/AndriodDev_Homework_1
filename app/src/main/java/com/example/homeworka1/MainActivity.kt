package com.example.homeworka1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.homeworka1.ui.theme.HomeWorkA1Theme
import kotlinx.android.synthetic.main.first_layout.exit_button
import kotlinx.android.synthetic.main.first_layout.login_button
import kotlinx.android.synthetic.main.first_layout.password_input
import kotlinx.android.synthetic.main.first_layout.show_someone
import kotlinx.android.synthetic.main.first_layout.show_time
import kotlinx.android.synthetic.main.first_layout.username_input
import java.io.Serializable
import java.util.Calendar
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> if(resultCode == RESULT_OK){
                val returnedData = data?.getStringExtra("data_return")
                show_someone.setText(returnedData + "知道你的信息")
                Log.d("FirstActivity", "returned data is $returnedData")
            }
        }
    }

    class Person: Serializable{
        var username = ""
        var password = ""
    }

    lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeWorkA1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        setContentView(R.layout.first_layout)
        val calendar: Calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)
        show_time.setText("当前时间为 " + hour.toString() + "点" + minute.toString() + "分")

        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)

        login_button.setOnClickListener {
            if(username_input.text.toString() != ""){
                if(password_input.text.toString() != ""){
                    Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show()
                    val person = Person()
                    person.username = username_input.text.toString()
                    person.password = password_input.text.toString()
                    val intent = Intent(this, MainActivity2::class.java)
                    intent.putExtra("person_data", person)
                    startActivityForResult(intent, 1)
//                    val intent = Intent(this, MainActivity2::class.java)
//                    intent.putExtra("username", username_input.text.toString())
//                    intent.putExtra("password", password_input.text.toString())
//                    startActivityForResult(intent, 1)
                }else{
                    Toast.makeText(this, "请输入密码!", Toast.LENGTH_SHORT).show()
                }
            }else{
                if(password_input.text.toString() != ""){
                    Toast.makeText(this, "请输入账号!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "请输入账号和密码!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        exit_button.setOnClickListener {
            finishAffinity() // 关闭所有活动（Activity）
            exitProcess(0) // 终止当前进程
        }

    }

    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            println("!!!")
            val calendar: Calendar = Calendar.getInstance()
            val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
            val minute: Int = calendar.get(Calendar.MINUTE)
            show_time.setText("当前时间为 " + hour.toString() + "点" + minute.toString() + "分")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeWorkA1Theme {
        Greeting("Android")
    }
}