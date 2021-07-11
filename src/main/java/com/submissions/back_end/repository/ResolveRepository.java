package com.submissions.back_end.repository;

import com.submissions.back_end.model.Resolve;
import com.submissions.back_end.model.ResolveStatistical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResolveRepository extends JpaRepository<Resolve,Long> {
    String ranking="SELECT u.*,SUM(tmp.point) as sum \n" +
            "FROM tbluser as u, (\tSELECT DISTINCT user.id as userid,p.level as point,p.id\n" +
            "\t\tFROM tblresolve as r\n" +
            "\t\tINNER JOIN tbluser as user on user.id = r.account_id\n" +
            "\t\tINNER JOIN tblproblem as p on r.problem_id = p.id\n" +
            "\t\tWHERE r.status = 'Accepted') AS tmp \n" +
            "where tmp.userid=u.id\n" +
            "GROUP BY(tmp.userid)  \n" +
            "ORDER BY sum  DESC";
    @Query("SELECT r FROM Resolve r where r.accountId=?1")
    public List<Resolve> getResolveByAccountIdOrderBySubmissionTime(Long accountId);
    @Query("SELECT r FROM Resolve r WHERE r.accountId=?1 AND r.problem.id=?2")
    public List<Resolve> getResolveByAccountIdAndProblemIdOrderBySubmissionTime(Long accountId,Long problemId);

    @Query(value = ranking,nativeQuery = true)
    public List<Object[]> statisticalResolve();
}
