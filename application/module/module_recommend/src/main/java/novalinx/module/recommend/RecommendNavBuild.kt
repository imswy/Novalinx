package novalinx.module.recommend

import novalinx.core.lib.router.module.recommend.RecommendNav

/**
 * detail: RecommendNav 跳转参数构建
 * @author Ttt
 */
object RecommendNavBuild {

    /**
     * 跳转 End Page Path
     */
    fun routerEndPage() {
        RecommendNav.buildEndPage(
            "End Page"
        ).navigation()
    }
}