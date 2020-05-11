package rouge.code.community.controller;

import java.util.Optional;
import java.util.stream.IntStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import rouge.code.community.mapper.UserMapper;
import rouge.code.community.model.User;

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

  @Autowired
  UserMapper userMapper;

  /**
   * @description: This function will check if user has already have the login and has (token) and
   * add the user info into the cookies
   * @param: [request]
   * @return: java.lang.String
   **/
  @GetMapping("/")
  public String indexController(HttpServletRequest request) {
    final Cookie[] cookies = request.getCookies();
    //make sure cookies always is not null, otherwise throw assertion
    if (cookies != null) {
      //search in cookies to check if already has token in user
      final int tokenIndex = IntStream.range(0, cookies.length)
          .parallel()
          .filter(index -> "token".equals(cookies[index].getName()))
          .findFirst()
          //if no token found, return -1 as a flag
          .orElse(-1);
      if (tokenIndex == -1) {
        //else means token in the cookie, so user has already login before
      } else {
        Optional<User> user = Optional
            .ofNullable(userMapper.findByToken(cookies[tokenIndex].getValue()));
        user.ifPresent(userInfo -> request.getSession().setAttribute("userInfo", userInfo));
      }
    }
    return "index";
  }

}
