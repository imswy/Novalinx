package novalinx.module.recommend

import novalinx.core.lib.base.app.BaseAppActivity
import novalinx.core.lib.base.controller.ui.ext.defaultMainContainerController
import novalinx.core.lib.base.controller.ui.ext.setExitBackIntercept
import novalinx.core.lib.base.controller.ui.theme.defaultMainContainerUITheme
import novalinx.module.recommend.databinding.RecommendAppContainerBinding
import com.alibaba.android.arouter.facade.annotation.Route
import dev.mvvm.utils.toResString
import novalinx.core.lib.router.module.recommend.RecommendRouter

@Route(path = RecommendRouter.PATH_MAIN, group = RecommendRouter.GROUP)
class MainContainerActivity : BaseAppActivity<RecommendAppContainerBinding, RecommendViewModel>(
    R.layout.recommend_app_container, BR.viewModel, simple_UITheme = {
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