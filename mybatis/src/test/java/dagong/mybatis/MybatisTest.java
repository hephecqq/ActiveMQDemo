package dagong.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MybatisTest {

	@Test
	public void testConn() {
		SqlSession session = MybatisUtils.getSession();
		session.commit();
	}

	@Test
	public void testBasicQuery() {
		SqlSession session = MybatisUtils.getSession();
		try {
			Visitor visitor = (Visitor) session.selectOne("dagong.mybatis.basicQuery",1);
			MybatisUtils.closeSession();
			System.out.println(visitor);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
