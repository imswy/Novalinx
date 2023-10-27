package novalinx.module.recommend.feature.end

import androidx.navigation.fragment.NavHostFragment
import com.alibaba.android.arouter.facade.annotation.Route
import novalinx.core.lib.base.app.BaseAppActivity
import novalinx.core.lib.base.controller.ui.theme.defaultActivityBlankUITheme
import novalinx.core.lib.router.module.recommend.RecommendRouter
import novalinx.module.recommend.R
import novalinx.module.recommend.BR
import novalinx.module.recommend.databinding.RecommendActivityEndBinding


@Route(path = RecommendRouter.PATH_END_ACTIVITY, group = RecommendRouter.GROUP)
class EndActivity : BaseAppActivity<RecommendActivityEndBinding, EndViewModel>(
    R.layout.recommend_activity_end, BR.viewModel, simple_UITheme = {
        it.defaultActivityBlankUITheme()
    }, simple_Agile = {
        val fragment = it.supportFragmentManager.findFragmentById(
            it.binding.navContainer.id
        ) as NavHostFragment
        val navController = fragment.navController
        navController.navigate(R.id.EndFragment, it.intent.extras)
    }
)