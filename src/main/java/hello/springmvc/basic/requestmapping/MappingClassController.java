package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

// HTTP API 를 통해 회원관리 로직을 구성해보자
// 데이터베이스에 데이터가 들어간다고 생각하고 매핑만 시도

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    // 회원 목록 조회
    @GetMapping
    public String user() {
        return "get users";
    }

    // 회원 등록
    @PostMapping
    public String addUser() {
        return "add user";
    }
    
    // 회원 상세 조회(회원 아이디에 해당하는 회원 상세 조회)
    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {


        return "get userId = " + userId;
    }
    
    // 회원 정보 수정
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {

        return "update userId = " + userId;
    }

    // 회원 삭제
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {

        return "delete userId = " + userId;
    }
}
