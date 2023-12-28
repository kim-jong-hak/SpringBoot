package web_run.hellospring.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // @이걸 어노테이션이라고 부른다 자세한건 chatgpt
public class helloController {




    @GetMapping("hello")  //헨들러 get방식으로 정보를 얻는다.
         // 텐플릿 엔진을 활용함.
    public String hello(Model model)
    {
    model.addAttribute("data1","spring");
    return "hello";
    }

    @GetMapping("hello-mvc") //mvc방식
    //http://localhost:8080/hello-mvc?name=무야호
    // 이렇게 url를 작성하면 ?는 파라미터를 받는 뜻이기 때문에 name에 무야호가 입력된다.
    // RequestParam는 어떤 방식으로 동작하는지 모르니까 조금 더 알아봐야 할거 같다.
    public String helloMvc(@RequestParam("name")String name, Model model)
    {
        model.addAttribute("name",name);
       return "hello-template";
    }

    @GetMapping("hello-string") //이것도 api방식에 일종이다.
    @ResponseBody
    public String helloString(@RequestParam("name")String name) {
        return "hello "+name;
    }

    @GetMapping("hello-api") // api 방식 <- 일방적으로 이걸 생각한다.
    @ResponseBody
    public hello helloApi(@RequestParam("name")String name) {
        hello hello=new hello();
        hello.setName(name);
        return hello;
        // 결과: {"name":"{입력된값}"} <- JSON 방식이다.// 요즘은 이러한 json방식이다.
    }

        static class hello{  // 프로퍼티 접근 방식이라고 하기도 한다.
            private String name;
            public String getName()
            {
                return name;
            }
            public void setName(String name)
            {
                this.name=name;
            }
        }



}
