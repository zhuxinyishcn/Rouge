package rouge.code.community.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rouge.code.community.mapper.PostMapper;
import rouge.code.community.model.Post;

/**
 * @author Xinyi Zhu
 * @program: Rouge
 * @date 5/10/2020 4:51 PM
 * @email: zhuxinyishcn@outlook.com
 * @github: https://github.com/zhuxinyishcn
 * @description: file info
 */
@Controller
public class PublishController {

  @Autowired
  private PostMapper postMapper;

  @GetMapping("/publish")
  public String publish() {
    return "publish";
  }

  @PostMapping("/publish")
  public String doPublish(
      @RequestParam("summary") String summary,
      @RequestParam("detail") String detail,
      @RequestParam("tag") String tag,
      @CookieValue(value = "token",defaultValue = "")String token) {
    Post post= new Post();
    post.setSummary(summary);
    post.setDetail(detail);
    post.setTag(tag);

    return "publish";
  }
}
