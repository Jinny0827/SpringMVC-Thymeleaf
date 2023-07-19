package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    // 요청 파라미터가 아닌 직접 메시지 바디로 TEXT 데이터가 날아올 경우
    // @ModelAttribute 혹은 @RequestParam을 사용할 수 없다.
    // 이를 받아서 객체화시켜주기 위해선 InputStream을 사용해야 한다.
    // HttpServletRequest를 사용해 request.getInputStream(); 을 통해 받아와
    // ServletInputStream 타입으로 객체화 한다.(inputStream)
    // 객체를 로그로 찍어보기 위해 StreamUtils 안에 copyToString을 사용하는데
    // 매개변수로 (사용할 객체, 인코딩타입)을 적어준다.
    // 인코딩 타입은 꼭 명시해줘야 한다.

    @RequestMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message Body = {}", messageBody);

        response.getWriter().write("ok");
    }

    // 코드 개선
    // 매개변수로 InputSteam을 직접 사용할수 있다.
    // Writer를 매개변수로 사용해 response 대신 사용가능
    @RequestMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer writer) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message Body = {}", messageBody);

        writer.write("ok");
    }
    
    // Http message Converter 를 통해 코드를 개선할 수 있다
    // HttpEntity<> 사용
    // 반환(response) 타입 객체 HttpEntity는
    // HTTP 메시지를 스펙화 해놓은거 그대로 가져온 것이라고 생각하자.

    @RequestMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();

        log.info("message Body = {}", messageBody);

       return new HttpEntity<>("ok");
    }

    // 매개변수 HttpEntity를 생략할 수 있다.
    // @RequestBody를 사용
    // 응답의 HttpEntity도 @ResponseBody로 수정 가능

    @ResponseBody
    @RequestMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("message Body = {}", messageBody);

        return "ok";
    }

}
