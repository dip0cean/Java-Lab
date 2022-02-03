package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class HandlerMethodController {

    /**
     * {@RequestMapping 별도로 HTTP Method 를 지정하지 않으면 GET 요청으로 처리한다.}
     *
     * @return "get!"
     */
    @RequestMapping(method = RequestMethod.GET, value = "/requestMapping")
    @ResponseBody
    public String requestMapping() {
        return "requestMapping!";
    }

    /**
     * 하나의 URI 여러 HTTP Method 를 지정하고 싶다면, 배열을 이용하면 된다.
     *
     * @param word 단어
     * @return word
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = "/requestMapping")
    public String requestMapping(@RequestParam("word") String word) {
        return word;
    }

    /**
     * 1. 클라이언트가 서버의 리소스를 요청할 때 사용한다.
     * 2. 조건적인 GET 으로 바뀔 수 있으며, 캐싱 처리가 가능하다.
     * 3. 브라우저 기록에 남으며, 북마크가 가능하다.
     * 4. URL 을 통해 데이터를 전달하므로 민감한 데이터를 전송할 때는 지양하는 것이 좋다.
     * 5. Idempotent
     *
     * @return "get!"
     */
    @GetMapping("/get")
    @ResponseBody
    public String get() {
        return "get!";
    }

    /**
     * 1. 클라이언트가 서버의 리소스를 수정하거나 새로 만들 때 사용한다.
     * 2. 서버에 보내는 데이터를 POST 요청 본문에 담는다. (@RequestBody 로 받는다.)
     * 3. 캐싱 처리를 할 수 없으며, 북마크 및 브라우저 기록을 저장할 수 없다.
     * 4. 데이터 길이에 제한이 없다.
     *
     * @param word 단어
     * @return word + "!!"
     */
    @PostMapping("/post")
    @ResponseBody
    public List<String> post(@RequestParam("word") String word) {
        return Collections.singletonList(word + "!!");
    }

    /**
     * 1. URI 에 해당 하는 데이터를 만들거나 수정할 때 사용한다.
     * 2. POST 와 다른 점은 URI 에 대한 의미가 다르다.
     * a. POST 의 URI 는 보내는 데이터를 처리할 리소스를 지칭한다.
     * b. PUT 의 URI 는 보내는 데이터에 해당하는 리소스를 지칭한다.
     * 3. Idempotent
     *
     * @param word 단어
     * @return word + "!!!"
     */
    @PutMapping("/put")
    @ResponseBody
    public String put(@RequestParam("word") String word) {
        return word + "!!!";
    }

    /**
     * 1. PUT 과 비슷하지만, 기존 엔티티와 새 데이터의 차이점만 보낸다는 차이를 가진다.
     * 2. Idempotent
     *
     * @param word 단어
     * @return word + "!!!!"
     */
    @PatchMapping("/patch")
    @ResponseBody
    public String patch(@RequestParam("word") String word) {
        return word + "!!!!";
    }

    /**
     * 1. URI 에 해당하는 리소스를 삭제할 때 사용한다.
     * 2. Idempotent
     *
     * @param word 단어
     * @return word + "은(는) 삭제되었습니다."
     */
    @DeleteMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("word") String word) {
        return word + "은(는) 삭제되었습니다.";
    }
}
