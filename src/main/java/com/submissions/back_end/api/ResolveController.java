package com.submissions.back_end.api;

import com.submissions.back_end.api.request.ResolveRequest;
import com.submissions.back_end.api.response.ResponseBodyDto;
import com.submissions.back_end.api.response.ResponseCodeEnum;
import com.submissions.back_end.error.BadRequestException;
import com.submissions.back_end.model.Resolve;
import com.submissions.back_end.model.ResolveStatistical;
import com.submissions.back_end.repository.ResolveStatisticalMapper;
import com.submissions.back_end.service.ResolveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resolve")
public class ResolveController {
    @Autowired
    private ResolveService resolveService;
//    @Autowired
//    private ResolveStatisticalMapper resolveStatisticalMapper;
//    @GetMapping("/ranking")
//    public ResponseEntity<ResponseBodyDto<ResolveStatistical>> ranking(){
//        List<ResolveStatistical> ranking = resolveStatisticalMapper.ranking();
//        ResponseBodyDto<ResolveStatistical> response=new ResponseBodyDto(ranking, ResponseCodeEnum.R_200,"OK",ranking.size());
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
    @GetMapping("/ranking")
    public ResponseEntity<ResponseBodyDto<ResolveStatistical>> ranking(){
        List<ResolveStatistical> ranking = resolveService.ranking();
        ResponseBodyDto<ResolveStatistical> response=new ResponseBodyDto(ranking, ResponseCodeEnum.R_200,"OK",ranking.size());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping
    public ResponseEntity<ResponseBodyDto<Resolve>> create(@RequestBody ResolveRequest request){
        Resolve resolve=this.resolveService.create(request);
        ResponseBodyDto<Resolve> response=new ResponseBodyDto(resolve, ResponseCodeEnum.R_201,"Create");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<ResponseBodyDto<List<Resolve>>> getAll(){
        List<Resolve> resolves=this.resolveService.getAll();
        ResponseBodyDto<List<Resolve>> response=new ResponseBodyDto(resolves,ResponseCodeEnum.R_200,"OK", resolves.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseBodyDto<List<Resolve>>> getAllResolveByAccountId(@PathVariable Long id){
        List<Resolve> resolves=this.resolveService.getAllResolveByUserId(id);
        ResponseBodyDto<List<Resolve>> response=new ResponseBodyDto(resolves,ResponseCodeEnum.R_200,"OK", resolves.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyDto<Resolve>> getResolveById(@PathVariable Long id){
        Resolve resolveById = this.resolveService.getResolveById(id);
        if(resolveById==null)
            throw new BadRequestException("id không tồn tại");
        ResponseBodyDto<Resolve> response=new ResponseBodyDto(resolveById,ResponseCodeEnum.R_200,"OK");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/user/{accountId}/{problemId}")
    public ResponseEntity<ResponseBodyDto<List<Resolve>>> getAllResolveByAccountId(@PathVariable Long accountId,@PathVariable Long problemId){
        List<Resolve> resolves=this.resolveService.getAllResolveByUserIdAndProblemId(accountId,problemId);
        ResponseBodyDto<List<Resolve>> response=new ResponseBodyDto(resolves,ResponseCodeEnum.R_200,"OK", resolves.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
