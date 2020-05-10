package rouge.code.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import rouge.code.community.model.User;

/**
 * @author Xinyi Zhu
 * @program: Rouge
 * @date 5/3/2020 5:42 PM
 * @email: zhuxinyishcn@outlook.com
 * @github: https://github.com/zhuxinyishcn
 * @description: This a a user mapper class to map the java object to database
 */
@Mapper
public interface UserMapper {

  /**
   * @description: Insert a User to database
   * @param: [user]
   * @return: void
   **/
  @Insert("insert into USER(name, account_id, token, gmt_create, gmt_modified) VALUES (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
  void insertUser(User user);

  /**
   * @description: search a user in database by token
   * @param: [token]
   * @return: rouge.code.community.model.User
   **/
  @Select("select * from user where token = #{token}")
  User findByToken(@Param("token") String token);
}
