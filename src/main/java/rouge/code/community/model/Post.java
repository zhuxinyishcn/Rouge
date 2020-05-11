package rouge.code.community.model;

import lombok.Data;

/**
 * @author Xinyi Zhu
 * @program: Rouge
 * @date 5/10/2020 4:38 PM
 * @email: zhuxinyishcn@outlook.com
 * @github: https://github.com/zhuxinyishcn
 * @description: file info
 */
@Data
public class Post {

  private int id;
  private String summary;
  private String detail;
  private long gmtCreate;
  private long gmtModified;
  private int creator;
  private long commentCount;
  private long likeCount;
  private String tag;
}
