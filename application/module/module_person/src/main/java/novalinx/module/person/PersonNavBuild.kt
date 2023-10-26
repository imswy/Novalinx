package novalinx.module.person

import novalinx.core.lib.bean.person.TMDBPerson
import novalinx.core.lib.router.module.person.PersonNav

/**
 * detail: PersonNav 跳转参数构建
 * @author Ttt
 */
object PersonNavBuild {

    /**
     * 跳转 Person Details Path
     * @receiver TMDBPerson
     * @return Postcard
     */
    fun TMDBPerson.routerPersonDetails() {
        PersonNav.buildPersonDetails(
            id.toString(), name
        ).navigation()
    }
}