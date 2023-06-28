package com.invoice.contratista.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.getDate(): String {
    val date = SimpleDateFormat(DATE_FORMAT).parse(this)
    val formatter = SimpleDateFormat("dd-MM-yyyy")
    return formatter.format(date!!)
}

fun String.getHour(): String {
    val date = SimpleDateFormat(DATE_FORMAT).parse(this)
    val formatter = SimpleDateFormat("HH:mm")
    return formatter.format(date!!)
}

fun Date.getDateComplete(): String {
    val formatter = SimpleDateFormat(DATE_FORMAT)
    return formatter.format(this)
}

fun Date.getDateWithoutHour(): String {
    val formatter = SimpleDateFormat("dd-MM-yyyy")
    return formatter.format(this)
}