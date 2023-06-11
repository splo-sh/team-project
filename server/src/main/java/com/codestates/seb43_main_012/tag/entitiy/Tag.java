package com.codestates.seb43_main_012.tag.entitiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // conversationTag 연결테이블을 통해 conversation 과 다대다로 매핑해야함

    public Tag(String name)
    {
        this.name = name;
    }
}
