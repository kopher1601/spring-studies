package jp.co.kopher1601.kotlinpractice.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity(
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private val createdDateTime: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(nullable = false)
    private val updatedDateTime: LocalDateTime = LocalDateTime.now(),
)