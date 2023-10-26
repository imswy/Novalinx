package novalinx.module.template.feature.end

import novalinx.core.lib.base.app.BaseAppActivity
import novalinx.core.lib.base.controller.ui.theme.defaultActivityBlankUITheme
import novalinx.core.lib.router.module.template.TemplateRouter
import novalinx.module.template.BR
import novalinx.module.template.R
import novalinx.module.template.databinding.TemplateActivityEndBinding
import androidx.navigation.fragment.NavHostFragment
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = TemplateRouter.PATH_END_ACTIVITY, group = TemplateRouter.GROUP)
class EndActivity : BaseAppActivity<TemplateActivityEndBinding, EndViewModel>(
    R.layout.template_activity_end, BR.viewModel, simple_UITheme = {
        it.defaultActivityBlankUITheme()
    }, simple_Agile = {
        val fragment = it.supportFragmentManager.findFragmentById(
            it.binding.navContainer.id
        ) as NavHostFragment
        val navController = fragment.navController
        navController.navigate(R.id.EndFragment, it.intent.extras)
    }
)