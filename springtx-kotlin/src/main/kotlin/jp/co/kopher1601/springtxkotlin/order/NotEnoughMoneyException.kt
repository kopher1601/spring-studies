package jp.co.kopher1601.springtxkotlin.order

class NotEnoughMoneyException(
    message: String,
) : Exception(message)