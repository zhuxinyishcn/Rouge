package rouge.code.community.dto;

import lombok.Data;

/**
 * @author Xinyi Zhu
 * @program: Rouge
 * @date 4/30/2020 9:09 PM
 * @email: zhuxinyishcn@outlook.com
 * @github: https://github.com/zhuxinyishcn
 * @description: An object for GitHub Authorization
 */
@Data
public class AccessTokenDTO {

  String client_id;
  String client_secret;
  String code;
  String redirect_uri;
  String state;

}
