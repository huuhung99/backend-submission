package com.submissions.back_end.service;

import com.submissions.back_end.api.request.UserRequest;
import com.submissions.back_end.error.BadRequestException;
import com.submissions.back_end.model.User;
import com.submissions.back_end.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public List<User> getAllByName(String name){
        return userRepository.findAllByNameIgnoreCase(name);
    }
    public User createOrUpdate(UserRequest.Create request){
        User user=new User();
        BeanUtils.copyProperties(request,user);
        User byUserName = userRepository.findByUserName(request.getUser_name());
        //id null thì tạo mới
        if(request.getId()==null){
            if(byUserName!=null){
                throw new BadRequestException("user name đã tồn tại");
            }
        }
        return userRepository.save(user);
    }
    public User findById(Long id){
        Optional<User> byId = userRepository.findById(id);
        if(!byId.isPresent())
            throw new BadRequestException("id user không tồn tại");
        return byId.get();
    }
    public User checkLogin(String username,String password){
        User user = userRepository.checkLogin(username, password);
        if(user!=null)
            return user;
        return null;
    }
}
