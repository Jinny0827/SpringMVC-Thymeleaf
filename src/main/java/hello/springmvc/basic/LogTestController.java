package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// aplication.properties 를 통해 전체적인 로그 지정이 가능하다.

@RestController
public class LogTestController {

    //로그 사용을 위한 SLF4J 라이브러리 사용
    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest() {

        String name = "Spring";

        System.out.println("name = " + name);

        // 로그별 찍어내기
        // trace부터 error까지 로그의 중요도 순서 기억
        log.trace("trace log = {}" + name);
        log.debug("debug log = {}" + name);
        log.info("info log = {}" + name);
        log.warn("warn log = {}" + name);
        log.error("error log = {}" + name);
        

        return "ok";
    }

}
