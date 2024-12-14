package jp.co.kopher1601.commandlinerunner

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class CommandLineRunnerApplication : CommandLineRunner {

    val log = LoggerFactory.getLogger(this::class.java)

    // interface 구현 방법
    override fun run(vararg args: String?) {
        log.info("CommandLineRunner has executed!")
    }

    // 빈 등록 방법
    @Bean
    fun commandLineRunner(): CommandLineRunner {
        return CommandLineRunner { args ->
            log.info("CommandLineRunner executed as a bean definition with args.length arguments")
            for (i in 0..args.count()) {
                log.info("Argument : $args[$i]")
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CommandLineRunnerApplication>(*args)
}
