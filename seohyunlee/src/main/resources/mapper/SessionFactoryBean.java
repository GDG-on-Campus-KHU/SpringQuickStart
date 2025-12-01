package mapper;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionFactoryBean {

  private static SqlSessionFactory session;

  static {
    try {
      String source = "sql-map-config.xml";
      InputStream input = Resources.getResourceAsStream(source);

      session = new SqlSessionFactoryBuilder().build(input);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public static SqlSession getSqlSession() {
    return session.openSession();
  }
}
