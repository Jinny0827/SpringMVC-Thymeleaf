package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// HTTP 메시지 바디가 JSON 타입으로 들어올 때 로그에 데이터 찍어보자
// JSON 타입의 메시지를 우리가 만든 DTO인 HelloData에 객체화시킨다.
// 처음에 스프링이 필요로 하는 스펙을 전부 지키고 점진적으로 개선해보자

@Slf4j
@Controller
public class RequestBodyJsonController {

    //JSON 데이터를 받기 위해 ObjectMapper 객체 사용
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 메시지 바디의 내용을 전부 가져오기 위해서 InputStream 사용
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}" , messageBody);

        // messageBody의 내용이 JSON이고 이걸 HelloData에 파싱한다.
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData = {}", helloData);

        response.getWriter().write("ok");
    }

    // @RequestBody 어노테이션 매개변수로 사용
    // @ResponseBody 반환으로 메시지 바디에 직접 응답
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody = {}" , messageBody);

        // messageBody의 내용이 JSON이고 이걸 HelloData에 파싱한다.
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData = {}", helloData);

        return "ok";
    }

    // InputStream을 통해서 문자열(String)으로 반환하는 일을 messageBody로
    // 매개변수에 직접적으로 선언하는거로 개선했다면
    // ObjectMapper를 사용해서 messageBody를 HelloData에 파싱하는 일을 줄여볼순없을까?
    // 매개변수에 @RequestBody 선언시 직접적으로 HelloData 타입선언하면 된다.
    // (@RequestBody HelloData helloData)

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {

        log.info("helloData.getUsername = {}, helloData.getAge = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }
    
    // HttpEntity를 사용한 방식도 적어보자
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {

        HelloData helloData = httpEntity.getBody();

        log.info("helloData.getUsername = {}, helloData.getAge = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    // @RequestBody HelloData data 를 매개변수로 직접 메시지 바디내용을
    // String(문자열)로 변환해 JSON의 요청 메시지를 HelloData에 파싱해줬다.
    // 반환 타입을 HelloData로 한다면 요청 내용을 그대로 메시지 바디에 적어 반환할 수 있을까?
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {

        log.info("helloData.getUsername = {}, helloData.getAge = {}", data.getUsername(), data.getAge());

        return data;
    }

}
