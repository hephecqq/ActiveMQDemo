package dagong.mybatis;

import java.util.List;

public interface IWebsiteOperation {

	public int add(Website website);
	public int delete(int id);
	public int update(Website website);
	public Website query(int id);
	public List<Website> getList();
	
	
	
}
