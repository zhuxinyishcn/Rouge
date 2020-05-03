package rouge.code.community.controller;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rouge.code.community.dto.AccessTokenDTO;
import rouge.code.community.dto.GitHubUserDTO;
import rouge.code.community.githubprovider.GitHubProvider;

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

  @Value("${github.client.id}")
  private String client_id;
  @Value("${github.client.secret}")
  private String client_secret;
  @Value("${github.redirect.uri}")
  private String redirect_uri;

  @GetMapping("/callback")
  public String callback(
      @RequestParam(name = "code") String code,
      @RequestParam(name = "state") String state,
      HttpServletRequest request) throws IOException {
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setClient_id(this.client_id);
    accessTokenDTO.setClient_secret(this.client_secret);
    accessTokenDTO.setCode(code);
    accessTokenDTO.setRedirect_uri(this.redirect_uri);
    accessTokenDTO.setState(state);
    final String accessToken = this.gitHubProvider.getAccessToken(accessTokenDTO);
    Optional<GitHubUserDTO> user = Optional.ofNullable(this.gitHubProvider.getUser(accessToken));
    user.ifPresent(userInfo ->
        request.getSession().setAttribute("userInfo", userInfo));
    return user.isPresent() ? "redirect:/" : "index";
  }
}
