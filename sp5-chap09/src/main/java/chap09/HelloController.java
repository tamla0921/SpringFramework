package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Controller Spring MVC에서 컨트롤러로 사용
 * @author seo
 *
 */
@Controller
public class HelloController {

    /**
     * 메서드가 처리할 요청 경로 지정.
     * @param model 컨트롤러의 처리 결과를 뷰에 전달할 때 사용.
     * @annotation RequestParam HTTP 요청 파라미터의 값을 메서드의 파라미터로 전달할 때 사용.
     * @param name name 요청 파라미터의 값을 name 파라미터에 전달.
     * @return 컨트롤러의 처리 결과를 보여줄 view 이름으로 "hello" 사용.
     */
    @GetMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false) String name) {
        model.addAttribute("greeting", "안녕하세요," + name);
        // "greeting"이라는 모델 속성에 값을 설정.
        // hello.JSP 코드에서 이 속서을 이용해 값을 출력.
        
        return "hello";
    }
}
