package rouge.code.community;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.thymeleaf.util.ArrayUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rouge.code.community.mapper.UserMapper;
import rouge.code.community.model.User;

@SpringBootTest
class RougeApplicationTests {

  @Autowired
  private UserMapper userMapper;

  @Test
  void contextLoads() {
  }

  @Test
  void TestFindUserByToken() {
    User user = userMapper.findByToken("8cdbfb8e-dbef-4ec0-9d5e-f3a76b515e6f");
    int arr[] = {1, 24, 4, 5, 5, 2};
//    System.out.println(ArrayUtil.isEmpty());
    assertEquals(user.getName(), "Xinyi Zhu");
  }
}
