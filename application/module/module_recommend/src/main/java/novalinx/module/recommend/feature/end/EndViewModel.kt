package novalinx.module.recommend.feature.end

import novalinx.core.lib.base.app.BaseViewModel
import novalinx.core.lib.base.split.data.IntentData
import novalinx.core.lib.config.ParamConst
import novalinx.module.recommend.RecommendRepository


class EndViewModel(
    private val repository: RecommendRepository = RecommendRepository()
) : BaseViewModel() {

    // 跳转传参
    private val intentData = IntentData.with()

    // ==============
    // = initialize =
    // ==============

    /**
     * 初始化 EndFragment ViewModel 调用
     * @param fragment EndFragment
     */
    fun initializeEndFragment(fragment: EndFragment) {
        intentData.reader(fragment.arguments)
        // 初始化数据
        fragment.apply {
            // 初始化 CoreUiBaseHeaderBinding 通用 include layout
            uiController.initializeCoreUiBaseHeader(binding.vidHeader)
            // 设置标题
            uiController.appUI.title.set(intentData.get(ParamConst.TITLE))
            // 设置返回键
            binding.vidHeader.vidTitleBar.vidBackCl.setOnClickListener {
                fragment.finishActivity()
            }
        }
    }
}