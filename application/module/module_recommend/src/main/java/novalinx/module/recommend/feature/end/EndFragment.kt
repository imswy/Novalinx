package novalinx.module.recommend.feature.end

import novalinx.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import novalinx.module.recommend.BR
import novalinx.module.recommend.R
import novalinx.module.recommend.databinding.RecommendFragmentEndBinding
import com.alibaba.android.arouter.facade.annotation.Route
import novalinx.core.lib.router.module.recommend.RecommendRouter

@Route(path = RecommendRouter.PATH_END_FRAGMENT, group = RecommendRouter.GROUP)
class EndFragment : BaseLoadingSkeletonFragment<RecommendFragmentEndBinding, EndViewModel>(
    R.layout.recommend_fragment_end, BR.viewModel, simple_Agile = {
        // 初始化 EndFragment ViewModel 调用
        it.viewModel.initializeEndFragment(it as EndFragment)
    }
)