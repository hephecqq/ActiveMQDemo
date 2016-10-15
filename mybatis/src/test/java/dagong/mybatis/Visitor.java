package dagong.mybatis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实体类
 * @author Administrator
 *
 */
public class Visitor {
    private int id;
    private String name;
    private String email;
    private int status;
    private Date createTime;

    public Visitor() {
        // TODO Auto-generated constructor stub
        createTime = new Date();
    }

    public Visitor(String name, String email) {
        this.name = name;
        this.email = email;
        this.status = 1;
        this.createTime = new Date();
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("{Id: %d, Name: %s, CreateTime: %s}", id, name,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime));
    }
}