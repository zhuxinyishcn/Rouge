package rouge.code.community.githubprovider;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Objects;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import rouge.code.community.dto.AccessTokenDTO;
import rouge.code.community.dto.GitHubUserDTO;

/**
 * @author Xinyi Zhu
 * @program: Rouge
 * @date 4/30/2020 9:00 PM
 * @email: zhuxinyishcn@outlook.com
 * @github: https://github.com/zhuxinyishcn
 * @description: OkHttp request to get accessTokenDTO and Gson parsing User Info
 */
@Component
public class GitHubProvider {

  /**
   * @description: Using a access token object send a POST Request to GitHub server and get actual
   * access token
   * @param: [accessTokenDTO]
   * @return: java.lang.String
   **/
  public String getAccessToken(AccessTokenDTO accessTokenDTO) throws IOException {
    final MediaType mediaType
        = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    RequestBody body = RequestBody.create(gson.toJson(accessTokenDTO), mediaType);
    Request request = new Request.Builder()
        .url("https://github.com/login/oauth/access_token")
        .post(body)
        .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      return Objects.requireNonNull(response.body()).string().substring(13, 53);
    }
  }

  /**
   * @description: Using access token send a GET request to GitHub server to get the user info
   * @param: [accessToken]
   * @return: rouge.code.community.dto.GitHubUserDTO
   **/
  public GitHubUserDTO getUser(String accessToken) throws IOException {
    final OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url("https://api.github.com/user?access_token=".concat(accessToken))
        .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      Gson gson = new Gson();
      //return a json formatt user info
      return gson.fromJson(Objects.requireNonNull(response.body()).string(), GitHubUserDTO.class);
    }
  }
}
