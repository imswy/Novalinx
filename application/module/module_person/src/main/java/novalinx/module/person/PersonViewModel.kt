package novalinx.module.person

import novalinx.core.lib.base.app.BaseViewModel

class PersonViewModel(
    private val repository: PersonRepository = PersonRepository()
) : BaseViewModel()