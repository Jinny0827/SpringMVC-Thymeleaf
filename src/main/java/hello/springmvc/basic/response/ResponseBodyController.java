package hello.springmvc.basic.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
