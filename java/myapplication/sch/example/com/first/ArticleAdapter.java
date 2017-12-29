package myapplication.sch.example.com.first;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
public class ArticleAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    List<Article> articles;
    public ArticleAdapter(Context context,List<Article> arti)
    {
        mInflater = LayoutInflater.from(context);
        articles = arti;

    }
    @Override
    public int getCount()
    {
        return articles.size();
    }
    @Override
    public Object getItem(int position)
    {
        return articles.get(position);
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolder holder = null;
        Article article = (Article) articles.get(position);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.title = (TextView)convertView.findViewById(R.id.title);
            holder.content = (TextView)convertView.findViewById(R.id.mess);
            holder.pub_date = (TextView)convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        switch(position%9){
            case 0:
                ((ImageView)convertView.findViewById(R.id.iii)).setImageResource(R.drawable.imgh);
                break;
            case 1:
                ((ImageView)convertView.findViewById(R.id.iii)).setImageResource(R.drawable.imgh1);
                break;
            case 2:
                ((ImageView)convertView.findViewById(R.id.iii)).setImageResource(R.drawable.imgh2);
                break;
            case 3:
                ((ImageView)convertView.findViewById(R.id.iii)).setImageResource(R.drawable.imgh3);
                break;
            case 4:
                ((ImageView)convertView.findViewById(R.id.iii)).setImageResource(R.drawable.img4);
                break;
            case 5:
                ((ImageView)convertView.findViewById(R.id.iii)).setImageResource(R.drawable.imgh5);
                break;
            case 6:
                ((ImageView)convertView.findViewById(R.id.iii)).setImageResource(R.drawable.imgh6);
                break;
            case 7:
                ((ImageView)convertView.findViewById(R.id.iii)).setImageResource(R.drawable.imgh7);
                break;
            case 8:
                ((ImageView)convertView.findViewById(R.id.iii)).setImageResource(R.drawable.imgh8);
                break;
        }
        holder.title.setText((String) article.getTitle());
        holder.content.setText((String) article.getContent());
        holder.pub_date.setText((String) article.getPub_date());

        return convertView;

    }
    private static class ViewHolder
    {
        TextView title;
        TextView content;
        TextView pub_date;
    }
}