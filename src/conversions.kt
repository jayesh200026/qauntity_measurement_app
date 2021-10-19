package com.example.quantitymeasurement

import android.view.View
import android.widget.Spinner


fun convertFromML(view: View?, volume: String, elementId: Int): Double {
    var result = 0.0
    if (view != null) {
        when (view.findViewById<Spinner>(elementId).selectedItem as String) {
            Constants.ML -> {
                result = volume.toDouble()
            }
            Constants.LITER -> {
                result = volume.toDouble() / 1000

            }
            Constants.GALLON -> {
                result = volume.toDouble() / 3785

            }

        }
    }
    return result

}

fun convertFromGallon(view: View?, volume: String, elementId: Int): Double {
    var result = 0.0
    if (view != null) {
        when (view.findViewById<Spinner>(elementId).selectedItem as String) {
            Constants.GALLON -> {
                result = volume.toDouble()
            }
            Constants.LITER -> {
                result = volume.toDouble() * 3.785

            }
            Constants.ML -> {
                result = volume.toDouble() * 3785

            }

        }
    }
    return result

}

fun convertFromLiter(view: View?, volume: String, elementId: Int): Double {
    var result = 0.0
    if (view != null) {
        when (view.findViewById<Spinner>(elementId).selectedItem as String) {
            Constants.LITER -> {
                result = volume.toDouble()
            }
            Constants.GALLON -> {
                result = volume.toDouble() / 3.785

            }
            Constants.ML -> {
                result = volume.toDouble() * 1000
            }

        }
    }
    return result
}

fun convertFromPound(view: View?, weight: String, elementId: Int): Double {
    var result = 0.0
    if (view != null) {
        when (view.findViewById<Spinner>(elementId).selectedItem as String) {
            Constants.POUNDS -> {
                result = weight.toDouble()
            }
            Constants.KG -> {
                result = weight.toDouble() / 2.205

            }
            Constants.GRAMS -> {
                result = weight.toDouble() * 454

            }

        }
    }
    return result

}

fun convertFromGrams(view: View?, weight: String, elementId: Int): Double {
    var result = 0.0
    if (view != null) {

        when (view.findViewById<Spinner>(elementId).selectedItem as String) {
            Constants.GRAMS -> {
                result = weight.toDouble()
            }
            Constants.KG -> {
                result = weight.toDouble() / 1000

            }
            Constants.POUNDS -> {
                result = weight.toDouble() / 454

            }

        }
    }
    return result

}

fun convertFromKG(view: View?, weight: String, elementId: Int): Double {
    var result = 0.0
    if (view != null) {

        when (view.findViewById<Spinner>(elementId).selectedItem as String) {
            Constants.KG -> {
                result = weight.toDouble()
            }
            Constants.GRAMS -> {
                result = weight.toDouble() * 1000

            }
            Constants.POUNDS -> {
                result = weight.toDouble() * 2.205

            }

        }
    }
    return result

}

fun convertFromCM(view: View?, length: String, elementId: Int): Double {
    var result = 0.0
    if (view != null) {
        when (view.findViewById<Spinner>(elementId).selectedItem as String) {
            Constants.CM -> {
                result = length.toDouble()
            }
            Constants.METER -> {
                result = length.toDouble() / 100

            }
            Constants.KM -> {
                result = length.toDouble() / 100000

            }
        }
    }
    return result

}

fun convertFromKm(view: View?, length: String, elementId: Int): Double {
    var result = 0.0
    if (view != null) {
        when (view.findViewById<Spinner>(elementId)?.selectedItem as String) {
            Constants.KM -> {
                result = length.toDouble()
            }
            Constants.METER -> {
                result = length.toDouble() * 1000

            }
            Constants.CM -> {
                result = length.toDouble() * 100000

            }
        }

    }
    return result
}

