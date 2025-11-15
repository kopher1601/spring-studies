package jp.kopher.springbootinpractice.properties


import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app.sbip.ct")
data class AppProperties(
    /**
     * Application Name
     */
    val name: String,
    /**
     * Application IP
     */
    val ip: String,
    /**
     * Application IP
     */
    val port: Int,
    /**
     * Application Security configuration
     */
    val security: Security
) {
    data class Security(
        /**
         * Enable Security. Possible values true/false
         */
        val isEnabled: Boolean,
        /**
         * Token Value
         */
        val token: String,
        /**
         * Available roles
         */
        val roles: List<String>
    )
}