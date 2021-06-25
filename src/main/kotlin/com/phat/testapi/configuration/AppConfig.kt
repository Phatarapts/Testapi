package com.phat.testapi.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated

@Component
@Validated
@ConfigurationProperties(prefix = "app")
class AppConfig {
    lateinit var plus: Number
    lateinit var minus: Number

    class Info{
        lateinit var name: String
        lateinit var email: String
    }
}