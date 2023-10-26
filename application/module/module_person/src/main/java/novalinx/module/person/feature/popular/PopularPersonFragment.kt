package novalinx.module.person.feature.popular

import novalinx.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import novalinx.core.lib.router.module.person.PersonRouter
import novalinx.module.person.BR
import novalinx.module.person.R
import novalinx.module.person.databinding.PersonFragmentPopularBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = PersonRouter.PATH_POPULAR_FRAGMENT, group = PersonRouter.GROUP)
class PopularPersonFragment : BaseLoadingSkeletonFragment<PersonFragmentPopularBinding, PopularPersonViewModel>(
    R.layout.person_fragment_popular, BR.viewModel, simple_Agile = {
        // 初始化 PopularPersonFragment ViewModel 调用
        it.viewModel.initializePopularPersonFragment(it as PopularPersonFragment)
    }
)