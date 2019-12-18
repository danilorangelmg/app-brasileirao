package br.com.soccer.feature.helper

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import br.com.soccer.feature.SoccerActivity

fun Fragment.updateToolbarTitle(@StringRes title: Int) {
    (activity as SoccerActivity).toolbarTitleLiveData.value = getString(title)
}

fun Fragment.showBackButtonToolbar(showBackButtonToolbar: Boolean) {
    (activity as SoccerActivity).showBackButtonToolbarLiveData.value = showBackButtonToolbar
}

fun Fragment.showGameButtonToolbar(showGameButtonToolbar: Boolean) {
    (activity as SoccerActivity).gameButtonToolbarLiveData.value = showGameButtonToolbar
}
