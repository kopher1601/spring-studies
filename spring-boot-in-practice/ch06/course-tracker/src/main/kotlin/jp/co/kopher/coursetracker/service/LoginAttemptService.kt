package jp.co.kopher.coursetracker.service

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

@Service
class LoginAttemptService(
    private val loginAttemptCache: LoadingCache<String, Int> = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(object : CacheLoader<String, Int>() {
        override fun load(key: String): Int {
            return 0
        }

    })
) {

    fun loginSuccess(username: String) {
        loginAttemptCache.invalidate(username)
    }

    fun loginFailed(username: String) {
        var failedAttemptCounter = try {
            loginAttemptCache.get(username)
        } catch (e: ExecutionException) {
            0
        }
        failedAttemptCounter++
        loginAttemptCache.put(username, failedAttemptCounter)
    }

    fun isBlocked(username: String): Boolean {
        return try {
            loginAttemptCache.get(username) >= MAX_ATTEMPTS_COUNT
        } catch (e: ExecutionException) {
            false
        }
    }

    companion object {
        const val MAX_ATTEMPTS_COUNT = 3
    }
}