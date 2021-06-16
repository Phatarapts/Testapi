package com.phat.testapi.model.entity

import com.phat.testapi.listeners.CommonListeners
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Version


@EntityListeners(value = [CommonListeners::class])
@MappedSuperclass
abstract class CommonEntity {
    var postDate: LocalDateTime? = null
    var updateDate: LocalDateTime? = null

    @Version
    var version = 1
}