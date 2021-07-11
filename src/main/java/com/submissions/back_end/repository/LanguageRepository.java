package com.submissions.back_end.repository;

import com.submissions.back_end.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Integer> {
    @Query("select l from Language l where l.name=?1")
    Language findByName(String name);
}
