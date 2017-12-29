package myapplication.sch.example.com.first;

/**
 * Created by SCH on 2017/12/11.
 */

public class User {
    private int id;
    private String name;
    private String nick;
    private String mess;
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNick(){
        return this.nick;
    }
    public void setNick(String nick){
        this.nick = nick;
    }
    public String getMess(){
        return this.mess;
    }
    public void setMess(String mess){
        this.mess = mess;
    }
}
