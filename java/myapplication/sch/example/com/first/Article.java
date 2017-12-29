package myapplication.sch.example.com.first;

/**
 * Created by SCH on 2017/12/25.
 */

public class Article {
    private String title;
    private String content;
    private String pub_date;
    private String update_time;
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getPub_date(){
        return this.pub_date;
    }
    public void setPub_date(String pub_date){
        this.pub_date = pub_date;
    }
    public String getUpdate_time(){
        return this.update_time;
    }
    public void setUpdate_time(String update_time){
        this.update_time = update_time;
    }
}
