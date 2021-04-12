package com.shineprincess.workoutappp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmi.*
import java.math.BigDecimal
import java.math.RoundingMode

class BmiActivity : AppCompatActivity() {

    val METRIC_UNITS_VIEW = "METRIC_uNIT_VIEW"
    val US_UNIT_VIEW = "US_UNIT_VIEW"

    var currentVisible: String = METRIC_UNITS_VIEW


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)


        setSupportActionBar(my_toolbar_BMI_activity)

        val actionbar = supportActionBar

        if(actionbar !=null) {
//            this will set the back button of our action bar
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "CALCULATE BMI"
        }

        my_toolbar_BMI_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btn_CalculateBMI.setOnClickListener {

            if (currentVisible.equals(METRIC_UNITS_VIEW)) {
                if (validateBMIUNits()) {
//                divide by 100 to convert it in meters
                    val heightValue : Float = etBMIHeightCalc.text.toString().toFloat()  / 100

                    val weightValue : Float = etBMIWeightCalc.text.toString().toFloat()

                    val bmi = weightValue / (heightValue * heightValue)

                    displayBMIResult(bmi)
                } else {
                    Toast.makeText(this, "Please enter valid values. ", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (validateUSUnits()) {

                    val heightInchUsValue: String = etsUnitHeightInch.text.toString()

                    val heightFeetUSValue : String = etUsUnitsHeightFeet.text.toString()

                    val weighInPoundsUsValue : Float = etUsUnitWeight.text.toString().toFloat()

                    val heightValue = heightInchUsValue.toFloat() + heightFeetUSValue .toFloat() * 12

                    val bmiUs : Float =  703 * (weighInPoundsUsValue / (heightValue * heightValue))

                    displayBMIResult(bmiUs)


                } else {
                    Toast.makeText(this, "Please enter valid values. ", Toast.LENGTH_SHORT).show()
                }
            }




        }


        makeVisibleMetricUnitsView()
        radioGroupBMIUnits.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioButtonBMIMetricUnits) {
                makeVisibleMetricUnitsView()
            }else {
                makeVisibleUSUnitsView()
            }
        }

    }


    private fun makeVisibleMetricUnitsView() {
        currentVisible = METRIC_UNITS_VIEW
        tilBMIHeightCalc.visibility = View.VISIBLE
        tilBMIWeighCalc.visibility = View.VISIBLE
        llDisplayBMIResult.visibility = View.INVISIBLE


        etBMIHeightCalc.text?.clear()
        etBMIWeightCalc.text?.clear()

        tilUsUnitWeight.visibility = View.GONE
        llUSUnitsHeight.visibility = View.GONE

//        tvYourBMI.visibility = View.INVISIBLE
//        tvBMIValue.visibility = View.INVISIBLE
//        tvBMIType.visibility = View.INVISIBLE
//        tvBMIDescription.visibility = View.INVISIBLE

    }

    private fun makeVisibleUSUnitsView() {
        currentVisible = US_UNIT_VIEW

        tilBMIHeightCalc.visibility = View.GONE
        tilBMIWeighCalc.visibility = View.GONE
        llDisplayBMIResult.visibility = View.INVISIBLE

        etUsUnitWeight.text!!.clear()
        etUsUnitsHeightFeet.text!!.clear()
        etsUnitHeightInch.text?.clear()

        tilUsUnitWeight.visibility = View.VISIBLE
        llUSUnitsHeight.visibility = View.VISIBLE


    }

    private fun validateUSUnits() : Boolean {
        var isUsValid = true

        when {
            etUsUnitWeight.text.toString().isEmpty() -> isUsValid = false
            etUsUnitsHeightFeet.text.toString().isEmpty() -> isUsValid = false
            etsUnitHeightInch.text.toString().isEmpty() -> isUsValid = false
        }

        return isUsValid
    }


    private fun validateBMIUNits() : Boolean {
        var isValid = true

        if (etBMIHeightCalc.text.toString().isEmpty())
            isValid = false
        else if (etBMIWeightCalc.text.toString().isEmpty())
            isValid = false

        return isValid
    }

    private fun displayBMIResult(bmi : Float) {
        val bmiType: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiType = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiType = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiType = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiType = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30) <= 0) {
            bmiType = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiType = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiType = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiType = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

//        tvYourBMI.visibility = View.VISIBLE
//        tvBMIValue.visibility = View.VISIBLE
//        tvBMIType.visibility = View.VISIBLE
//        tvBMIDescription.visibility = View.VISIBLE

        llDisplayBMIResult.visibility = View.VISIBLE

//        Limit Values to the user
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        tvBMIValue.text = bmiValue
        tvBMIType.text = bmiType
        tvBMIDescription.text = bmiDescription

    }
}