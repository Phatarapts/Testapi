package com.phat.testapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class TestapiApplication

fun main(args: Array<String>) {
    runApplication<TestapiApplication>(*args)
}
