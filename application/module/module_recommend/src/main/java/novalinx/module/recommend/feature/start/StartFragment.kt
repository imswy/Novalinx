package novalinx.module.recommend.feature.start

import novalinx.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import novalinx.module.recommend.BR
import novalinx.module.recommend.R
import novalinx.module.recommend.databinding.RecommendFragmentStartBinding
import com.alibaba.android.arouter.facade.annotation.Route
import novalinx.core.lib.router.module.recommend.RecommendRouter

@Route(path = RecommendRouter.PATH_START_FRAGMENT, group = RecommendRouter.GROUP)
class StartFragment : BaseLoadingSkeletonFragment<RecommendFragmentStartBinding, StartViewModel>(
    R.layout.recommend_fragment_start, BR.viewModel, simple_Agile = {
        // 初始化 StartFragment ViewModel 调用
        it.viewModel.initializeStartFragment(it as StartFragment)
    }
)