package com.submissions.back_end.api;

import com.submissions.back_end.api.response.ResponseBodyDto;
import com.submissions.back_end.api.response.ResponseCodeEnum;
import com.submissions.back_end.model.Language;
import com.submissions.back_end.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/language")
public class LanguageController {
    @Autowired
    private LanguageService languageService;
    @GetMapping
    public ResponseEntity<ResponseBodyDto<List<Language>>> getAll(){
        List<Language> languages = languageService.getAll();
        ResponseBodyDto<List<Language>> response=new ResponseBodyDto(languages, ResponseCodeEnum.R_200,"OK",languages.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{name}")
    public ResponseEntity<ResponseBodyDto<Language>> findByName(@PathVariable String name){
        Language language = languageService.findByName(name);
        ResponseBodyDto<Language> response=new ResponseBodyDto<>(language,ResponseCodeEnum.R_200,"OK");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping
    public ResponseEntity<ResponseBodyDto<Language>> createOrUpdate(@RequestBody Language request){
        Language create = languageService.createOrupdate(request);
        ResponseBodyDto<Language> response=new ResponseBodyDto<>(create,ResponseCodeEnum.R_201,"Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto<Language>> delete(@PathVariable Integer id){
        ResponseBodyDto<Language> response=new ResponseBodyDto<>();
        if(languageService.delete(id)){
            response.setMessage("Xóa thành công!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.setMessage("id không tồn tại.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}