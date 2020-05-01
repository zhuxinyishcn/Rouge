package rouge.code.community.githubProvider;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rouge.code.community.dto.AccessTokenDTO;

/**
 * @author Xinyi Zhu
 * @program: Rouge
 * @date 4/30/2020 9:00 PM
 * @email: zhuxinyishcn@outlook.com
 * @github: https://github.com/zhuxinyishcn
 * @description: file info
 */
public class GitHubProvider {

  public String getAccessToken(AccessTokenDTO accessTokenDTO) {
    final MediaType JSON
        = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    RequestBody body = RequestBody.create(json, JSON);
    Request request = new Request.Builder()
        .url(url)
        .post(body)
        .build();
    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }
}
