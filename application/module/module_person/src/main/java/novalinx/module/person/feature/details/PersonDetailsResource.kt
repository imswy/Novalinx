package novalinx.module.person.feature.details

import novalinx.core.lib.base.controller.loading.BaseLoadingSkeletonController
import novalinx.core.lib.base.repository.Resource
import novalinx.core.lib.base.repository.Status
import novalinx.core.lib.base.split.data.IntentData
import novalinx.core.lib.bean.person.PersonActing
import novalinx.core.lib.bean.person.PersonDetails
import novalinx.core.lib.config.ParamConst
import novalinx.lib.tmdb.ui.adapter.PersonActingItem
import novalinx.module.person.databinding.PersonFragmentDetailsBinding
import androidx.lifecycle.MutableLiveData
import dev.utils.common.StringUtils
import dev.utils.common.able.Consumerable
import dev.utils.common.able.Getable

interface PersonDetailsDataConsumer : Consumerable.ConsumerByParam2<
        Boolean, PersonDetails?, PersonActing?>

/**
 * 绑定数据源解析处理
 */
fun bindPersonDetailsResource(
    personDetails: Resource<PersonDetails>?,
    personActing: Resource<PersonActing>?,
    consumer: PersonDetailsDataConsumer
) {
    var details: PersonDetails? = null
    var acting: PersonActing? = null
    // 解析人物详情数据
    personDetails?.let {
        when (it.status) {
            Status.LOADING -> Unit
            Status.SUCCESS -> {
                it.data?.let { bean ->
                    details = bean
                }
            }

            Status.ERROR, Status.EMPTY -> Unit
        }
    }
    // 解析人物参演作品数据
    personActing?.let {
        when (it.status) {
            Status.LOADING -> Unit
            Status.SUCCESS -> {
                it.data?.let { bean ->
                    acting = bean
                }
            }

            Status.ERROR, Status.EMPTY -> Unit
        }
    }
    consumer.accept(details, acting)
}

/**
 * detail: 用户详情数据消费实现类
 * @author Ttt
 */
class PersonDetailsDataConsumerIMPL(
    private val loadingSkeletonGet: Getable.Get<BaseLoadingSkeletonController<*>>,
    private val intentData: IntentData
) : PersonDetailsDataConsumer {

    private lateinit var binding: PersonFragmentDetailsBinding

    override fun accept(
        param: PersonDetails?,
        param2: PersonActing?
    ): Boolean {
        param?.let {
            details.postValue(it)
        }
        param2?.let {
            acting.postValue(it)
        }
        if (param != null && param2 != null) {
            loadingSkeletonGet.get()?.showSuccess(
                notFoundOP = false
            )
        }
        return true
    }

    // ==============
    // = 对外公开方法 =
    // ==============

    fun initialize(
        fragment: PersonDetailsFragment,
        actingItem: PersonActingItem
    ) {
        fragment.binding.let {
            this.binding = it
            it.details = details
            it.acting = acting
        }
        fragment.uiController.apply {
            // 初始化 CoreUiBaseHeaderBinding 通用 include layout
            initializeCoreUiBaseHeader(binding.vidHeader, true)
            // 设置标题
            appUI.title.set(intentData.get(ParamConst.NAME))
            // 设置返回键
            binding.vidHeader.vidTitleBar.vidBackCl.setOnClickListener {
                fragment.finishActivity()
            }
        }
        acting.observe(fragment) {
            actingItem.items.clear()
//            actingItem.items.addAll(it.cast)
            // 移除空的数据
            actingItem.items.addAll(it.cast.mapIndexedNotNull { _, knownFor ->
                if (StringUtils.isNotEmpty(knownFor.poster_path)) {
                    return@mapIndexedNotNull knownFor
                }
                return@mapIndexedNotNull null
            })
        }
        // 设置参演详情 Binding
        actingItem.adapter.actionPopupBinding = fragment.binding.vidActionPopup
    }

    // 数据源
    private val details = MutableLiveData<PersonDetails>()
    private val acting = MutableLiveData<PersonActing>()
}