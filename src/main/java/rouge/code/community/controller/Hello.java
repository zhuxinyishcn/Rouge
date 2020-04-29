package rouge.code.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Xinyi Zhu
 * @program: Rouge
 * @date 4/29/2020 3:48 PM
 * @email: zhuxinyishcn@outlook.com
 * @github: https://github.com/zhuxinyishcn
 * @description: file info
 */
@Controller
public class Hello {

  @GetMapping("/hello")
  public String hello(@RequestParam(name = "name") String name, Model model) {
    model.addAttribute("name", name);
    return "hello";
  }
}
