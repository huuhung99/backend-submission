package com.submissions.back_end.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbllanguage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    private Integer id;
    private String name;
    private boolean is_archived;
//    @OneToMany(mappedBy = "resolve", cascade = CascadeType.ALL)
//    private List<Resolve> resolves;
}
