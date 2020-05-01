package rouge.code.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Xinyi Zhu
 * @program: Rouge
 * @date 4/29/2020 3:48 PM
 * @email: zhuxinyishcn@outlook.com
 * @github: https://github.com/zhuxinyishcn
 * @description: file info
 */
@Controller
public class IndexController {

  @GetMapping("/")
  public String indexController() {
    return "index";
  }
}
