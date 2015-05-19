package com.example.tariqul.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tariqul on 5/16/2015.
 */
public class CustomListAdapter extends ArrayAdapter<Employee> {

    LayoutInflater inflater;

    public CustomListAdapter(Context context, List<Employee> employeesObj) {
        super(context, 0, employeesObj);

        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder holder;

        if (convertView == null){
            convertView= inflater.inflate(R.layout.custom_list_view, null);
            holder= new ViewHolder();
            holder.tvName=(TextView) convertView.findViewById(R.id.tvNameCustomList);
            holder.tvPhone=(TextView)convertView.findViewById(R.id.tvPhoneCustomList);

            convertView.setTag(holder);

        }else {
            holder=(ViewHolder)convertView.getTag();
        }

        Employee employee= getItem(position);

        holder.tvName.setText(employee.getName());
        holder.tvPhone.setText(employee.getPhone());


        return convertView;
    }

    static class ViewHolder {

        public ImageView imageView;
        public TextView tvName;
        public TextView tvPhone;
    }
}
