package novalinx.module.tv.feature.popular

import novalinx.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import novalinx.core.lib.router.module.tv.TvRouter
import novalinx.module.tv.BR
import novalinx.module.tv.R
import novalinx.module.tv.databinding.TvFragmentPopularBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = TvRouter.PATH_POPULAR_FRAGMENT, group = TvRouter.GROUP)
class PopularTvFragment : BaseLoadingSkeletonFragment<TvFragmentPopularBinding, PopularTvViewModel>(
    R.layout.tv_fragment_popular, BR.viewModel, simple_Agile = {
        // 初始化 PopularTvFragment ViewModel 调用
        it.viewModel.initializePopularTvFragment(it as PopularTvFragment)
    }
)