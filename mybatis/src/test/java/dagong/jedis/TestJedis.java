package dagong.jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * Java中使用Jedis操作reids
 * 
 * @author Administrator
 *
 */
public class TestJedis {

	private Jedis jedis;

	@Before
	public void before() {
		// 连接redis服务器.localhost:6379
		jedis = new Jedis("localhost", 6379);
		// 权限认证
		// jedis.auth("admin");
	}

	// redis存储字符串
	@Test
	public void testString() {
		// 添加数据
		jedis.set("name", "hepng");
		// 向key->name中放入value-hepeng的数据
		System.out.println("name:" + jedis.get("name"));

		jedis.append("name", " my name");
		System.out.println("name append :" + jedis.get("name"));

		jedis.del("name");// 删除某个键
		System.out.println(jedis.get("name"));
		// 设置多个键值对
		jedis.mset("name", "zhangsan", "name2", "lisi", "age", "10");
		jedis.incr("age");// 进行加1操作
		System.out.println(jedis.get("name") + " age:" + jedis.get("age") + ":" + jedis.get("name2"));

	}

	/**
	 * redis操作Map
	 */
	@Test
	public void testMap() {
		// 添加数据
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "xinxin");
		map.put("age", "22");
		map.put("qq", "123456");
		jedis.hmset("key", map);
		// 取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
		// 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变
		List<String> rsmap = jedis.hmget("key", "name", "age", "qq");
		System.out.println(rsmap);

		// 删除map中的某个键值
		jedis.hdel("key", "age");
		// 返回key为user的键中存放的值的个数2
		System.out.println(jedis.hlen("key"));
		// 是否存在key为user的记录 返回true
		System.out.println(jedis.exists("key"));
		System.out.println(jedis.hkeys("key"));// 返回map对象中的所有key
		// 返回map对象中的所有value
		System.out.println(jedis.hvals("user"));// 返回map对象中的所有value
		Iterator<String> iter = jedis.hkeys("user").iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key + ":" + jedis.hmget("user", key));
		}
	}

	@Test
	public void testList() {
		//// 开始前，先移除所有的内容
		jedis.del("java framework");
		System.out.println(jedis.lrange("java framework", 0, -1));
		// 先向key java framework中存放三条数据
		jedis.lpush("java framework", "spring");
		jedis.lpush("java framework", "struts");
		jedis.lpush("java framework", "hibernate");
		// 再取出所有数据jedis.lrange是按范围取出，
		// 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
		System.out.println(jedis.lrange("java framework", 0, -1));
		jedis.del("java framework");
		jedis.rpush("java framework", "spring");
		jedis.rpush("java framework", "struts");
		jedis.rpush("java framework", "hibernate");
		System.out.println(jedis.lrange("java framework", 0, -1));

	}

	
	
}
