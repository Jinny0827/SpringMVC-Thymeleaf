package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

// 메시지 바디에 직접적으로 데이터를 전달하기 위해 RestController 사용
@RestController
public class MappingController {

    //로그를 남기기 위해서 SLF4J 라이브러리 안의 Logger 사용
    private final Logger log = LoggerFactory.getLogger(getClass());

    // 일반적인 리퀘스트 매핑 - 메시지바디에 return(반환값) 직접 전달
    @RequestMapping({"/hello-basic", "/hello-go"})
    public String helloBasic() {
        log.info("hellobasic");

        return "ok";
    }
    // Get 매핑의 1번째 방법
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGet1() {
        log.info("hellobasic");

        return "ok";
    }
    // Get 매핑의 2번째 방법
    // HTTP 메서드를 축약해서 적으면 더 직관적이다.
   @GetMapping("/mapping-get-v2")
    public String mappingGet2() {
        log.info("hellobasic");

        return "ok";
    }

    // PathVariable을 통한 변수명이 같으면 생략 가능
    // PathVariable("userId") String userId -> @Pathvariable userId
    // /mapping/userA
    // URL 자체에 값이 들어가있는것 그것을 파라미터로 사용가능

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {

        log.info("mappingPath userId = {}", data);

        return "a";
    }

    // 다중매핑
    // 경로 변수를 두가지를 줬을때 예시
    // {userId}에 해당하는 {orderId}를 가져온다.
    // 유저아이디에 해당하는 주문번호를 가져온다.
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable long orderId) {

        log.info("mappingPath userId={}, orderId={}", userId, orderId);

        return "ok";
    }

    // 특정 파라미터 요청이 있을 경우에만 요청되는 매핑
    // 주소/매핑메서드 + 파라미터의 조건 충족
    // 파라미터에 대한 조건이 충족됬을때만 매핑
    // 조건 미충족시 400에러(Bad Request) 처리
    // params="mode", params="!mode"(mode가 아닐경우), params="mode!=debug", params = {"mode=debug", "data=good"} 도 가능
    // {}는 두가지 조건이 모두 충족되었을 경우
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    // 특정 헤더의 조건 매핑
    // 헤더의 조건이 mode=debug 일때만 매핑 가능
    // postman으로 테스트 가능
    // headers의 내용에 key에 mode, value에 debug 일 경우에만 동작
    // 아닐경우 404에러
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {

        log.info("mappingHeader");

        return "ok";
    }

    // HTTP 요청에 ContentType에 조건을 달고 매핑한 경우
    // postman으로 테스트 가능
    // headers에 content-Type이 application/json일 경우에만 매핑 가능
    // json으로 데이터를 보내주기 때문에 PostMapping 처리
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsume() {

        log.info("mappingConsume");

        return "ok";
    }

    // Accept 헤더 기반의 Media Type 조건
    // 초기 Accept 설정이 */* 로 설정되어있어 에러가 없다.\
    // 임의적으로 Accept를 application/json으로 변경시 406 에러
    // Not Acceptable
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduce() {

        log.info("mappingProduce");

        return "ok";
    }

}
