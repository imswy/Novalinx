package novalinx.module.movie.feature.details

import novalinx.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import novalinx.core.lib.router.module.movie.MovieRouter
import novalinx.module.movie.BR
import novalinx.module.movie.R
import novalinx.module.movie.databinding.MovieFragmentDetailsBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = MovieRouter.PATH_MOVIE_DETAILS_FRAGMENT, group = MovieRouter.GROUP)
class MovieDetailsFragment : BaseLoadingSkeletonFragment<MovieFragmentDetailsBinding, MovieDetailsViewModel>(
    R.layout.movie_fragment_details, BR.viewModel, simple_Agile = {
        // 初始化 MovieDetailsFragment ViewModel 调用
        it.viewModel.initializeMovieDetailsFragment(it as MovieDetailsFragment)
    }
)