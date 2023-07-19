package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    // 뷰 템플릿으로 매핑과 model(데이터) 정보를 넘겨주는 첫 단추
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello");

        return mav;
    }

    // ModelAndView 사용하지 않고
    // 반환 타입 String(논리경로명)
    // return "response/hello
    // 매개변수 Model model 을 사용하여
    // model.addAttribute("data", "hello");
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {

        model.addAttribute("data", "hello!");

        return "response/hello";
    }

    // 권장하지 않는 방법
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {

        model.addAttribute("data", "hello!");

    }
}
