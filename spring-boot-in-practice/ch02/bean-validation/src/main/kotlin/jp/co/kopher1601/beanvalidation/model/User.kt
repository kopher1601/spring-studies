package jp.co.kopher1601.beanvalidation.model

import jp.co.kopher1601.beanvalidation.validator.Password

class User(
    private val userName: String,

    @field:Password
    private val password: String
)
