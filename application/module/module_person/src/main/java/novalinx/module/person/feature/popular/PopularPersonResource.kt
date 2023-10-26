package novalinx.module.person.feature.popular

import novalinx.core.lib.base.repository.Resource
import novalinx.core.lib.base.repository.Status
import novalinx.core.lib.bean.person.PopularPerson
import novalinx.core.lib.ui.widget.extension.smartFinish
import novalinx.core.lib.ui.widget.extension.smartSimpleFinishWithNoMoreData
import novalinx.lib.tmdb.ui.adapter.PersonProfileItem
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import dev.mvvm.utils.hi.hiif.hiIfNotNull

/**
 * 绑定数据源解析处理
 * @param item PersonProfileItem
 * @param refreshLayout SmartRefreshLayout
 */
fun Resource<PopularPerson>.bindResource(
    item: PersonProfileItem,
    refreshLayout: SmartRefreshLayout
) {
    when (status) {
        Status.LOADING -> Unit
        Status.SUCCESS -> {
            data.hiIfNotNull({ popular ->
                item.page.setPage(popular.page).isLastPage = popular.isLastPage()

                if (item.page.isFirstPage) {
                    item.items.clear()
                }
                popular.results?.let { list ->
                    item.items.addAll(list)
                }
                // 智能结束刷新、加载状态并标记是否存在更多数据
                refreshLayout.smartSimpleFinishWithNoMoreData(
                    item.page.isLastPage
                )
            }, {
                // 智能结束刷新、加载状态
                refreshLayout.smartFinish()
            })
        }

        Status.ERROR, Status.EMPTY -> {
            // 智能结束刷新、加载状态
            refreshLayout.smartFinish()
        }
    }
}