package novalinx.module.movie

import novalinx.core.lib.base.app.BaseAppActivity
import novalinx.core.lib.base.controller.ui.theme.defaultAppLauncherUITheme
import novalinx.core.lib.config.AppLibConfig
import novalinx.core.lib.router.module.movie.MovieNav
import novalinx.core.lib.router.module.movie.MovieRouter
import novalinx.module.movie.databinding.MovieAppLauncherBinding
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.jessyan.autosize.internal.CancelAdapt

class AppLauncherActivity : BaseAppActivity<MovieAppLauncherBinding, MovieViewModel>(
    R.layout.movie_app_launcher, BR.viewModel, simple_UITheme = {
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
            MovieNav.build(MovieRouter.PATH_MAIN).navigation()
            finish()
        }
    }
}