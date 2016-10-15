package dagong.mybatis;

import dagong.jms.PersonService;

/**
 * 
 * @author Administrator
 *
 */
class User implements Runnable{
    private PersonService ps;
    private String name;
    public User(PersonService ps, String name) {
        super();
        this.ps = ps;
        this.name = name;
    }

    @Override
    public void run() {
        for( int i=0;i<10;i++){
            ps.sendMessage(name+"向你问好");
        }

    }
}