package com.example.stopwatch

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var timerTextView: TextView
    private lateinit var startButton: Button
    private lateinit var resetTextView: TextView
    private lateinit var titleTv: TextView
    private lateinit var progressBar: ProgressBar

    private var countDownTimer: CountDownTimer? = null
    private var isRunning = false
    private var elapsedTimeInMillis: Long = 0 // To keep track of elapsed time
    private val interval: Long = 1000 // Timer interval (1 second)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerTextView = findViewById(R.id.timer_tv)
        startButton = findViewById(R.id.start_button)
        resetTextView = findViewById(R.id.reset_tv)
        titleTv = findViewById(R.id.title_tv)
        progressBar = findViewById(R.id.progressBar)

        startButton.setOnClickListener {
            if (isRunning) {
                pauseTimer()
            } else {
//                timerTextView.text = resources.getText(R.string.Keep_going)
                startTimer()


            }
        }

        resetTextView.setOnClickListener {
            resetTimer()
        }

        updateTimerText(0)
        progressBar.max = 100 // Set max value for the progress bar (for example, 100 seconds)
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(Long.MAX_VALUE, interval) {
            override fun onTick(millisUntilFinished: Long) {
                elapsedTimeInMillis += interval
                updateTimerText(elapsedTimeInMillis)
                updateProgressBar(elapsedTimeInMillis)
                titleTv.text = resources.getText(R.string.Keep_going)


            }

            override fun onFinish() {
                // Not used for continuous stopwatch
            }
        }.start()

        isRunning = true
        startButton.text = "pause"
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        isRunning = false
        startButton.text = getString(R.string.start)
        titleTv.text = resources.getText(R.string.Take_Pomodoro)
    }

    private fun resetTimer() {
        countDownTimer?.cancel()
        elapsedTimeInMillis = 0
        updateTimerText(elapsedTimeInMillis)
        updateProgressBar(elapsedTimeInMillis)
        isRunning = false
        startButton.text = getString(R.string.start)
        titleTv.text = resources.getText(R.string.Take_Pomodoro)

    }

    private fun updateTimerText(timeInMillis: Long) {
        val seconds = (timeInMillis / 1000 % 60).toInt()
        val minutes = (timeInMillis / 1000 / 60).toInt()
        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        timerTextView.text = timeFormatted
    }

    private fun updateProgressBar(timeInMillis: Long) {
        val progress = (timeInMillis / 1000).toInt()
        progressBar.progress = progress
    }
}


/***
 * //package com.example.stopwatch
 * //
 * //import android.os.Bundle
 * //import android.os.CountDownTimer
 * //import android.widget.Button
 * //import android.widget.ProgressBar
 * //import android.widget.TextView
 * //import androidx.activity.enableEdgeToEdge
 * //import androidx.appcompat.app.AppCompatActivity
 * //
 * //class MainActivity : AppCompatActivity() {
 * //    lateinit var title_tv : TextView
 * //    lateinit var time_tv : TextView
 * //    lateinit var start_bt : Button
 * //    lateinit var reset_bt : TextView
 * //   lateinit var circularProgressbar : ProgressBar
 * //    val startTime = System.currentTimeMillis()
 * //    var elapsedMillis = System.currentTimeMillis() - startTime
 * //
 * //    override fun onCreate(savedInstanceState: Bundle?) {
 * //        super.onCreate(savedInstanceState)
 * //        enableEdgeToEdge()
 * //        setContentView(R.layout.activity_main)
 * //        title_tv = findViewById(R.id.title_tv)
 * //        time_tv = findViewById(R.id.timer_tv)
 * //        start_bt = findViewById(R.id.start_button)
 * //        reset_bt = findViewById(R.id.reset_tv)
 * //
 * //        start_bt.setOnClickListener {
 * //
 * //
 * //           var timer =  object : CountDownTimer(Long.MAX_VALUE, 1000) {
 * //                override fun onTick(millisUntilFinished: Long) {
 * //                    elapsedMillis = millisUntilFinished
 * //                    // حساب الدقائق والثواني
 * //                    updateTimerText()
 * //
 * //                    // تحديث التقدم في circularProgressbar (هنا يتم استخدام 1 دقيقة كحد أقصى)
 * //
 * //                }
 * //
 * //                override fun onFinish() {
 * //                    // تنفيذ أي إجراءات إذا انتهى المؤقت
 * //                }
 * //            }.start()
 * //        }
 * //
 * //
 * //
 * //    }
 * //
 * //    fun updateTimerText(){
 * //        val seconds = (elapsedMillis / 1000).toInt() % 60
 * //        val minutes = (elapsedMillis / 1000 / 60).toInt()
 * //        // تحديث الوقت في TextView
 * //        var formattedTime = String.format("%02d:%02d", minutes, seconds)
 * //        time_tv.text = formattedTime
 * //    }
 * //
 * //}
 */
