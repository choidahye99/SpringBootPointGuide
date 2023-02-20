package com.springboot.springproject.controller;

import com.springboot.springproject.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.coyote.Response;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@RestController
@RequestMapping("/rest")
public class RestApiController {


    //private final Logger LOGGER = (Logger) LoggerFactory.getLogger(RestApiController.class);

    @GetMapping("/hello")
    public  String hello(){
        //LOGGER.info("hello 메서드가 호출되었습니다.");
        return "hello";
    }

    @GetMapping("/name")
    public String getName() {
        //LOGGER.info("getName 메서드가 호출되었습니다.");
        return "Flature";
    }

    @GetMapping(value = "/variable/{variable}")
    public String getVariable(@PathVariable String variable){
        return variable;
    }

    @ApiOperation(value = "GET 메서드 예제", notes = "@RequestParam을 활용한 Get Method")
    @GetMapping("/request1")
    public String getRequestParam(@ApiParam(value = "이름", required = true) @RequestParam String name,
                                  @ApiParam(value = "이메일", required = true) @RequestParam String email,
                                  @ApiParam(value = "회사", required = true)@RequestParam String organization){
        return name = " " + email + " " + organization;
    }

    //RequestParam과 Map 조합
        //회원가입을 하며 ID같은 필수 항목이 아닌 취미 같은 선택항목에 대해서 기입하지 않는 경우가 있음 => 매개 변수가 일정하지 않을 수 있어 Map으로 받는 게 좋다
    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param){
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : "+map.getValue()+"\n");
        });

        return sb.toString();
    }

    @PostMapping("/domain")
    public String postExample(){
        return "Hello Post API";
    }

    @PostMapping("/member")
    public String postMapping(@RequestBody Map<String, Object> postData){
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue()+"\n");
        });

        return sb.toString();
    }

    @PostMapping("/member2")
    public String postMemberDto(@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }

    @PutMapping("/member")
    public String putMember(@RequestBody Map<String, Object> putData){
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map->{
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    //결과값을 string으로 전달하면 content-type : text/plain
    @PutMapping("/member1")
    public String putMember1(@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }

    //content-type: application/json
    @PutMapping("/member2")
    public MemberDto putMember2(@RequestBody MemberDto memberDto){
        return memberDto;
    }

    @PutMapping("/member3")
    public ResponseEntity<MemberDto> putMember3(@RequestBody MemberDto memberDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDto);
    }

    @DeleteMapping("/{variable}")
    public String DeleteVariable(@PathVariable String variable){
        return variable;
    }

    @DeleteMapping("/request1")
    public String getRequestParam1(@RequestParam String email){
        return "email : "+email;
    }

}
