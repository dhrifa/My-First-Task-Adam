package com.example.myfirsttaskadam.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {

    fun String.toDate(
        inputFormat: String = "yyyy-MM-dd'T'HH:mm:ss",
        outputFormat: String = "dd/MM/yyyy"
    ): String {
        val parser = SimpleDateFormat(inputFormat)
        val formatter = SimpleDateFormat(outputFormat)
        return formatter.format(parser.parse(this))

    }
}