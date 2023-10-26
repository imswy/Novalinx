package novalinx.core.lib.base.controller.inter

import novalinx.core.lib.base.controller.transition.ITransitionController
import novalinx.core.lib.base.controller.ui.IUIController

/**
 * detail: Base 汇总控制器接口
 * @author Ttt
 */
interface IController : IUIController,
    ITransitionController {

    // =======================
    // = ControllerViewModel =
    // =======================

    /**
     * 是否初始化 ControllerViewModel 相关参数
     * @return `true` yes, `false` no
     */
    fun isControllerViewModelInit(): Boolean = true
}