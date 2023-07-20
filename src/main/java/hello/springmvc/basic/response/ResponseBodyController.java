package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    //-------------------------------------------------------------------------------------
    // HTTP 메시지 바디에 문자열 직접 응답 방식
    
    // HttpServletResponse를 이용한 가장 단순한 방식
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    // 반환타입에 ResponseEntity<String>을 사용해
    // ResponseEntity로 직접 응답하는 방식
    // HttpStatus를 같이 보낼 수 있다.
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    // @ResponseBody 어노테이션을 이용하여 직접 Http 메시지 바디로
    // 응답하는 방식
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {

        return "ok";
    }

    //----------------------------------------------------------------------------

    // 이제는 JSON 타입으로 HTTP 메시지 바디에 내용을
    // 직접 전달하는 방식이다.

    // 반환타입을 ResponseEntity를 사용하고 제네릭변수를 HelloData(DTO)
    // 데이터를 직접 담아서 응답해준다.
    // 반환시 HttpStatus로 응답 상태를 보내줄 수 있다.
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("원진");
        helloData.setAge(29);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    // @ResponseBody 어노테이션을 사용하여
    // 반환타입에 HelloData를 넣고
    // HelloData의 set으로 데이터 입혀서
    // helloData 객체를 직접 반환
    // HttpStatus(상태)는 어떻게 보낼까?
    // @ResponseStatus(HttpStatus.~~) 를 사용하면 된다.
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("원진");
        helloData.setAge(29);

        return helloData;
    }



}
