package novalinx.module.recommend.feature.start

import novalinx.core.lib.base.app.BaseViewModel
import novalinx.module.recommend.RecommendNavBuild
import novalinx.module.recommend.RecommendRepository
import android.view.View
import dev.mvvm.command.BindingConsumer

/**
 * detail: 模板 Module Start 演示 ViewModel
 * @author Ttt
 * 也可统一放到 RecommendViewModel, 拆分只是为了更加方便维护管理
 */
class StartViewModel(
    private val repository: RecommendRepository = RecommendRepository()
) : BaseViewModel() {

    val onClickEnd = object : BindingConsumer<View?> {
        override fun accept(value: View?) {
            RecommendNavBuild.routerEndPage()
        }
    }

    // ==============
    // = initialize =
    // ==============

    /**
     * 初始化 StartFragment ViewModel 调用
     * @param fragment StartFragment
     */
    fun initializeStartFragment(fragment: StartFragment) {
        fragment.binding.apply {
        }
    }
}