package com.submissions.back_end.service;

import com.submissions.back_end.dto.ProblemDto;
import com.submissions.back_end.model.Problem;
import com.submissions.back_end.repository.ProblemRepository;
import com.submissions.back_end.api.request.ProblemRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    public List<ProblemDto> getAll() {
        List<Problem> all = problemRepository.findAll();
        return all.stream().map(this::convertProblemToProblemDto).collect(Collectors.toList());
    }

    public ProblemDto findById(Long id){
        Optional<Problem> byId = problemRepository.findById(id);
        if(byId.isPresent()){
            return convertProblemToProblemDto(byId.get());
        }
        return new ProblemDto();
    }

    public ProblemDto create(ProblemRequest.Create request) {
        ProblemDto problemDto = new ProblemDto();
        BeanUtils.copyProperties(request, problemDto);
        Problem problem = problemRepository.findAllByCode(request.getCode());
        // update
        if (problem != null) {
            problemDto.setId(problem.getId());
        }
        // add new
        return this.convertProblemToProblemDto(this.problemRepository.save(this.convertProblemDtoToProblem(problemDto)));
    }

    public boolean delete(long id) {
        Optional<Problem> byId = this.problemRepository.findById(id);
        if (byId.isPresent()) {
            this.problemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ProblemDto> search(String code, String title, String level) {
        return this.problemRepository.findAllByCodeAndTitleAndTopic(code, title, level).stream()
                .map(this::convertProblemToProblemDto).collect(Collectors.toList());
    }

    private ProblemDto convertProblemToProblemDto(Problem problem) {
        ProblemDto problemDto = new ProblemDto();
        BeanUtils.copyProperties(problem, problemDto);
        return problemDto;
    }

    private Problem convertProblemDtoToProblem(ProblemDto problemDto) {
        Problem problem = new Problem();
        BeanUtils.copyProperties(problemDto, problem);
        return problem;
    }
}
