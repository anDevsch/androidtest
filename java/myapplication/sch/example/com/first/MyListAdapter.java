package myapplication.sch.example.com.first;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
public class MyListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    List<User> lUsers;
    public MyListAdapter(Context context,List<User> user)
    {
        mInflater = LayoutInflater.from(context);
        lUsers = user;

    }
    @Override
    public int getCount()
    {
        return lUsers.size();
    }
    @Override
    public Object getItem(int position)
    {
        return lUsers.get(position);
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolder holder = null;
        User user = (User) lUsers.get(position);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.name = (TextView)convertView.findViewById(R.id.title);
            holder.nick = (TextView)convertView.findViewById(R.id.time);
            holder.mess = (TextView)convertView.findViewById(R.id.mess);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText((String) user.getName());
        holder.nick.setText((String) user.getNick());
        holder.mess.setText((String) user.getMess());
        return convertView;

    }
    private static class ViewHolder
    {
        TextView name;
        TextView nick;
        TextView mess;
    }
}