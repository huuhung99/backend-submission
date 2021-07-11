package com.submissions.back_end.repository;

import com.submissions.back_end.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,Long> {
    @Query("select p from Problem p where p.code like '%?1' and p.title like '%?2%' and p.level=?3")
    List<Problem> findAllByCodeAndTitleAndTopic(@RequestParam String code,@RequestParam String title,@RequestParam String topic);
    Problem findAllByCode(@RequestParam String code);
}
