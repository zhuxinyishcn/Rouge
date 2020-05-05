package rouge.code.community.model;

import lombok.Data;

/**
 * @author Xinyi Zhu
 * @program: Rouge
 * @date 5/3/2020 5:47 PM
 * @email: zhuxinyishcn@outlook.com
 * @github: https://github.com/zhuxinyishcn
 * @description: A class contain user information on database
 */
@Data
public class User {

  private int id;
  private String name;
  private String accountId;
  private String token;
  private long gmtCreate;
  private long gmtModified;
}
