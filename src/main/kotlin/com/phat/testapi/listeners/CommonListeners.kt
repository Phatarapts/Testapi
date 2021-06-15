package com.phat.testapi.listeners

import com.phat.testapi.model.entity.CommonEntity
import java.time.LocalDateTime
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

class CommonListeners <T :CommonEntity> {
    @PrePersist
    private fun prePersist(e:T){
        e.postDate = LocalDateTime.now()
    }
    @PreUpdate
    private fun preUpdate(e:T){
        e.updateDate = LocalDateTime.now()
    }
}