package rouge.code.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import rouge.code.community.model.Post;

/**
 * @author Xinyi Zhu
 * @program: Rouge
 * @date 5/10/2020 4:30 PM
 * @email: zhuxinyishcn@outlook.com
 * @github: https://github.com/zhuxinyishcn
 * @description: file info
 */
@Mapper
public interface PostMapper {

  @Insert(
      "insert into USER(summary, detail, gmt_create, gmt_modified,creator,comment_count,like_count,tag) VALUES (#{summary}, #{detail}, #{gmtCreate}, #{gmtModified}, #{creator}, #{comment_count}, #{like_count}, #{tag}")
   void insertPost(Post post);
}
