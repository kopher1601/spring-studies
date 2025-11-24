package jp.kopher.springprinciple.api

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URI
import java.util.stream.Collectors

class SimpleApiExecutor: ApiExecutor {
    override fun execute(uri: URI): String {
        val connection = uri.toURL().openConnection()
        return BufferedReader(InputStreamReader(connection.getInputStream(), "UTF-8")).use {
            it.lines().collect(Collectors.joining())
        }
    }
}