package novalinx.module.tv.feature.details

import novalinx.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import novalinx.core.lib.router.module.tv.TvRouter
import novalinx.module.tv.BR
import novalinx.module.tv.R
import novalinx.module.tv.databinding.TvFragmentDetailsBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = TvRouter.PATH_TV_DETAILS_FRAGMENT, group = TvRouter.GROUP)
class TvDetailsFragment : BaseLoadingSkeletonFragment<TvFragmentDetailsBinding, TvDetailsViewModel>(
    R.layout.tv_fragment_details, BR.viewModel, simple_Agile = {
        // 初始化 TvDetailsFragment ViewModel 调用
        it.viewModel.initializeTvDetailsFragment(it as TvDetailsFragment)
    }
)