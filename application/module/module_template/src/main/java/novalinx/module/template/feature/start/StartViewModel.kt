package novalinx.module.template.feature.start

import novalinx.core.lib.base.app.BaseViewModel
import novalinx.module.template.TemplateNavBuild
import novalinx.module.template.TemplateRepository
import android.view.View
import dev.mvvm.command.BindingConsumer

/**
 * detail: 模板 Module Start 演示 ViewModel
 * @author Ttt
 * 也可统一放到 TemplateViewModel, 拆分只是为了更加方便维护管理
 */
class StartViewModel(
    private val repository: TemplateRepository = TemplateRepository()
) : BaseViewModel() {

    val onClickEnd = object : BindingConsumer<View?> {
        override fun accept(value: View?) {
            TemplateNavBuild.routerEndPage()
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