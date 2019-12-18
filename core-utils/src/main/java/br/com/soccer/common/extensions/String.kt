package br.com.soccer.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.formatStrDate(baseFormat: String, newFormat: String): String? {
    return this.takeIf { !isNullOrEmpty() }.run {
        val newFormat = SimpleDateFormat(newFormat)
        val date = SimpleDateFormat(baseFormat).parse(this)
        newFormat.format(date)
    }
}

fun String.parseInternationalDateToBr(): String = run {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
    }.run {
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(this)
    }
