package novalinx.module.tv.feature.details

import novalinx.core.lib.base.app.BaseAppActivity
import novalinx.core.lib.base.controller.ui.theme.defaultActivityBlankUITheme
import novalinx.core.lib.router.module.tv.TvRouter
import novalinx.module.tv.BR
import novalinx.module.tv.R
import novalinx.module.tv.TvViewModel
import novalinx.module.tv.databinding.TvActivityDetailsBinding
import androidx.navigation.fragment.NavHostFragment
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = TvRouter.PATH_TV_DETAILS_ACTIVITY, group = TvRouter.GROUP)
class TvDetailsActivity : BaseAppActivity<TvActivityDetailsBinding, TvViewModel>(
    R.layout.tv_activity_details, BR.viewModel, simple_UITheme = {
        it.defaultActivityBlankUITheme()
    }, simple_Agile = {
        val fragment = it.supportFragmentManager.findFragmentById(
            it.binding.navContainer.id
        ) as NavHostFragment
        val navController = fragment.navController
        navController.navigate(R.id.TvDetailsFragment, it.intent.extras)
    }
)