package novalinx.core.lib.router.module.main

import novalinx.core.lib.router.module.AppRouter
import com.alibaba.android.arouter.facade.Postcard

/**
 * detail: Main Module Router
 * @author Ttt
 */
object MainRouter {

    const val GROUP = "main"

    // ========
    // = PATH =
    // ========

    // 模块入口
    const val PATH_MAIN = "/$GROUP/main"

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 [GROUP] 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 [GROUP] ( Module )
     */
    internal fun build(path: String): Postcard {
        return AppRouter.buildByUri(path)
    }
}