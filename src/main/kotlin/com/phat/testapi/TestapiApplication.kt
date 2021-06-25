package com.phat.testapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
@EnableConfigurationProperties
@SpringBootApplication
@EnableScheduling
class TestapiApplication

fun main(args: Array<String>) {
    runApplication<TestapiApplication>(*args)
}
