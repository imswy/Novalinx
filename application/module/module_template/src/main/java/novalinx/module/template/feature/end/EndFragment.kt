package novalinx.module.template.feature.end

import novalinx.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import novalinx.core.lib.router.module.template.TemplateRouter
import novalinx.module.template.BR
import novalinx.module.template.R
import novalinx.module.template.databinding.TemplateFragmentEndBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = TemplateRouter.PATH_END_FRAGMENT, group = TemplateRouter.GROUP)
class EndFragment : BaseLoadingSkeletonFragment<TemplateFragmentEndBinding, EndViewModel>(
    R.layout.template_fragment_end, BR.viewModel, simple_Agile = {
        // 初始化 EndFragment ViewModel 调用
        it.viewModel.initializeEndFragment(it as EndFragment)
    }
)