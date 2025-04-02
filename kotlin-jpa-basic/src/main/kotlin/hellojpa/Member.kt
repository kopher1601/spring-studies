package hellojpa

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Member(
    var name: String,
    @Id
    var id: Long? = null,
)