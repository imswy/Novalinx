package novalinx.module.person.feature.details

import novalinx.core.lib.base.app.BaseViewModel
import novalinx.core.lib.base.controller.loading.BaseLoadingSkeletonController
import novalinx.core.lib.base.repository.AbsentLiveData
import novalinx.core.lib.base.repository.Resource
import novalinx.core.lib.base.split.data.IntentData
import novalinx.core.lib.base.split.inter.FunctionFlowCall
import novalinx.core.lib.bean.person.KnownFor
import novalinx.core.lib.bean.person.PersonActing
import novalinx.core.lib.bean.person.PersonDetails
import novalinx.core.lib.config.ParamConst
import novalinx.core.lib.router.module.movie.MovieNav
import novalinx.core.lib.ui.databinding.CoreUiBaseStatusBarBinding
import novalinx.core.lib.ui.databinding.CoreUiBaseTitleBarBinding
import novalinx.core.lib.ui.widget.view_assist.loading_skeleton.PageTitleBindable
import novalinx.core.lib.ui.widget.view_assist.loading_skeleton.PageTitleLoadingSkeletonViewAssist
import novalinx.lib.tmdb.ui.adapter.PersonActingItem
import novalinx.module.person.PersonRepository
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import dev.mvvm.command.BindingClick
import dev.utils.common.ConvertUtils
import dev.utils.common.able.Getable

class PersonDetailsViewModel(
    private val repository: PersonRepository = PersonRepository()
) : BaseViewModel() {

    // 跳转传参
    private val intentData = IntentData.with()

    // Loading Skeleton 控制封装
    private val loadingSkeletonGet: Getable.Get<BaseLoadingSkeletonController<*>> = Getable.Get {
        return@Get loadingSkeletonController.value
    }

    // 数据消费处理
    private val dataConsumer = PersonDetailsDataConsumerIMPL(
        loadingSkeletonGet, intentData
    )

    // 人物参演 Adapter Item
    val actingItem = PersonActingItem(Getable.Get {
        return@Get null
    }).apply {
        itemClick = object : BindingClick<KnownFor> {
            override fun onClick(value: KnownFor) {
                MovieNav.buildMovieDetails(
                    value.id.toString(), value.title()
                ).navigation()
            }
        }
    }

    // ==========
    // = 请求方法 =
    // ==========

    // ==========
    // = 人物详情 =
    // ==========

    // 请求方法流程回调
    private val flowCall = object : FunctionFlowCall {
        override fun start() {
            loadingSkeletonGet.get()?.let {
                if (!it.isTypeIng()) it.showIng()
            }
        }

        override fun error() {
            loadingSkeletonGet.get()?.showFailed()
        }
    }

    // 请求人物详情数据
    private val personIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private val personDetailsLiveData: LiveData<Resource<PersonDetails>> =
        personIdLiveData.switchMap {
            personIdLiveData.value?.let { personId ->
                repository.requestPersonDetails(
                    viewModel = this, personId = personId, flowCall = flowCall
                )
            } ?: AbsentLiveData.create()
        }
    private val personActingLiveData: LiveData<Resource<PersonActing>> =
        personIdLiveData.switchMap {
            personIdLiveData.value?.let { personId ->
                repository.requestPersonActing(
                    viewModel = this, personId = personId, flowCall = flowCall
                )
            } ?: AbsentLiveData.create()
        }

    /**
     * 请求人物详情
     */
    private fun requestPersonDetails() {
        personIdLiveData.postValue(
            ConvertUtils.toInt(intentData.get(ParamConst.PERSON_ID))
        )
    }

    // ==============
    // = initialize =
    // ==============

    /**
     * 初始化 PersonDetailsFragment ViewModel 调用
     * @param fragment PersonDetailsFragment
     */
    fun initializePersonDetailsFragment(fragment: PersonDetailsFragment) {
        intentData.reader(fragment.arguments)
        // 注册 Loading 骨架 type
        fragment.loadingSkeletonFactory.register(
            PageTitleLoadingSkeletonViewAssist(
                fragment.loadingSkeletonController.viewAssist,
                fragment.loadingSkeletonController.contentAssist.contentLinear,
                reload = { requestPersonDetails() },
                bindable = object : PageTitleBindable {
                    override fun bind(
                        param: ViewDataBinding,
                        param2: CoreUiBaseStatusBarBinding,
                        param3: CoreUiBaseTitleBarBinding
                    ): Boolean {
                        fragment.uiController.apply {
                            // 绑定 UI 自动更新
                            initializeVDBVariable(param)
                            // 初始化 StatusBar 骨架 View
                            initializeStatusBarSkeletonView(param2)
                            // 初始化 TitleBar 骨架 View
                            initializeTitleBarSkeletonView(param3)
                            // 设置标题
                            appUI.title.set(intentData.get(ParamConst.NAME))
                            // 设置返回键
                            param3.vidBackCl.setOnClickListener {
                                fragment.finishActivity()
                            }
                        }
                        return true
                    }
                }
            )
        )
        dataConsumer.initialize(fragment, actingItem)
        // 监听数据
        personDetailsLiveData.observe(fragment) {
            bindPersonDetailsResource(
                personDetailsLiveData.value, personActingLiveData.value,
                dataConsumer
            )
        }
        personActingLiveData.observe(fragment) {
            bindPersonDetailsResource(
                personDetailsLiveData.value, personActingLiveData.value,
                dataConsumer
            )
        }
        // 请求人物详情数据
        requestPersonDetails()
    }
}