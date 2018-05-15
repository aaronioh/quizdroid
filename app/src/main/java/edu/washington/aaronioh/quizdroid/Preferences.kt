package edu.washington.aaronioh.quizdroid

import android.Manifest
import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.*

class Preferences : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val url = findViewById<EditText>(R.id.editURL) as EditText
        val submitURL = findViewById<Button>(R.id.buttonSubmitURL) as Button
        val updateTime = findViewById<Spinner>(R.id.spinnerUpdateTime) as Spinner

        val timeList = arrayOf("5 minutes", "15 minutes", "30 minutes", "60 minutes")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        updateTime.adapter = adapter

        url.setText("http://tednewardsandbox.site44.com/questions.json")

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, FileReceiver::class.java)

        submitURL.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.i("Preferences", "Permission check")
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
            }
            val airplaneMode = Settings.System.getInt(this.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0)
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkAvailable = connectivityManager.activeNetworkInfo != null
            val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            Log.i("Preferences", "Write Permissions: " + permission)
            if (airplaneMode == 1) {
                val builder = AlertDialog.Builder(this)
                val inflater = this.layoutInflater
                builder.setTitle("Airplane Mode is on, please turn it off")
                builder.setPositiveButton("Settings",
                        { dialog, which -> startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))})
                builder.setNegativeButton("Cancel", { dialog, which -> })
                builder.create().show()
            } else if (!networkAvailable) {
                Toast.makeText(this, "Error: no access to Internet", Toast.LENGTH_SHORT).show()
            } else {
                Log.i("Preferences", "Download from URL")
                intent.putExtra("url", url.text.toString())
                val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                val interval = updateTime.selectedItem.toString().split(" ")[0].toLong() * 60 * 1000
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent)
                Toast.makeText(this, "Attempting download from " + url.text, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
