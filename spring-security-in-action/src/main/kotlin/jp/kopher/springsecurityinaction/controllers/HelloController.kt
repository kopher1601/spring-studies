package jp.kopher.springsecurityinaction.controllers

import org.springframework.scheduling.annotation.Async
import org.springframework.security.concurrent.DelegatingSecurityContextCallable
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable
import java.util.concurrent.Executors


@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(authentication: Authentication): String {
        return "Hello, ${authentication?.name}!"
    }

    @GetMapping("/bye")
    @Async
    fun goodbye() {
        val context = SecurityContextHolder.getContext()
        val authentication = context.authentication
        println("goodbye, ${authentication?.name}") // null
    }

    @GetMapping("/ciao")
    fun ciao(): String {
        val task = Callable {
            val context = SecurityContextHolder.getContext()
            context.authentication.name
        }

        val e = Executors.newCachedThreadPool()
        try {
            // タスクを Decorate する方法での解決
            val contextTask = DelegatingSecurityContextCallable(task)
            return "Ciao, " + e.submit(contextTask).get() + "!"
        } finally {
            e.shutdown()
        }
    }

    @GetMapping("/hola")
    fun hola(): String {
        val task = Callable {
            val context = SecurityContextHolder.getContext()
            context.authentication.name
        }

        var e = Executors.newCachedThreadPool()
        e = DelegatingSecurityContextExecutorService(e)
        try {
            return "Hola, " + e.submit(task).get() + "!"
        } finally {
            e.shutdown()
        }
    }
}