package br.com.soccer.common.extensions

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

fun ImageView.loadImageUrl(url: String?) {
    if (url.isNullOrEmpty()) {
        setImageBitmap(null) //todo set blank image
        return
    }

    GlideToVectorYou
        .init()
        .with(context)
        .load(Uri.parse(url), this)

}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}