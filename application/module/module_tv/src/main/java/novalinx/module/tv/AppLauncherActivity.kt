package novalinx.module.tv

import novalinx.core.lib.base.app.BaseAppActivity
import novalinx.core.lib.base.controller.ui.theme.defaultAppLauncherUITheme
import novalinx.core.lib.config.AppLibConfig
import novalinx.core.lib.router.module.tv.TvNav
import novalinx.core.lib.router.module.tv.TvRouter
import novalinx.module.tv.databinding.TvAppLauncherBinding
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.jessyan.autosize.internal.CancelAdapt

class AppLauncherActivity : BaseAppActivity<TvAppLauncherBinding, TvViewModel>(
    R.layout.tv_app_launcher, BR.viewModel, simple_UITheme = {
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
            TvNav.build(TvRouter.PATH_MAIN).navigation()
            finish()
        }
    }
}