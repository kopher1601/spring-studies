package jp.kopher1601.splearn.domain;

import lombok.Getter;

//@MappedSuperclass
public abstract class AbstractEntity {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
}
