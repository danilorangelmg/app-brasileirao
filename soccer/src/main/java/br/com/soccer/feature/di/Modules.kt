import br.com.soccer.feature.game.GameViewModel
import br.com.soccer.feature.game.bottomSheet.GameBottomSheetViewModel
import br.com.soccer.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun loadKoinFeatures() = loadFeatures

fun unloadKoinFeatures() = org.koin.core.context.unloadKoinModules(listOf(movieModule))

private val loadFeatures by lazy {
    loadKoinModules(
        listOf(movieModule)
    )
}

val movieModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { GameViewModel(get()) }
    viewModel { GameBottomSheetViewModel(get()) }
}