fun convertFromMeter(view: View?, length: String, elementId: Int): Double {
    var result = 0.0
    when (view?.findViewById<Spinner>(elementId)?.selectedItem as String) {
        Constants.METER -> {
            result = length.toDouble()
        }
        Constants.KM -> {
            result = length.toDouble() / 1000

        }
        Constants.CM -> {
            result = length.toDouble() * 100

        }
    }
    return result
}

fun convertFromKelvin(view: View?, temp: String, elementId: Int): Double {
    var result = 0.0
    if (view != null) {
        when (view.findViewById<Spinner>(elementId).selectedItem as String) {
            Constants.KELVIN -> {
                result = temp.toDouble()
            }
            Constants.CELSIUS -> {
                result = temp.toDouble() - 273.15
            }
            Constants.FAHRENHEIT -> {
                result = (temp.toDouble() - 273.15) * 9 / 5 + 32
            }
        }
    }
    return result
}

fun convertFromFahrenheit(view: View?, temp: String, elementId: Int): Double {
    var result = 0.0
    if (view != null) {
        when (view.findViewById<Spinner>(elementId).selectedItem as String) {
            Constants.FAHRENHEIT -> {
                result = temp.toDouble()
            }
            Constants.CELSIUS -> {
                result = (temp.toDouble() - 32) * 5 / 9

            }
            Constants.KELVIN -> {
                result = (temp.toDouble() - 32) * 5 / 9 + 273.15

            }
        }
    }
    return result
}

fun convertFromCelsius(view: View?, temp: String, elementId: Int): Double {
    var result = 0.0
    if (view != null) {
        when (view.findViewById<Spinner>(elementId).selectedItem as String) {
            Constants.CELSIUS -> {
                result = temp.toDouble()

            }
            Constants.FAHRENHEIT -> {
                result = temp.toDouble() * 1.8 + 32

            }
            Constants.KELVIN -> {
                result = temp.toDouble() + 273.15

            }
        }
    }
    return result
}


fun convertVolume(
    view: View?,
    volume: String,
    spinner: Spinner?,
    elementId: Int
): Double {
    var result = 0.0
    if (volume != "") {
        if (view != null) {
            when (spinner?.selectedItem as String) {
                Constants.LITER -> {
                    result = convertFromLiter(view, volume, elementId)
                }
                Constants.GALLON -> {
                    result = convertFromGallon(view, volume, elementId)
                }
                Constants.ML -> {
                    result = convertFromML(view, volume, elementId)
                }
            }
        }
    }
    return result
}

fun convertMass(
    view: View?,
    weight: String,
    spinner: Spinner?,
    elementId: Int
): Double {
    var result = 0.0
    if (weight != "") {
        if (view != null) {
            when (spinner?.selectedItem as String) {
                Constants.KG -> {
                    result = convertFromKG(view, weight, elementId)
                }
                Constants.GRAMS -> {
                    result = convertFromGrams(view, weight, elementId)
                }
                Constants.POUNDS -> {
                    result = convertFromPound(view, weight, elementId)
                }
            }
        }
    }
    return result
}

fun convertDistance(
    view: View?,
    length: String,
    spinner: Spinner?,
    elementId: Int
): Double {
    var result = 0.0
    if (length != "") {
        if (view != null) {
            when (spinner?.selectedItem as String) {
                Constants.METER -> {
                    result = convertFromMeter(view, length, elementId)
                }
                Constants.KM -> {
                    result = convertFromKm(view, length, elementId)
                }
                Constants.CM -> {
                    result = convertFromCM(view, length, elementId)
                }
            }
        }
    }
    return result
}

fun convertTemp(view: View?, temp: String, spinner: Spinner?, elementId: Int): Double {
    var result = 0.0
    if (temp != "") {
        if (view != null) {
            when (spinner?.selectedItem as String) {
                Constants.CELSIUS -> {
                    result = convertFromCelsius(view, temp, elementId)
                }
                Constants.FAHRENHEIT -> {
                    result = convertFromFahrenheit(view, temp, elementId)
                }
                Constants.KELVIN -> {
                    result = convertFromKelvin(view, temp, elementId)
                }
            }
        }
    }
    return result
}
