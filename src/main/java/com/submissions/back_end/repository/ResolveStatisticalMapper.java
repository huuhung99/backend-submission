package com.submissions.back_end.repository;

import com.submissions.back_end.model.ResolveStatistical;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ResolveStatisticalMapper {
    public List<ResolveStatistical> ranking();
}
