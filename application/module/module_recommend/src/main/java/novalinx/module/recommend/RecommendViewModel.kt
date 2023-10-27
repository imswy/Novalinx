package novalinx.module.recommend

import novalinx.core.lib.base.app.BaseViewModel

class RecommendViewModel(
    private val repository: RecommendRepository = RecommendRepository()
) : BaseViewModel()