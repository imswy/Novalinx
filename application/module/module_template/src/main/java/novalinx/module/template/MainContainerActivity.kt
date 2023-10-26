package novalinx.module.template

import novalinx.core.lib.base.app.BaseAppActivity
import novalinx.core.lib.base.controller.ui.ext.defaultMainContainerController
import novalinx.core.lib.base.controller.ui.ext.setExitBackIntercept
import novalinx.core.lib.base.controller.ui.theme.defaultMainContainerUITheme
import novalinx.core.lib.router.module.template.TemplateRouter
import novalinx.module.template.databinding.TemplateAppContainerBinding
import com.alibaba.android.arouter.facade.annotation.Route
import dev.mvvm.utils.toResString

@Route(path = TemplateRouter.PATH_MAIN, group = TemplateRouter.GROUP)
class MainContainerActivity : BaseAppActivity<TemplateAppContainerBinding, TemplateViewModel>(
    R.layout.template_app_container, BR.viewModel, simple_UITheme = {
        it.defaultMainContainerUITheme()
    }, simple_PreLoad = {
        it.apply {
            uiController.defaultMainContainerController(
                R.string.app_name.toResString()
            )
            // 设置返回键退出 App 拦截监听
            keyEventController.setExitBackIntercept(BuildConfig.MODULE_NAME)
        }
    }
)