package novalinx.module.person.feature.details

import novalinx.core.lib.base.app.BaseAppActivity
import novalinx.core.lib.base.controller.ui.theme.defaultActivityBlankUITheme
import novalinx.core.lib.router.module.person.PersonRouter
import novalinx.module.person.BR
import novalinx.module.person.PersonViewModel
import novalinx.module.person.R
import novalinx.module.person.databinding.PersonActivityDetailsBinding
import androidx.navigation.fragment.NavHostFragment
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = PersonRouter.PATH_PERSON_DETAILS_ACTIVITY, group = PersonRouter.GROUP)
class PersonDetailsActivity : BaseAppActivity<PersonActivityDetailsBinding, PersonViewModel>(
    R.layout.person_activity_details, BR.viewModel, simple_UITheme = {
        it.defaultActivityBlankUITheme()
    }, simple_Agile = {
        val fragment = it.supportFragmentManager.findFragmentById(
            it.binding.navContainer.id
        ) as NavHostFragment
        val navController = fragment.navController
        navController.navigate(R.id.PersonDetailsFragment, it.intent.extras)
    }
)