package hello.springmvc.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    // 모든 HTTP Method를 허용한다.
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    // 특정 HTTP Method만 허용한다.
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    // 축약 Annotation이다.
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    // PathVariable 사용한다.
    // URL 자체에 변수가 들어가있다. 이것을 가지고 사용이 가능하다.
    // userId를 PathVariable로 받는 것이다.
    // @PathVariable("userId") String userid -> @PathVariable userId
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    // PathVariable 다중 사용
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    // 파라미터로 추가 매핑
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    // 특정 헤더로 추가 매핑
    @GetMapping(value = "/mapping-header", headers = "mode-debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    // Content-Type 헤더 기반 추가 매핑
    @GetMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    // Accept 헤더 기반 추가 매핑
    @PostMapping(value = "/mapping-produces", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
