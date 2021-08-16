package rouge.code.community.controller;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rouge.code.community.mapper.PostMapper;
import rouge.code.community.mapper.UserMapper;
import rouge.code.community.model.Post;
import rouge.code.community.model.User;

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
  @Autowired
  private UserMapper userMapper;

  @GetMapping("/publish")
  public String publish() {
    return "publish";
  }

  @PostMapping("/publish")
  public String doPublish(
      @RequestParam(value = "summary", required = false) String summary,
      @RequestParam(value = "detail", required = false) String detail,
      @RequestParam(value = "tag", required = false) String tag,
      @CookieValue(value = "token", defaultValue = "") String token,
      Model model, HttpServletRequest request) {
    //add user input store into model for future user
    model.addAttribute("summary", summary);
    model.addAttribute("detail", detail);
    model.addAttribute("tag", tag);
    //check if user have valid summary, detail, tags, otherwise return to publish page
    if (summary == null || summary.isEmpty()) {
      model.addAttribute("error", "Your summary can not be empty");
      return "publish";
    }
    if (detail == null || detail.isEmpty()) {
      model.addAttribute("error", "Your detail can not be empty");
      return "publish";
    }
    if (tag == null || tag.isEmpty()) {
      model.addAttribute("error", "Your tag can not be empty");
      return "publish";
    }
    Optional<User> user = Optional
        .ofNullable(userMapper.findByToken(token));
    //check if user login, otherwise return back to publish page
    if (!user.isPresent()) {
      return "publish";
    }
    Post post = new Post();
    post.setSummary(summary);
    post.setDetail(detail);
    post.setCreator(user.get().getId());
    post.setTag(tag);
    post.setGmtCreate(System.currentTimeMillis());
    post.setGmtModified(post.getGmtCreate());
    postMapper.insertPost(post);
    return "publish";
  }
}
