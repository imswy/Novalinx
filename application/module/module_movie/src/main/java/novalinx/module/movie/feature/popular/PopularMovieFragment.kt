package novalinx.module.movie.feature.popular

import novalinx.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import novalinx.core.lib.router.module.movie.MovieRouter
import novalinx.module.movie.BR
import novalinx.module.movie.R
import novalinx.module.movie.databinding.MovieFragmentPopularBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = MovieRouter.PATH_POPULAR_FRAGMENT, group = MovieRouter.GROUP)
class PopularMovieFragment : BaseLoadingSkeletonFragment<MovieFragmentPopularBinding, PopularMovieViewModel>(
    R.layout.movie_fragment_popular, BR.viewModel, simple_Agile = {
        // 初始化 PopularMovieFragment ViewModel 调用
        it.viewModel.initializePopularMovieFragment(it as PopularMovieFragment)
    }
)