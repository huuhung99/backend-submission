package com.submissions.back_end.service;

import com.submissions.back_end.model.Language;
import com.submissions.back_end.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;
    public Language createOrupdate(Language request){
        Language findByName = this.findByName(request.getName());
        if(findByName!=null){
           return new Language();
        }
        return languageRepository.save(request);
    }
    public List<Language> getAll(){
        return languageRepository.findAll();
    }
    public Language findByName(String name){
        return languageRepository.findByName(name);
    }
    public boolean delete(Integer id){
        Optional<Language> byId = languageRepository.findById(id);
        if(byId.isPresent()){
            languageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
