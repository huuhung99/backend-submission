package com.submissions.back_end.api;

import com.submissions.back_end.api.response.ResponseBodyDto;
import com.submissions.back_end.api.response.ResponseCodeEnum;
import com.submissions.back_end.dto.ProblemDto;
import com.submissions.back_end.api.request.ProblemRequest;
import com.submissions.back_end.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/problem")
@CrossOrigin
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    @GetMapping
    public ResponseEntity<ResponseBodyDto<List<ProblemDto>>> getAll() {
        List<ProblemDto> problemDtos = problemService.getAll();
        ResponseBodyDto<List<ProblemDto>> response=new ResponseBodyDto(problemDtos, ResponseCodeEnum.R_200,"OK",problemDtos.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping(value = "/search/{code}/{title}/{level}",produces = "application/json")
    public ResponseEntity<ResponseBodyDto<List<ProblemDto>>> search(@PathVariable String code,@PathVariable String title,@PathVariable String level){
        List<ProblemDto> problemDtos = problemService.search(code, title, level);
        ResponseBodyDto<List<ProblemDto>> response=new ResponseBodyDto(problemDtos, ResponseCodeEnum.R_200,"OK",problemDtos.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping(value = "/{id}",produces = "application/json")
    public  ResponseEntity<ResponseBodyDto<ProblemDto>> getById(@PathVariable Long id){
        ProblemDto problemDto=problemService.findById(id);
        ResponseBodyDto<ProblemDto> response=new ResponseBodyDto(problemDto,ResponseCodeEnum.R_200,"OK");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping
    public ResponseEntity<ResponseBodyDto<ProblemDto>> addOrUpdate(@RequestBody @Valid ProblemRequest.Create request){
        ProblemDto problemDto = this.problemService.create(request);
        ResponseBodyDto<ProblemDto> response=new ResponseBodyDto<>(problemDto,ResponseCodeEnum.R_201,"Create");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseBodyDto<ProblemDto>> delete(@PathVariable long id){
        ResponseBodyDto<ProblemDto> response=new ResponseBodyDto<>();
        if(problemService.delete(id)){
            response.setMessage("xóa thành công!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.setMessage("id không tồn tại");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
