package novalinx.module.movie

import novalinx.core.lib.base.app.BaseViewModel

class MovieViewModel(
    private val repository: MovieRepository = MovieRepository()
) : BaseViewModel()