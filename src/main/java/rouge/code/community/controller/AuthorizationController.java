package rouge.code.community.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rouge.code.community.dto.AccessTokenDTO;
import rouge.code.community.dto.GitHubUserDTO;
import rouge.code.community.githubprovider.GitHubProvider;
import rouge.code.community.mapper.UserMapper;
import rouge.code.community.model.User;

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

  @Autowired(required = false)
  private GitHubProvider gitHubProvider;
  @Autowired(required = false)
  private UserMapper userMapper;

  @Value("${github.client.id}")
  private String clientId;
  @Value("${github.client.secret}")
  private String clientSecret;
  @Value("${github.redirect.uri}")
  private String redirectUri;

  @GetMapping("/callback")
  public String callback(
      @RequestParam(name = "code") String code,
      @RequestParam(name = "state") String state,
      HttpServletRequest request,
      HttpServletResponse repsond) throws IOException {
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setClient_id(this.clientId);
    accessTokenDTO.setClient_secret(this.clientSecret);
    accessTokenDTO.setCode(code);
    accessTokenDTO.setRedirect_uri(this.redirectUri);
    accessTokenDTO.setState(state);
    final String accessToken = this.gitHubProvider.getAccessToken(accessTokenDTO);
    Optional<GitHubUserDTO> githubUser = Optional
        .ofNullable(this.gitHubProvider.getUser(accessToken));
    githubUser.ifPresent(userInfo -> {
      final User user = new User();
      user.setName(userInfo.getName());
      user.setAccountId(Long.toString(userInfo.getId()));
      user.setToken(UUID.randomUUID().toString());
      user.setGmtCreate(System.currentTimeMillis());
      user.setGmtModified(user.getGmtCreate());
      userMapper.insertUser(user);
      repsond.addCookie(new Cookie("token", user.getToken()));
    });
    return githubUser.isPresent() ? "redirect:/" : "index";
  }
}
