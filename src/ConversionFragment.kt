package com.example.quantitymeasurement

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.text.TextWatcher
import java.text.DecimalFormat

class ConversionFragment : Fragment(), AdapterView.OnItemSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_conversion, container, false)
        val spin = view.findViewById<Spinner>(R.id.quantities)
        val secondSpin = view.findViewById<Spinner>(R.id.inputType)
        val thirdSpin = view.findViewById<Spinner>(R.id.outputType)
        spin.onItemSelectedListener = this
        secondSpin.onItemSelectedListener = this
        thirdSpin.onItemSelectedListener = this

        val arrayAdapter =
            ArrayAdapter<String>(
                view.context,
                android.R.layout.simple_spinner_item,
                Constants.UNITS
            )

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = arrayAdapter
        val input = view.findViewById<EditText>(R.id.inputValue)
        input.afterTextChanged {
            if (input.text != null)
                convertQt(view, input.text, spin)
        }
        return view
    }

    private fun convertQt(view: View?, text: Editable, spin: Spinner?) {
        val spinner = view?.findViewById<Spinner>(R.id.inputType)
        if (text.toString() == "") {
            noNumberToPrint()
            return
        }
        var res = 0.0
        if (text.toString() != "") {
            res = when (spin?.selectedItemPosition) {
                Constants.TEMPERATURE -> convertTemp(
                    view,
                    text.toString(),
                    spinner,
                    R.id.outputType
                )
                Constants.DISTANCE -> convertDistance(
                    view,
                    text.toString(),
                    spinner,
                    R.id.outputType
                )
                Constants.MASS -> convertMass(view, text.toString(), spinner, R.id.outputType)
                Constants.VOLUME -> convertVolume(view, text.toString(), spinner, R.id.outputType)
                else -> 0.0
            }
        }
        printConvertedResult(res)
    }

    private fun noNumberToPrint() {
        val output = view?.findViewById<TextView>(R.id.textView)
        output?.text = ""
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
        val tempSpin = view?.findViewById<Spinner>(R.id.quantities)
        val index = tempSpin?.selectedItemPosition
        val inputView = view?.findViewById<EditText>(R.id.inputValue)

        when (p0?.id) {
            R.id.quantities -> {
                loadSpinners(pos, inputView)
            }
            R.id.outputType -> {
                convertFromSpinner(index, inputView)
            }
            R.id.inputType -> {
                convertFromSpinner(index, inputView)
            }
        }
    }

    private fun convertFromSpinner(index: Int?, inputView: EditText?) {
        if (inputView?.text.toString() == "") {
            noNumberToPrint()
            return
        }
        var res = 0.0
        val spinner = view?.findViewById<Spinner>(R.id.inputType)
        val inputValue = inputView?.text.toString()
        val spinnerId = R.id.outputType
        when (index) {
            Constants.TEMPERATURE -> {
                res = convertTemp(view, inputValue, spinner, spinnerId)
            }
            Constants.DISTANCE -> {
                res = convertDistance(view, inputValue, spinner, spinnerId)
            }
            Constants.MASS -> {
                res = convertMass(view, inputValue, spinner, spinnerId)
            }
            Constants.VOLUME -> {
                res = convertVolume(view, inputValue, spinner, spinnerId)
            }
        }
        printConvertedResult(res)

    }

    private fun loadSpinners(pos: Int, inputView: EditText?) {
        inputView?.setText("")
        val output = view?.findViewById<TextView>(R.id.textView)
        output?.text = ""
        when (pos) {
            Constants.TEMPERATURE -> {
                loadTempSpin(R.id.inputType, Constants.TEMPERATURE_VALUES)
                loadTempSpin(R.id.outputType, Constants.TEMPERATURE_VALUES)
            }
            Constants.DISTANCE -> {
                loadTempSpin(R.id.inputType, Constants.DISTANCE_VALUES)
                loadTempSpin(R.id.outputType, Constants.DISTANCE_VALUES)
            }
            Constants.MASS -> {
                loadTempSpin(R.id.inputType, Constants.MASS_VALUES)
                loadTempSpin(R.id.outputType, Constants.MASS_VALUES)
            }
            Constants.VOLUME -> {
                loadTempSpin(R.id.inputType, Constants.VOLUME_VALUES)
                loadTempSpin(R.id.outputType, Constants.VOLUME_VALUES)
            }
        }
    }

    private fun loadTempSpin(elementId: Int, listOfValues: ArrayList<String?>) {
        val tempSpin = view?.findViewById<Spinner>(elementId)
        val tempAdapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_item,
            listOfValues
        )

        tempAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tempSpin?.adapter = tempAdapter

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun printConvertedResult(res: Double) {
        val output = view?.findViewById<TextView>(R.id.textView)
        if (res.toInt() > 9999999) {
            val numberFormat = DecimalFormat("0.###E0")
            val exponentialResult = numberFormat.format(res)
            output?.text = exponentialResult
        } else if ((res > 0 && res < 1)) {
            if (res.toString().length < 7) {
                output?.text = res.toString()
            } else {
                val numberFormat = DecimalFormat("0.###E0")
                val exponentialResult = numberFormat.format(res)
                output?.text = exponentialResult
            }
        } else {
            val formatted = String.format("%.2f", res)
            output?.text = formatted
        }
    }

}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

