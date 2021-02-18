package com.example.calculadoranotas

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager.LayoutParams.*
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.example.calculadoranotas.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.btnCalcular.setOnClickListener{

            if (!validFields())
                return@setOnClickListener

            val gradeOne = binding.gradeOne.text.toString().toInt()
            val gradeTwo = binding.gradeTwo.text.toString().toInt()
            val faults = binding.faults.text.toString().toInt()
            val result = binding.showText
            val media = (gradeOne + gradeTwo) / 2

            if (media <= 5 || faults > 10){
                result.text = getString(R.string.result_info_reproved, media, faults)
                result.setTextColor(Color.rgb(100,0,20))
           } else {
               result.text = getString(R.string.result_info_approved, media, faults)
               result.setTextColor(Color.rgb(0,100,0))
           }
            clearField()
            hideKeyboard()
        }
            binding.gradeOne.requestFocus()
    }

    private fun validFields(): Boolean {
        if (binding.gradeOne.text.toString().trim().isEmpty()){
            binding.gradeOne.error = getString(R.string.required_field)
            return false
        }
        if (binding.gradeTwo.text.toString().trim().isEmpty()){
            binding.gradeOne.error = getString(R.string.required_field)
            return false
        }
        if (binding.faults.text.toString().trim().isEmpty()){
            binding.gradeOne.error = getString(R.string.required_field)
            return false
        }
        return true
    }

    private fun clearField(){
        binding.gradeOne.text?.clear()
        binding.gradeOne.requestFocus()
        binding.gradeTwo.text?.clear()
        binding.faults.text?.clear()
    }

    private fun hideKeyboard(){
        val view = this.currentFocus
        if (view != null){
            val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(view.windowToken,0)
        }
        window.setSoftInputMode(SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}