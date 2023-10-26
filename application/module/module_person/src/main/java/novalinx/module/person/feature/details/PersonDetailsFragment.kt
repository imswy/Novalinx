package novalinx.module.person.feature.details

import novalinx.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import novalinx.core.lib.router.module.person.PersonRouter
import novalinx.module.person.BR
import novalinx.module.person.R
import novalinx.module.person.databinding.PersonFragmentDetailsBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = PersonRouter.PATH_PERSON_DETAILS_FRAGMENT, group = PersonRouter.GROUP)
class PersonDetailsFragment : BaseLoadingSkeletonFragment<PersonFragmentDetailsBinding, PersonDetailsViewModel>(
    R.layout.person_fragment_details, BR.viewModel, simple_Agile = {
        // 初始化 PersonDetailsFragment ViewModel 调用
        it.viewModel.initializePersonDetailsFragment(it as PersonDetailsFragment)
    }
)