package br.com.soccer.common.extensions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.startFeatureActivity(activityPath: String) {
    val basePath = "br.com.soccer"
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        setClassName(basePath, basePath.plus(".$activityPath"))
    }
    startActivity(intent)
}
