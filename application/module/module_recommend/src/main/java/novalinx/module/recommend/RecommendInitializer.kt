package novalinx.module.recommend

import novalinx.core.BaseInitializer
import android.content.Context
import androidx.startup.Initializer
import java.util.*

/**
 * detail: Recommend Module ( App Startup Initializer )
 * @author Ttt
 */
class RecommendInitializer : BaseInitializer<RecommendModule>() {

    override fun create(context: Context): RecommendModule {
        RecommendModule.instance.initialize(context)
        return RecommendModule.instance
    }

    override fun dependencies_abs(): MutableList<Class<out Initializer<*>>> =
        Collections.emptyList()
}