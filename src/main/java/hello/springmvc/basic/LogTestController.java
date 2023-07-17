package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// aplication.properties 를 통해 전체적인 로그 지정이 가능하다.
// @Slf4j 어노테이션 처리로 매번 Logger log 생성처리를 하지않아도 된다.

@Slf4j
@RestController
public class LogTestController {

    // 로그 사용을 위한 SLF4J 라이브러리 사용
    // private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest() {

        String name = "Spring";

        System.out.println("name = " + name);

        // apllication.properties 에서 이 클래스가 속한 패키지의 기본 로깅 레벨이 debug이다.
        // trace는 debug의 상위 레벨이기 때문에 출력이 불가
        // 그리고 "trace log =" + name 은 자바 언어의 특성 때문에..
        // 문자열 + 문자열 = 문자열로 인식
        // "trace log = Spring" 로 인식한다.
        log.trace("trace log =" + name);

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
