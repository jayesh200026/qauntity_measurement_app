package com.example.quantitymeasurement

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.text.DecimalFormat


class AdditionFragment : Fragment(), AdapterView.OnItemSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_addition, container, false)
        val addSpinner = view.findViewById<Spinner>(R.id.addMainSpinner)
        val addValue1 = view.findViewById<Spinner>(R.id.addSpinner1)
        val addValue2 = view.findViewById<Spinner>(R.id.addSpinner2)
        val addSResult = view.findViewById<Spinner>(R.id.addResultSpinner)
        addSpinner.onItemSelectedListener = this
        addValue1.onItemSelectedListener = this
        addValue2.onItemSelectedListener = this
        addSResult.onItemSelectedListener = this
        val arrayAdapter =
            ArrayAdapter<String>(
                view.context,
                android.R.layout.simple_spinner_item,
                Constants.UNITS
            )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        addSpinner.adapter = arrayAdapter

        val value1 = view.findViewById<EditText>(R.id.addInput1)
        val value2 = view.findViewById<EditText>(R.id.addInput2)
        value1.afterTextChanged {
            if (value1.text != null) {
                computeResult(view, value1.text, value2.text, addSpinner)
            }
        }
        value2.afterTextChanged {
            if (value2.text != null) {
                computeResult(view, value1.text, value2.text, addSpinner)
            }
        }
        return view
    }


    private fun computeResult(
        view: View?,
        value1: Editable,
        value2: Editable,
        addSpinner: Spinner?
    ) {
        val addValue1 = view?.findViewById<Spinner>(R.id.addSpinner1)
        val addValue2 = view?.findViewById<Spinner>(R.id.addSpinner2)
        val spinnerId = R.id.addResultSpinner
        var converted1 = 0.0
        var converted2 = 0.0

        if (value1.toString() == "" && value2.toString() == "") {
            printNoValue()
            return
        }
        when (addSpinner?.selectedItemPosition) {
            Constants.TEMPERATURE -> {
                converted1 = convertTemp(view, value1.toString(), addValue1, spinnerId)
                converted2 = convertTemp(view, value2.toString(), addValue2, spinnerId)
            }
            Constants.DISTANCE -> {
                converted1 = convertDistance(view, value1.toString(), addValue1, spinnerId)
                converted2 = convertDistance(view, value2.toString(), addValue2, spinnerId)
            }
            Constants.MASS -> {
                converted1 = convertMass(view, value1.toString(), addValue1, spinnerId)
                converted2 = convertMass(view, value2.toString(), addValue2, spinnerId)
            }
            Constants.VOLUME -> {
                converted1 = convertVolume(view, value1.toString(), addValue1, spinnerId)
                converted2 = convertVolume(view, value2.toString(), addValue2, spinnerId)
            }
        }
        val output = view?.findViewById<TextView>(R.id.addResult)
        val total = converted1 + converted2
        printTotal(total, output)
    }

    private fun printNoValue() {
        val output = view?.findViewById<TextView>(R.id.addResult)
        output?.text = ""
    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val mainType = view?.findViewById<Spinner>(R.id.addMainSpinner)
        val index = mainType?.selectedItemPosition
        val addValue1 = view?.findViewById<Spinner>(R.id.addSpinner1)
        val addValue2 = view?.findViewById<Spinner>(R.id.addSpinner2)
        val value1 = view?.findViewById<EditText>(R.id.addInput1)
        val value2 = view?.findViewById<EditText>(R.id.addInput2)
        val output = view?.findViewById<TextView>(R.id.addResult)
        var total = 0.0
        when (p0?.id) {
            R.id.addMainSpinner -> {
                value1?.setText("")
                value2?.setText("")
                loadSpinners(p2)
            }
            R.id.addSpinner1 -> {
                total = totalFromSpinnerChange(index, value1, value2, view, addValue1, addValue2)
            }
            R.id.addSpinner2 -> {
                total = totalFromSpinnerChange(index, value1, value2, view, addValue1, addValue2)
            }
            R.id.addResultSpinner -> {
                total = totalFromSpinnerChange(index, value1, value2, view, addValue1, addValue2)
            }
        }
        if (value1?.text.toString() == "" && value2?.text.toString() == "") {
            printNoValue()
        } else {
            printTotal(total, output)
        }

    }

    private fun loadSpinners(p2: Int) {
        when (p2) {
            Constants.TEMPERATURE -> {
                loadRemainingSpinners(R.id.addSpinner1, Constants.TEMPERATURE_VALUES)
                loadRemainingSpinners(R.id.addSpinner2, Constants.TEMPERATURE_VALUES)
                loadRemainingSpinners(R.id.addResultSpinner, Constants.TEMPERATURE_VALUES)
            }
            Constants.DISTANCE -> {
                loadRemainingSpinners(R.id.addSpinner1, Constants.DISTANCE_VALUES)
                loadRemainingSpinners(R.id.addSpinner2, Constants.DISTANCE_VALUES)
                loadRemainingSpinners(R.id.addResultSpinner, Constants.DISTANCE_VALUES)
            }
            Constants.MASS -> {
                loadRemainingSpinners(R.id.addSpinner1, Constants.MASS_VALUES)
                loadRemainingSpinners(R.id.addSpinner2, Constants.MASS_VALUES)
                loadRemainingSpinners(R.id.addResultSpinner, Constants.MASS_VALUES)
            }
            Constants.VOLUME -> {
                loadRemainingSpinners(R.id.addSpinner1, Constants.VOLUME_VALUES)
                loadRemainingSpinners(R.id.addSpinner2, Constants.VOLUME_VALUES)
                loadRemainingSpinners(R.id.addResultSpinner, Constants.VOLUME_VALUES)
            }
        }

    }

    private fun printTotal(res: Double, output: TextView?) {

        if (res.toInt() > 99999) {
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


    private fun totalFromSpinnerChange(
        index: Int?,
        value1: EditText?,
        value2: EditText?,
        view: View?,
        addValue1: Spinner?,
        addValue2: Spinner?
    ): Double {
        var converted1 = 0.0
        var converted2 = 0.0

        if (value1?.text != null && value2?.text != null) {
            val input1 = value1.text.toString()
            val input2 = value2.text.toString()
            val spinnerId = R.id.addResultSpinner
            when (index) {
                Constants.TEMPERATURE -> {
                    converted1 = convertTemp(view, input1, addValue1, spinnerId)
                    converted2 = convertTemp(view, input2, addValue2, spinnerId)
                }
                Constants.DISTANCE -> {
                    converted1 = convertDistance(view, input1, addValue1, spinnerId)
                    converted2 = convertDistance(view, input2, addValue2, spinnerId)

                }
                Constants.MASS -> {
                    converted1 = convertMass(view, input1, addValue1, spinnerId)
                    converted2 = convertMass(view, input2, addValue2, spinnerId)

                }
                Constants.VOLUME -> {
                    converted1 = convertVolume(view, input1, addValue1, spinnerId)
                    converted2 = convertVolume(view, input2, addValue2, spinnerId)

                }
            }
        }
        return converted1 + converted2
    }

    private fun loadRemainingSpinners(elementId: Int, listOfValues: ArrayList<String?>) {
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
}


