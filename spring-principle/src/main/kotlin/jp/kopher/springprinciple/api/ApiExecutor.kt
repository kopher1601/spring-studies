package jp.kopher.springprinciple.api

import java.net.URI

interface ApiExecutor {
    fun execute(uri: URI): String
}