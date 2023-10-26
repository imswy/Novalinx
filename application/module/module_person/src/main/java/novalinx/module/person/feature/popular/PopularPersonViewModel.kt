package novalinx.module.person.feature.popular

import novalinx.core.lib.base.app.BaseViewModel
import novalinx.core.lib.base.controller.loading.BaseLoadingSkeletonController
import novalinx.core.lib.base.repository.AbsentLiveData
import novalinx.core.lib.base.repository.Resource
import novalinx.core.lib.base.split.inter.FunctionFlowCall
import novalinx.core.lib.bean.person.PopularPerson
import novalinx.core.lib.bean.person.TMDBPerson
import novalinx.core.lib.ui.widget.extension.smartRefreshLoadMoreListener
import novalinx.core.lib.ui.widget.view_assist.loading_skeleton.PageLoadingSkeletonViewAssist
import novalinx.lib.tmdb.ui.adapter.PersonProfileItem
import novalinx.module.person.PersonNavBuild.routerPersonDetails
import novalinx.module.person.PersonRepository
import androidx.lifecycle.*
import dev.base.state.RequestState
import dev.mvvm.command.BindingClick
import dev.utils.common.able.Getable

/**
 * detail: 热门人物 ViewModel
 * @author Ttt
 * 也可统一放到 PersonViewModel, 拆分只是为了更加方便维护管理
 */
class PopularPersonViewModel(
    private val repository: PersonRepository = PersonRepository()
) : BaseViewModel() {

    // 热门人物 Adapter Item
    val popularItem = PersonProfileItem(Getable.Get {
        return@Get uiController.value?.appThemeRes
    }).apply {
        itemClick = object : BindingClick<TMDBPerson> {
            override fun onClick(value: TMDBPerson) {
                value.routerPersonDetails()
            }
        }
    }

    // ==========
    // = 生命周期 =
    // ==========

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        // 请求热门人物列表
        resumePopularPerson()
    }

    // ==========
    // = 请求方法 =
    // ==========

    // onResume 请求状态控制
    private val resumeRequest = RequestState<Any>()

    // ==============
    // = 热门人物列表 =
    // ==============

    // Loading Skeleton 控制封装
    private val loadingSkeletonGet: Getable.Get<BaseLoadingSkeletonController<*>> = Getable.Get {
        return@Get loadingSkeletonController.value
    }

    // 请求页数信息
    private val personPageLiveData: MutableLiveData<Int> = MutableLiveData()
    private val personListLiveData: LiveData<Resource<PopularPerson>> =
        personPageLiveData.switchMap {
            personPageLiveData.value?.let { page ->
                repository.requestPopularPerson(
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
     * 请求热门人物列表
     */
    private fun requestPopularPerson(refresh: Boolean) {
        val page = if (refresh) {
            popularItem.page.configPage
        } else {
            popularItem.page.nextPage
        }
        personPageLiveData.postValue(page)
    }

    /**
     * 请求热门人物列表
     * 内部校验是否存在数据
     */
    private fun resumePopularPerson() {
        if (popularItem.items.isEmpty() && resumeRequest.isRequestNormal) {
            requestPopularPerson(true)
        }
    }

    // ==============
    // = initialize =
    // ==============

    /**
     * 初始化 PopularPersonFragment ViewModel 调用
     * @param fragment PopularPersonFragment
     */
    fun initializePopularPersonFragment(fragment: PopularPersonFragment) {
        fragment.binding.apply {
            // 设置刷新和加载监听器
            vidRefresh.smartRefreshLoadMoreListener { _, refresh ->
                requestPopularPerson(refresh)
            }
            // 监听数据
            personListLiveData.observe(fragment, Observer {
                it.bindResource(popularItem, vidRefresh)
            })
        }
        // 注册 Loading 骨架 type
        fragment.loadingSkeletonFactory.register(
            PageLoadingSkeletonViewAssist(
                fragment.loadingSkeletonController.viewAssist,
                fragment.loadingSkeletonController.contentAssist.contentLinear
            ) { requestPopularPerson(true) }
        )
    }
}