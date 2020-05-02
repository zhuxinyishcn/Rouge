package rouge.code.community.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rouge.code.community.dto.AccessTokenDTO;
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

  @GetMapping("/callback")
  public String callback(@RequestParam(name = "code") String code,
      @RequestParam(name = "state") String state) throws IOException {
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setClient_id("2697703126ad99441817");
    accessTokenDTO.setClient_secret("14024413270a7b0c2159f5e57f74d735bb9f5784");
    accessTokenDTO.setCode(code);
    accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
    accessTokenDTO.setState(state);
    final String accessToken = this.gitHubProvider.getAccessToken(accessTokenDTO);
    String name = this.gitHubProvider.getUser(accessToken).getName();
    System.out.println(name);
    return "index";
  }
}
