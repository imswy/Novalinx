package novalinx.module.movie

import novalinx.core.lib.base.app.BaseAppActivity
import novalinx.core.lib.base.controller.ui.ext.defaultMainContainerController
import novalinx.core.lib.base.controller.ui.ext.setExitBackIntercept
import novalinx.core.lib.base.controller.ui.theme.defaultMainContainerUITheme
import novalinx.core.lib.router.module.movie.MovieRouter
import novalinx.module.movie.databinding.MovieAppContainerBinding
import com.alibaba.android.arouter.facade.annotation.Route
import dev.mvvm.utils.toResString

@Route(path = MovieRouter.PATH_MAIN, group = MovieRouter.GROUP)
class MainContainerActivity : BaseAppActivity<MovieAppContainerBinding, MovieViewModel>(
    R.layout.movie_app_container, BR.viewModel, simple_UITheme = {
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