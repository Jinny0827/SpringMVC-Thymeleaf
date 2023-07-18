package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

// HTTP 요청 - 기본, 헤더의 정보
// 나만의 MVC 프레임워크를 만들때 처음으로 했던 HTTP 요청 헤더의 정보를 받아올 때와 마찬가지로
// 스프링 MVC 프레임워크의 헤더 정보를 받아와보자

@Slf4j
@RestController
public class RequestHeadersController {

    // 각종 헤더의 정보들을 가져오기 위해서 매개변수로
    // HttpServlet 요청,응답
    // Locale 정보(ko_kr)
    // HttpMethod 정보(GET, POST, DELETE ...)
    // 헤더의 전체 정보(@RequestHeader MultiValueMap<>)
    // 헤더의 각각 정보(@RequestHeader("host"))
    // 쿠키의 정보(@CookieValue)
    // required = false 는 없으면 안가져와도 된다는 뜻
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request, HttpServletResponse response,
                          Locale locale, HttpMethod httpMethod,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie) {

        log.info("request = {}", request);
        log.info("response = {}", response);
        log.info("httpMethod = {}", httpMethod);
        log.info("Locale = {}", locale);
        log.info("RequestHeader 전체 정보 = {}", headerMap);
        log.info("RequestHeader host 정보 = {}", host);
        log.info("Cookie의 값 = {}", cookie);


        return "ok";
    }
}
