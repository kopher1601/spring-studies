package jp.kopher.customspringdatarepository.repository

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository

@NoRepositoryBean
interface BaseRepository<T, ID> : Repository<T, ID> {

    // java = <S extends T> S save(S entity);
    fun <S : T?> save(entity: S): S
    fun findAll(): Iterable<T>

}
