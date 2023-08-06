package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * webMvcConfigurer Spring MVC의 개별 설정을 조정
 * configureDefaultServletHandling() 메서드: 디폴트 Servlet과 관련된 설정을 조정.
 * configureViewResolvers() 메서드: ViewResolver와 관련된 설정을 조정.
 * @author seo
 *
 */
@Configuration
@EnableWebMvc // Spring MVC 활성화. MVC 패턴을 사용하기 위해 많은 코드를 작성하는 대신, 내부적으로 다양한 Bean 설정을 추가해줌.
public class MvcConfig implements WebMvcConfigurer {

    /**
     * DispatcherServlet의 매핑 경로를 '/'로 주었을 때, JSP/HTML/CSS 등을 올바르게 처리하기 위한 설정을 추가.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    /**
     * JSP를 이용해서 컨트롤러의 실행 결과를 보여주기 위한 설정을 추가.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }
} // 클래스 닫음.
