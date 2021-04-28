package course.springdata.restmvc.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", required = false,defaultValue = "stranger") String name){
        return String.format("Hello %s, from Spring MVC!",name);
    }

    @GetMapping("/users/{userId}/post/{postID}")
    public String getPost(@PathVariable Integer userId, @PathVariable Integer postID){
        return String.format("User: %d --> Post: %d%n",userId,postID);
    }


}
