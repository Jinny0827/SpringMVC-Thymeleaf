package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    // GET, POST 구분 없이 HTTP 요청 데이터를 객체화 해서 사용가능
    // 쿼리 파라미터의 정보를 가공하는 것에 의의를 둔다.
    
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }
    
    // @RequestParam을 이용하여 HTTP 요청 데이터를 객체화 시킨다.
    // return "ok"; 를 그냥 사용하면 viewResolver를 통해 ok.jsp를 찾게되는데 없으므로 에러난다.
    // 메서드 레벨단에 @ResponseBody를 사용하거나 클래스 레벨단에 @RestController를 사용하면 되는데.
    // 현재 메서드만 메세지 바디에 직접적으로 내용을 적어 반환할거기때문에 @ResponseBody 사용

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {

        log.info("username = {}, age = {}", memberName, memberAge);

        return "ok";
    }

    // 바로 전 메서드에서 파라미터명과 변수명이 같다는 조건하에
    // ("value") 부분을 지웠다.
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {

        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    // 더 개선할 수 있었다.
    // 파라미터의 이름과 변수명이 같다는 조건하에
    // 매개변수에 그냥 변수 선언만 해줘도 알아서 매핑된다.

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    // @RequestParam에 required를 사용하여 요청 파라미터의 필수값을 조정할수 있다.
    // 기본값(default)는 true이며 파라미터 필수이다.
    // 파라미터 필수값을 보내지 않고 통신시 400 예외 발생(HTTP 스펙 미준수)
    // false를 사용했는데 매개변수에서 int와 같은 자바의 기본자료형 사용시 null값을 사용할수 없으므로
    // Integer(객체타입)으로 타입을 변경해준다.
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {

        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    // 파라미터의 기본 값 적용 - requestParamDefault
    // @RequestParam에 defaultValue를 사용하여 기본값을 지정해줄 수 있다.
    // required가 true여도 빈값이 들어오면 ""이 아닌 defaultValue로 지정해놓은 값이 저장되고
    // required가 false여도 빈값이 아닌 defaultValue로 지정해놓은 데이터가 들어오기 때문에 int를 사용할수 있다.

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    // 요청 파라미터를 Map 타입으로 받을 수 있다.
    // 모든 요청 파라미터 정보를 다 받고싶다면 사용
    // @RequestParam Map<String, Object> paramMap 매개변수 사용
    // 로그에 파라미터 정보를 띄워야 할때는 paramMap.get("요청파라미터이름")
    // 파라미터의 키에 해당하는 값이 복수형태라면 MultiValueMap을 사용해야한다.
    // 파라미터의 키에 해당하는 값이 복수인 형태는 거의 없지만 있다면 MultiValueMap 사용
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

}
