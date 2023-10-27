package novalinx.module.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import novalinx.core.lib.base.repository.NetworkBoundScopeResource
import novalinx.core.lib.base.repository.Resource
import novalinx.core.lib.base.split.inter.FunctionFlowCall
import novalinx.core.lib.bean.movie.PopularMovie
import novalinx.module.recommend.data.RecommendAPI
import novalinx.module.recommend.data.entity.MoviesList

class RecommendRepository {

    fun test(
        viewModel: ViewModel,
        page: Int,
        magnet:String,
        flowCall: FunctionFlowCall?
    ): LiveData<Resource<MoviesList>> {
        return object : NetworkBoundScopeResource<MoviesList>(
            viewModel.viewModelScope, flowCall = flowCall
        ){

            var localDBData: MoviesList? = null

            override fun shouldFetch(data: MoviesList?): Boolean {
                return true
            }

            override suspend fun fetchRequest(): MoviesList {
                return RecommendAPI.api().getMovies(page,magnet,"","","")
            }

            override fun fetchRandomUUID(): Long {
                TODO("Not yet implemented")
            }

            override fun shouldFetchByUUID(uuid: Long): Boolean {
                TODO("Not yet implemented")
            }

            override fun loadFromDb(): LiveData<MoviesList?> {
                val local = MutableLiveData<MoviesList>()
                local.postValue(localDBData)
                return local
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: MoviesList?
            ) {

            }

            override fun saveFetchData(item: MoviesList) {
                this.localDBData = item
            }

        }.asLiveData()
    }
}