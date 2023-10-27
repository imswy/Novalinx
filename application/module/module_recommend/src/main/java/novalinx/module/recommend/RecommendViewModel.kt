package novalinx.module.recommend

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import novalinx.core.lib.base.app.BaseViewModel
import novalinx.core.lib.base.repository.AbsentLiveData
import novalinx.core.lib.base.repository.Resource
import novalinx.core.lib.base.split.inter.FunctionFlowCall
import novalinx.module.recommend.data.entity.MoviesList

class RecommendViewModel(
    private val repository: RecommendRepository = RecommendRepository()
) : BaseViewModel() {
    private val moviePageLiveData: MutableLiveData<Int> = MutableLiveData()
    private val movieListLiveData: LiveData<Resource<MoviesList>> = moviePageLiveData.switchMap {
        moviePageLiveData.value?.let { page ->
                repository.test(
                    viewModel = this, page = page,
                    magnet = "all", flowCall = object : FunctionFlowCall {
                        override fun success() {

                        }
                    })
            } ?: AbsentLiveData.create()
        }

    override fun onResume(owner: LifecycleOwner) {
        moviePageLiveData.value = 1
    }
}
