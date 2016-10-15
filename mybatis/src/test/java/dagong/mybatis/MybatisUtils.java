package dagong.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public final class MybatisUtils {

	 private static final String CONFIG_FILE = "mybatis-config.xml";
	// private static final Logger logger = LoggerFactory.getLogger(MybatisUtils.class);
	 private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	 private static TransactionFactory transactionFactory = new JdbcTransactionFactory();
	 private static SqlSessionFactory sqlSessionFactory = null;
	// private static final String MYBATIS_ENVIRONMENT = ApplicationPropUtil.getValue("mybatis_environment");
	 
	 private MybatisUtils(){
	  
	 }
	 
	 static {
	  buildSessionFactory();
	 }
	 
	 /**
	  * 获取session
	  * @return SqlSession
	  */
	 public static SqlSession getSession(){
	  SqlSession session = threadLocal.get();
	  if (session == null){
	   if(sqlSessionFactory == null){
	    buildSessionFactory();
	      }
	   session = sqlSessionFactory != null ? sqlSessionFactory.openSession() : null;
	   threadLocal.set(session);
	  }
	   
	  return session;
	 }
	 
	 /**
	  * 关闭session
	  */
	 public static void closeSession(){
	  SqlSession session = threadLocal.get();
	  if (session != null){
	   session.close();
	   threadLocal.set(null);
	  }
	 }
	 
	 public static TransactionFactory getTransactionFactory(){
	  return transactionFactory;
	 }
	 
	 public static SqlSessionFactory getSessionFactory(){
	  if (sqlSessionFactory == null){
	   buildSessionFactory();
	  }
	  return sqlSessionFactory;
	 }
	 
	 
	 private static void buildSessionFactory(){
	  if (sqlSessionFactory == null){
	   InputStream inputStream = null;
	   try {
	    inputStream = Resources.getResourceAsStream(CONFIG_FILE);
	    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//	    addMapper(sqlSessionFactory);
	   } catch (IOException e) {
	   // logger.error("读取mybatis-config.xml文件时出现异常，异常信息：" + e.getMessage());
	    e.printStackTrace();
	   } catch (Exception e){
	    //logger.error("创建SessionFactory失败，异常信息：" + e.getMessage());
	    e.printStackTrace();
	   } finally{
	    if (inputStream != null){
	     try {
	      inputStream.close();
	     } catch (IOException e) {
	      e.printStackTrace();
	     }
	     inputStream = null;
	    }
	   }
	  }
	 }
	 
	// private static void addMapper(SqlSessionFactory sqlSessionFactory){
	//  if (sqlSessionFactory != null){
	//   sqlSessionFactory.getConfiguration().addMapper(MerchantInfoContentDao.class);
	//  }
	// }
	 
	}


