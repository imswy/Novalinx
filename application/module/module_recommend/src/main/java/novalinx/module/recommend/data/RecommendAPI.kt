package novalinx.module.recommend.data

import dev.DevHttpManager
import dev.environment.DevEnvironment
import dev.http.manager.RetrofitBuilder
import dev.http.manager.RetrofitOperation
import novalinx.core.app.AppContext
import novalinx.core.lib.network.HttpCoreUtils
import novalinx.module.recommend.BuildConfig
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * @ClassName RecommendAPI
 * @Author zwy
 * @Description TODD
 */
class RecommendAPI private constructor(){

    companion object {
        private val instance: RecommendAPI by lazy { RecommendAPI() }

        fun api(): RecommendService {
            return instance.api()
        }

        fun operation(): RetrofitOperation {
            return instance.operation()
        }
    }

    // =================
    // = RecommendService =
    // =================

    @Volatile
    private var mRecommendService: RecommendService? = null

    fun api(): RecommendService {
        if (mRecommendService == null){
            synchronized(RecommendService::class.java) {
                if (mRecommendService == null){
                    createAPI()
                }
            }
        }
        return mRecommendService as RecommendService
    }

    private fun createAPI(){
        mRecommendService = operation().create(
            RecommendService::class.java
        )
    }

    // ==================
    // = DevEnvironment =
    // ==================

    private fun apiBaseUrl(): HttpUrl {
        return DevEnvironment.getRecommendEnvironmentValue(
            AppContext.context()
        ).toHttpUrl()
    }

    // =====================
    // = RetrofitOperation =
    // =====================

    /**
     * 对外提供操作对象
     * @return RetrofitOperation
     */
    fun operation(): RetrofitOperation {
        return mOperation
    }

    // Retrofit Operation
    private val mOperation: RetrofitOperation by lazy {
        DevHttpManager.RM.putRetrofitBuilder(
            BuildConfig.MODULE_NAME, mRetrofitBuilder
        )
    }

    // ===================
    // = RetrofitBuilder =
    // ===================

    // Retrofit Builder 接口
    private val mRetrofitBuilder: RetrofitBuilder by lazy {
        object : RetrofitBuilder {

            /**
             * 创建 Retrofit Builder
             * @param oldRetrofit 上一次构建的 Retrofit
             * @param httpUrl 构建使用指定 baseUrl
             * @param okHttp OkHttpClient 构建全局复用
             * @return Retrofit.Builder
             */
            override fun createRetrofitBuilder(
                oldRetrofit: Retrofit?,
                httpUrl: HttpUrl?,
                okHttp: OkHttpClient.Builder?
            ): Retrofit.Builder {
                return HttpCoreUtils.createRetrofitBuilder(
                    httpUrl = httpUrl ?: apiBaseUrl(),
                    okHttp = okHttp ?: OkHttpClient.Builder()
                )
            }

            // ==========
            // = 通知事件 =
            // ==========

            /**
             * 重新构建前调用
             * @param key String
             * @param oldRetrofit 上一次构建的 Retrofit
             * 在 [createRetrofitBuilder] 之前调用
             */
            override fun onResetBefore(
                key: String,
                oldRetrofit: Retrofit?
            ) {

            }

            /**
             * 重新构建后调用
             * @param key String
             * @param newRetrofit 重新构建的 Retrofit 对象
             * 在 [createRetrofitBuilder] 之后调用
             */
            override fun onReset(
                key: String,
                newRetrofit: Retrofit?
            ) {
                // 重新构建后创建新的代理对象
                createAPI()
            }
        }
    }
}