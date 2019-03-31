package com.example.myapplicationexamplepost;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MessageDataShowadapter extends RecyclerView.Adapter<MessageDataShowadapter.Viewholder> {
    public Context context;
     ArrayList<Messagesetgetpojo>messagesetgetpojos;

     public  MessageDataShowadapter(Context context, ArrayList<Messagesetgetpojo>messagesetgetpojos)
     {
         this.context=context;
         this.messagesetgetpojos=messagesetgetpojos;
     }

    @Override
    public MessageDataShowadapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(context).inflate(R.layout.adaptershowinfo,parent,false));
    }

    @Override
    public void onBindViewHolder(MessageDataShowadapter.Viewholder holder, int position) {


        Picasso.get().load(messagesetgetpojos.get(position).getProject_image()).into(holder.imv_msgimage);
        holder.tv_projectname .setText(messagesetgetpojos.get(position).project_name);
        holder.tv_parentcatagoryname.setText(messagesetgetpojos.get(position).property_type_name);
        holder.tv_category_name.setText(messagesetgetpojos.get(position).category_name);

    }

    @Override
    public int getItemCount() {
        return messagesetgetpojos.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imv_msgimage;
        TextView tv_projectname;
        TextView tv_parentcatagoryname;
        TextView tv_category_name;
        public Viewholder(View itemView) {
            super(itemView);
            imv_msgimage=(ImageView)itemView.findViewById(R.id.imv_msgimage);
            tv_projectname=(TextView)itemView.findViewById(R.id.tv_projectname);
            tv_parentcatagoryname=(TextView)itemView.findViewById(R.id.tv_parentcatagoryname);
            tv_category_name=(TextView)itemView.findViewById(R.id.tv_category_name);
        }
    }
}
