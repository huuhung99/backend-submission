package com.submissions.back_end.api;

import com.submissions.back_end.api.request.UserRequest;
import com.submissions.back_end.api.response.ResponseBodyDto;
import com.submissions.back_end.api.response.ResponseCodeEnum;
import com.submissions.back_end.model.User;
import com.submissions.back_end.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserConntroller {
    @Autowired
    private UserService userService=new UserService();
    @GetMapping
    public ResponseEntity<ResponseBodyDto<List<User>>> getAll(){
        List<User> users = userService.getAll();
        ResponseBodyDto<List<User>> response = new ResponseBodyDto(users, ResponseCodeEnum.R_200, "OK", users.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{name}")
    public ResponseEntity<ResponseBodyDto<List<User>>> getAllByName(@PathVariable String name){
        List<User> users = userService.getAllByName(name);
        ResponseBodyDto<List<User>> response = new ResponseBodyDto(users, ResponseCodeEnum.R_200, "OK", users.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping
    public ResponseEntity<ResponseBodyDto<User>> createOrUpdate(@RequestBody UserRequest.Create request){
        User user = userService.createOrUpdate(request);
        ResponseBodyDto<User> response=new ResponseBodyDto<>(user,ResponseCodeEnum.R_201,"CREATE");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{username}/{password}")
    public ResponseEntity<ResponseBodyDto<User>> checkLogin(@PathVariable String username,@PathVariable String password){
        User user = userService.checkLogin(username,password);
        ResponseBodyDto<User> response;
        if(user==null){
            response=new ResponseBodyDto<>(user,ResponseCodeEnum.R_401,"Login failed!");
        }
        else response=new ResponseBodyDto<>(user,ResponseCodeEnum.R_200,"OK");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
