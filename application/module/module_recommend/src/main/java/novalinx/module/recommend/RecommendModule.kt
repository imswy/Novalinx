package novalinx.module.recommend

import novalinx.core.BaseModule
import android.content.Context

/**
 * detail: Recommend Module ( ContentProvider Initializer )
 * @author Ttt
 */
class RecommendModule private constructor() : BaseModule(RecommendModule::class.java.simpleName) {

    companion object {
        val instance: RecommendModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RecommendModule()
        }
    }

    /**
     * 初始化方法
     * @param context Context
     */
    fun initialize(context: Context) {
    }
}