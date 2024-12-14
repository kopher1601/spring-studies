package jp.co.kopher1601.configurationproperties.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


@ConfigurationProperties("app.sbip.ct")
data class AppProperties @ConstructorBinding constructor(
    val name: String,
    val ip: String,
    val port: Int,
    val security: Security,
) {
    data class Security(
        val enabled: Boolean,
        val token: String,
        val roles: List<String>
    )
}
