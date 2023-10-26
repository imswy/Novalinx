package novalinx.module.template.feature.start

import novalinx.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import novalinx.core.lib.router.module.template.TemplateRouter
import novalinx.module.template.BR
import novalinx.module.template.R
import novalinx.module.template.databinding.TemplateFragmentStartBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = TemplateRouter.PATH_START_FRAGMENT, group = TemplateRouter.GROUP)
class StartFragment : BaseLoadingSkeletonFragment<TemplateFragmentStartBinding, StartViewModel>(
    R.layout.template_fragment_start, BR.viewModel, simple_Agile = {
        // 初始化 StartFragment ViewModel 调用
        it.viewModel.initializeStartFragment(it as StartFragment)
    }
)