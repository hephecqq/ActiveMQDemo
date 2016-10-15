package dagong.mybatis;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Website {
    private int id;
    private String name;
    private int visitorId;
    private int status;
    private Date createTime;
    private Visitor visitor;//Visitor类型

    public Website() {
        // TODO Auto-generated constructor stub
        createTime = new Date();
        visitor = new Visitor();
    }

    public Website(String name, int visitorId) {
        this.name = name;
        this.visitorId = visitorId;
        visitor = new Visitor();
        status = 1;
        createTime = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getVisitorId() {
        int id = 0;
        if (visitor == null)
            id = visitorId;
        else
            id = visitor.getId();
        return id;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("Website=> {Id：%d, Name：%s, CreateTime：%s}\r\n", id, name,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime)));
        if (visitor != null)
            sb.append(String.format("Visitor=> %s", visitor.toString()));
        return sb.toString();
    }
}
