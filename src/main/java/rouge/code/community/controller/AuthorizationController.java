package rouge.code.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Xinyi Zhu
 * @program: Rouge
 * @date 4/30/2020 8:41 PM
 * @email: zhuxinyishcn@outlook.com
 * @github: https://github.com/zhuxinyishcn
 * @description: This class is mainly for Authorization on GitHub
 */
@Controller
public class AuthorizationController {

  @GetMapping("/callback")
  public String callback(@RequestParam(name = "code") String code,
      @RequestParam(name = "state") String state) {
    return "index";
  }
}
