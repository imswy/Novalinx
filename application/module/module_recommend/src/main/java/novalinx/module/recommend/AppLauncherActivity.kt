package novalinx.module.recommend

import novalinx.core.lib.base.app.BaseAppActivity
import novalinx.core.lib.base.controller.ui.theme.defaultAppLauncherUITheme
import novalinx.core.lib.config.AppLibConfig
import novalinx.module.recommend.databinding.RecommendAppLauncherBinding
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.jessyan.autosize.internal.CancelAdapt
import novalinx.core.lib.router.module.recommend.RecommendNav
import novalinx.core.lib.router.module.recommend.RecommendRouter

class AppLauncherActivity : BaseAppActivity<RecommendAppLauncherBinding, RecommendViewModel>(
    R.layout.recommend_app_launcher, BR.viewModel, simple_UITheme = {
        it.defaultAppLauncherUITheme()
    }
),
    CancelAdapt {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 非组件化编译运行直接关闭页面
        if (!BuildConfig.isModular) {
            finish()
            return
        }

        lifecycleScope.launch {
            delay(AppLibConfig.ROUTER_DELAY_MILLIS)
            RecommendNav.build(RecommendRouter.PATH_MAIN).navigation()
            finish()
        }
    }
}