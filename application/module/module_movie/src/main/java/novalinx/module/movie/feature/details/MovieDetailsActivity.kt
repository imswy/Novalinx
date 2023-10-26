package novalinx.module.movie.feature.details

import novalinx.core.lib.base.app.BaseAppActivity
import novalinx.core.lib.base.controller.ui.theme.defaultActivityBlankUITheme
import novalinx.core.lib.router.module.movie.MovieRouter
import novalinx.module.movie.BR
import novalinx.module.movie.MovieViewModel
import novalinx.module.movie.R
import novalinx.module.movie.databinding.MovieActivityDetailsBinding
import androidx.navigation.fragment.NavHostFragment
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = MovieRouter.PATH_MOVIE_DETAILS_ACTIVITY, group = MovieRouter.GROUP)
class MovieDetailsActivity : BaseAppActivity<MovieActivityDetailsBinding, MovieViewModel>(
    R.layout.movie_activity_details, BR.viewModel, simple_UITheme = {
        it.defaultActivityBlankUITheme()
    }, simple_Agile = {
        val fragment = it.supportFragmentManager.findFragmentById(
            it.binding.navContainer.id
        ) as NavHostFragment
        val navController = fragment.navController
        navController.navigate(R.id.MovieDetailsFragment, it.intent.extras)
    }
)