package com.submissions.back_end.service;

import com.submissions.back_end.api.request.ResolveRequest;
import com.submissions.back_end.error.BadRequestException;
import com.submissions.back_end.model.Problem;
import com.submissions.back_end.model.Resolve;
import com.submissions.back_end.model.ResolveStatistical;
import com.submissions.back_end.repository.LanguageRepository;
import com.submissions.back_end.repository.ProblemRepository;
import com.submissions.back_end.repository.ResolveRepository;
import com.submissions.back_end.util.DateFormatter;
import kong.unirest.*;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ResolveService {
    private static final String API_CHECK_CODE= "https://judge0-ce.p.rapidapi.com/submissions";
    private static final String API_GET_RESULT= "https://api.judge0.com/submissions/";
//    private static final String API_GET_RESULTS= "https://judge0-ce.p.rapidapi.com/submissions/batch?tokens=";
    private static final String API_GET_RESULTS= "https://ce.judge0.com/submissions/batch?tokens=";
    @Autowired
    private ResolveRepository resolveRepository;
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private LanguageRepository languageRepository;
    public List<ResolveStatistical> ranking(){
        List<ResolveStatistical> resolveStatisticals=new ArrayList<>();
        List<Object[]> objects = resolveRepository.statisticalResolve();
        for(int i=0;i<objects.size();i++){
            ResolveStatistical resolveStatistical=new ResolveStatistical();
            Object[] tmp = objects.get(i);
            if(tmp[0]!=null)
                resolveStatistical.setId(Long.parseLong(String.valueOf(tmp[0])));
            if(tmp[1]!=null)
                resolveStatistical.setAddress((String)tmp[1]);
            if(tmp[2]!=null)
                resolveStatistical.setDate_of_birth(Long.valueOf(String.valueOf(tmp[2])));
            if(tmp[3]!=null)
                resolveStatistical.setEmail((String)tmp[3]);
            if(tmp[4]!=null)
                resolveStatistical.setFirst_name((String)tmp[4]);
            if(tmp[5]!=null)
                resolveStatistical.setLast_name((String)tmp[5]);
            if(tmp[6]!=null)
                resolveStatistical.setPassword((String)tmp[6]);
            if(tmp[7]!=null)
                resolveStatistical.setPhone((String)tmp[7]);
            if(tmp[8]!=null)
                resolveStatistical.setSex((String)tmp[8]);
            if(tmp[9]!=null)
                resolveStatistical.setUser_name((String)tmp[9]);
            if(tmp[10]!=null)
                resolveStatistical.setSum(Math.round(Float.valueOf(String.valueOf(tmp[10]))));
            resolveStatisticals.add(resolveStatistical);
        }
        return resolveStatisticals;
    }
    public Resolve create(ResolveRequest resolveRequest){
        // search problem by id
        Problem problem = problemRepository.findById(resolveRequest.getProblem_id()).get();
        // has code
//        resolveRequest.setAccount_id(resolveRequest.getAccount_id());
//        resolveRequest.setProblem_id(problem.getId());
//        resolveRequest.setSource_code("import java.util.Scanner;\n" +
//                "\n" +
//                "public class Main {\n" +
//                "    public static void main(String[] args) throws Exception{\n" +
//                "        String n=new Scanner(System.in).nextLine();\n" +
//                "        System.out.println(n);\n" +
//                "    }\n" +
//                "}");
//
//        resolveRequest.setLanguage_id(resolveRequest.getLanguage_id());
//        resolveRequest.setStdin(problem.getInput());
//        resolveRequest.setExpected_output(problem.getOutput());
        JSONArray jsonArray=new JSONArray();
//        List<JSONObject> list=new ArrayList<>();
        for(int i=0;i<problem.getTest_case().size();i++){
            JSONObject createRequest = new JSONObject();
//            createRequest.put("account_id",resolveRequest.getAccount_id());
//            createRequest.put("problem_id",resolveRequest.getProblem_id());
            createRequest.put("source_code",resolveRequest.getSource_code());
//            createRequest.put("submission_time", new Date());
            createRequest.put("language_id", resolveRequest.getLanguage_id());
            createRequest.put("stdin", problem.getTest_case().get(i).getInput());
            createRequest.put("expected_output", problem.getTest_case().get(i).getExpect_output());
            createRequest.put("cpu_time_limit", resolveRequest.getCpu_time_limit());
            createRequest.put("memory_limit", problem.getMemory_limit());
//            list.add(createRequest);
            jsonArray.put(createRequest);
        }
        JSONObject request = new JSONObject();
        request.put("submissions",jsonArray);

        HttpResponse<String> response = Unirest.post("https://judge0-ce.p.rapidapi.com/submissions/batch")
                .header("content-type", "application/json;charset=utf-8")
                .header("x-rapidapi-host", "judge0-ce.p.rapidapi.com")
                .header("x-rapidapi-key", "a6f120da85mshd95e745176e9798p1eb296jsn005a80ad960a")
                .header("cache-control", "no-cache")
                .header("postman-token", "b875e2a5-e899-a354-c391-7ceb8689fd47")
                .body(request)
                .asString();
        JSONArray tokens=new JSONArray(response.getBody());
        String token="";
        for(int i=0;i<tokens.length();i++){
            if(i==0)
                token+=tokens.getJSONObject(i).getString("token");
            else token+=","+tokens.getJSONObject(i).getString("token");
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpResponse<String> jsonNodeHttpResponse = Unirest.get(API_GET_RESULTS + token+"&base64_encoded=true&fields=*")
                .header("content-type", "application/json;charset=utf-8")
                .header("x-rapidapi-key", "1ea4efebfamshf0a205ab9e87a07p166590jsn5360d16176c4")
                .header("x-rapidapi-host", "judge0-ce.p.rapidapi.com")
//                .header("cache-control", "no-cache")
//                .header("postman-token", "d763e9e1-b4f0-e5a4-26d7-07a87bb5eba6")
                .asString();
        JSONObject jsonObject = new JSONObject(jsonNodeHttpResponse.getBody());
        System.out.println(jsonObject.toString());
        JSONArray jsonArray1;
        int index=-2;
        try {
            jsonArray1=jsonObject.getJSONArray("submissions");
            System.out.println(jsonArray1.toString());
            for(int i=0;i<jsonArray1.length();i++){
                JSONObject jsonObject1=jsonArray1.getJSONObject(i).getJSONObject("status");
                if(jsonObject1.getString("description").equals("Accepted")){
                    index=-1;
                }
                else {
                    index=i;
                    break;
                }
            }
        }
        catch (Exception e){
            throw new BadRequestException("lỗi server! vui lòng thử lại sau ít phút!");
        }

        Resolve resolve=new Resolve();
        switch (index){
            case -2:
                throw new BadRequestException("con ga phuc!");
            case -1:
                JSONObject jsonObject2 = jsonArray1.getJSONObject(0);
                resolve=this.jsonToResolve(jsonObject2,resolveRequest,problem);
                break;
            default:
                JSONObject jsonObject3 = jsonArray1.getJSONObject(index);
                resolve=this.jsonToResolve(jsonObject3,resolveRequest,problem);
                break;
        }

        resolve.setLanguage(languageRepository.findById(resolveRequest.getLanguage_id()).get());
        Resolve save = resolveRepository.save(resolve);
        return save;
    }
    public List<Resolve> getAll(){
        return resolveRepository.findAll();
    }
    public List<Resolve> getAllResolveByUserId(Long id){
        return resolveRepository.getResolveByAccountIdOrderBySubmissionTime(id);
    }
    public List<Resolve> getAllResolveByUserIdAndProblemId(Long id,Long problemId){
        return resolveRepository.getResolveByAccountIdAndProblemIdOrderBySubmissionTime(id,problemId);
    }
    public Resolve getResolveById(Long id){
        Optional<Resolve> byId = resolveRepository.findById(id);
        return byId.isPresent()?byId.get():null;
    }

    public Resolve jsonToResolve(JSONObject jsonObject,ResolveRequest request,Problem problem){
        Resolve resolve=new Resolve();
        resolve.setAccountId(request.getAccount_id());
        resolve.setProblem(problem);
        resolve.setStatus(jsonObject.getJSONObject("status").getString("description"));
        Float time;
        try {
            time=jsonObject.getFloat("time");
        }
        catch (Exception e){
            time=null;
        }
        resolve.setTime(time);
        resolve.setSubmissionTime(new Date());
        Integer memory;
        try {
            memory=jsonObject.getInt("memory");
        }
        catch(Exception e){
            memory=null;
        }
        resolve.setMemory(memory);
        resolve.setToken(jsonObject.getString("token"));
        String compile_output;
        try {
            compile_output = jsonObject.getString("compile_output");
        }
        catch (Exception e){
            compile_output=null;
        }
        resolve.setComplieOutput(compile_output);
        String message;
        try {
            message= jsonObject.getString("message");
        }
        catch (Exception e){
            message=null;
        }
        resolve.setMassage(message);
        return  resolve;
    }
}
