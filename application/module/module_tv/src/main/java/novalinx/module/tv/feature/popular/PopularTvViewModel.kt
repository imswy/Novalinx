package novalinx.module.tv.feature.popular

import novalinx.core.lib.base.app.BaseViewModel
import novalinx.core.lib.base.controller.loading.BaseLoadingSkeletonController
import novalinx.core.lib.base.repository.AbsentLiveData
import novalinx.core.lib.base.repository.Resource
import novalinx.core.lib.base.split.inter.FunctionFlowCall
import novalinx.core.lib.bean.base.TMDBCommon
import novalinx.core.lib.bean.tv.PopularTv
import novalinx.core.lib.ui.widget.extension.smartRefreshLoadMoreListener
import novalinx.core.lib.ui.widget.view_assist.loading_skeleton.PageLoadingSkeletonViewAssist
import novalinx.lib.tmdb.ui.adapter.PosterCoverItem
import novalinx.module.tv.TvNavBuild.routerTvDetails
import novalinx.module.tv.TvRepository
import androidx.lifecycle.*
import dev.base.state.RequestState
import dev.mvvm.command.BindingClick
import dev.utils.common.able.Getable

/**
 * detail: 热门剧集 ViewModel
 * @author Ttt
 * 也可统一放到 TvViewModel, 拆分只是为了更加方便维护管理
 */
class PopularTvViewModel(
    private val repository: TvRepository = TvRepository()
) : BaseViewModel() {

    // 热门剧集 Adapter Item
    val popularItem = PosterCoverItem(Getable.Get {
        return@Get uiController.value?.appThemeRes
    }).apply {
        itemClick = object : BindingClick<TMDBCommon> {
            override fun onClick(value: TMDBCommon) {
                value.routerTvDetails()
            }
        }
    }

    // ==========
    // = 生命周期 =
    // ==========

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        // 请求热门剧集列表
        resumePopularTv()
    }

    // ==========
    // = 请求方法 =
    // ==========

    // onResume 请求状态控制
    private val resumeRequest = RequestState<Any>()

    // ==============
    // = 热门剧集列表 =
    // ==============

    // Loading Skeleton 控制封装
    var loadingSkeletonGet: Getable.Get<BaseLoadingSkeletonController<*>> = Getable.Get {
        return@Get loadingSkeletonController.value
    }

    // 请求页数信息
    private val tvPageLiveData: MutableLiveData<Int> = MutableLiveData()
    private val tvListLiveData: LiveData<Resource<PopularTv>> = tvPageLiveData.switchMap {
        tvPageLiveData.value?.let { page ->
            repository.requestPopularTv(
                viewModel = this, page = page,
                devPage = popularItem.page,
                flowCall = object : FunctionFlowCall {
                    override fun start() {
                        resumeRequest.setRequestIng()
                        loadingSkeletonGet.get()?.showIng()
                    }

                    override fun success() {
                        loadingSkeletonGet.get()?.showSuccess()
                    }

                    override fun error() {
                        loadingSkeletonGet.get()?.showFailed()
                    }

                    override fun finish() {
                        resumeRequest.setRequestNormal()
                    }
                }
            )
        } ?: AbsentLiveData.create()
    }

    /**
     * 请求热门剧集列表
     */
    private fun requestPopularTv(refresh: Boolean) {
        val page = if (refresh) {
            popularItem.page.configPage
        } else {
            popularItem.page.nextPage
        }
        tvPageLiveData.postValue(page)
    }

    /**
     * 请求热门剧集列表
     * 内部校验是否存在数据
     */
    private fun resumePopularTv() {
        if (popularItem.items.isEmpty() && resumeRequest.isRequestNormal) {
            requestPopularTv(true)
        }
    }

    // ==============
    // = initialize =
    // ==============

    /**
     * 初始化 PopularTvFragment ViewModel 调用
     * @param fragment PopularTvFragment
     */
    fun initializePopularTvFragment(fragment: PopularTvFragment) {
        fragment.binding.apply {
            // 设置刷新和加载监听器
            vidRefresh.smartRefreshLoadMoreListener { _, refresh ->
                requestPopularTv(refresh)
            }
            // 监听数据
            tvListLiveData.observe(fragment, Observer {
                it.bindResource(popularItem, vidRefresh)
            })
        }
        // 注册 Loading 骨架 type
        fragment.loadingSkeletonFactory.register(
            PageLoadingSkeletonViewAssist(
                fragment.loadingSkeletonController.viewAssist,
                fragment.loadingSkeletonController.contentAssist.contentLinear
            ) { requestPopularTv(true) }
        )
    }
}