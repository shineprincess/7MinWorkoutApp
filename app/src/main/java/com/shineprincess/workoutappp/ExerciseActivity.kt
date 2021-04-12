package com.shineprincess.workoutappp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.custom_dialog_box_back.*
import java.util.*


class ExerciseActivity : AppCompatActivity() , TextToSpeech.OnInitListener{

    private var restTimer : CountDownTimer? = null
    private var restProgress = 0
    private var restTimerDuration :Long = 1




    private var exerciseTimer : CountDownTimer? = null
    private var exerciseProgress = 0
//    private var exerciseTimerDuration : Long = 30
    private var exerciseTimerDuration : Long = 1
    private var exerciseList : ArrayList<ExerciseImageInfo> ?=null
    private var currentExerciseImagePositionChange : Int = -1

    private var tts:TextToSpeech? = null

    private var player:MediaPlayer? = null

    private var exerciseAdapter : ExerciseStatusAdapter? = null

    private var mPauseTimeRemaining: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(my_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        val actionBar = supportActionBar
//
//        if(actionBar !=null) {
//            actionBar.setDisplayHomeAsUpEnabled(true)
//        }

        my_toolbar.setNavigationOnClickListener {

            customDialogForBackButton ()
        }



        exerciseList = ExercisesConstants.defaultExerciseList()

        tts = TextToSpeech(this, this)

        setUpRestView()

        setUpExerciseStatusNumbersRecyclerView ()


    }





    override fun onDestroy() {

        if (restTimer !=null) {
            restTimer!!.cancel()
            restProgress=0
        }

        if (exerciseTimer !=null) {
            exerciseTimer!!.cancel()
            exerciseProgress=0
        }

        if (tts !=null) {
            tts!!.stop()
            tts!!.shutdown()
        }

        if(player !=null) {
            player!!.stop()
        }
        super.onDestroy()
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            val result = (tts!!.setLanguage(Locale.US))

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The language specified is not supported")
            }
        } else {
            Log.e("TTS", "Initialization Of language Failed !")
        }

    }



    @SuppressLint("NewApi")
    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }




    private fun setUpRestView () {

        try {
            player = MediaPlayer.create(applicationContext, R.raw.sound)

            player!!.isLooping = false
            player!!.start()
        }catch (e: Exception) {
            e.printStackTrace()
        }

        llRestViewTimer.visibility = View.VISIBLE
        llExerciseTimer.visibility = View.GONE

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress=0
        }
        setUpProgressBar()

        tvUpcomingExerciseName.text = exerciseList!![currentExerciseImagePositionChange + 1].getName()
    }

    private fun setUpProgressBar () {

        progressBar.progress = restProgress
        restTimer = object : CountDownTimer(restTimerDuration * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = restTimerDuration.toInt() - restProgress
                tvTimer.text = (restTimerDuration - restProgress).toString()
            }

            override fun onFinish() {

                currentExerciseImagePositionChange ++

                exerciseList!![currentExerciseImagePositionChange].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setUpExerciseView ()
//                Toast.makeText(this@ExerciseActivity, "Here now we are going to start the exercise",Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    

//Setting up now exercise progress bar up to 30
    private fun setUpExerciseProgressBar () {

        progressBarExercise.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(exerciseTimerDuration * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progressBarExercise.progress = exerciseTimerDuration.toInt()  - exerciseProgress
                tvExerciseTimer.text = (exerciseTimerDuration.toInt() - exerciseProgress).toString()
            }

            override fun onFinish() {

                exerciseList!![currentExerciseImagePositionChange].setIsSelected(false)
                exerciseList!![currentExerciseImagePositionChange].setIsCompleted(true)
                exerciseAdapter!!.notifyDataSetChanged()

//                Toast.makeText(this@ExerciseActivity, "Here we will start the next rest screen",Toast.LENGTH_SHORT).show()
                if(currentExerciseImagePositionChange < exerciseList?.size!!-1) {

                    setUpRestView()
                } else {
//                    Toast.makeText(this@ExerciseActivity,"Congratulations You have successfully completed the exercises Preetties",Toast.LENGTH_SHORT).show()
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    private fun setUpExerciseView () {

        llRestViewTimer.visibility = View.GONE
        llExerciseTimer.visibility = View.VISIBLE

        if(exerciseTimer!= null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        speakOut(exerciseList!![currentExerciseImagePositionChange].getName())

        setUpExerciseProgressBar()

        ivImage.setImageResource(exerciseList!![currentExerciseImagePositionChange].getImage())
        tvExerciseName.text = exerciseList!![currentExerciseImagePositionChange].getName()
    }

    private fun setUpExerciseStatusNumbersRecyclerView () {

        rvExerciseStatus.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!, this)
        rvExerciseStatus.adapter = exerciseAdapter
    }


    private fun customDialogForBackButton() {
        val customDialog = Dialog(this)

        customDialog.setContentView(R.layout.custom_dialog_box_back)
        customDialog.tvYes.setOnClickListener {
//            Go back to the main Activity
            finish()
            customDialog.dismiss()
        }

        customDialog.tvNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()

    }

//    override fun onBackPressed() {
//        exerciseTimer!!.cancel()
//        restTimer!!.cancel()
//        super.onBackPressed()
//    }

}