package novalinx.module.tv

import novalinx.core.BaseModule
import android.content.Context

/**
 * detail: Tv Module ( ContentProvider Initializer )
 * @author Ttt
 */
class TvModule private constructor() : BaseModule(TvModule::class.java.simpleName) {

    companion object {
        val instance: TvModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            TvModule()
        }
    }

    /**
     * 初始化方法
     * @param context Context
     */
    fun initialize(context: Context) {
    }
}