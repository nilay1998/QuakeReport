package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class itemsAdapter extends ArrayAdapter<Earthquake>
{
    public itemsAdapter(Activity context, ArrayList<Earthquake> item)
    {
        super(context,0,item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Earthquake item=getItem(position);
        if(convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.items_listview, parent, false);

        TextView magn=(TextView)convertView.findViewById(R.id.mag);
        TextView near=(TextView)convertView.findViewById(R.id.near);
        TextView place=(TextView)convertView.findViewById(R.id.place);
        TextView time=(TextView)convertView.findViewById(R.id.time);
        TextView date=(TextView)convertView.findViewById(R.id.date);
        int colorId=getMagnitudeColor(Double.parseDouble(item.getmMag()));


        DecimalFormat decimalFormat=new DecimalFormat("0.0");
        magn.setText(decimalFormat.format(Double.parseDouble(item.getmMag())));
        GradientDrawable gradientDrawable=(GradientDrawable)magn.getBackground();
        gradientDrawable.setColor(colorId);

        time.setText(date(item.getmDate()));
        date.setText(time(item.getmDate()));

        String area=item.getmPlace();
        if(area.contains(" of "))
        {
            near.setText(area.substring(0,area.indexOf(" of ")+3));
            place.setText(area.substring(area.indexOf(" of ")+3));
        }
        else
        {
            near.setText("Near");
            place.setText(area);
        }

        return convertView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    String date(String milliseconds)
    {
        long time=Long.parseLong(milliseconds);
        Date dateObject=new Date(time);
        SimpleDateFormat dateFormat=new SimpleDateFormat("MMM DD,yyyy");
        return dateFormat.format(dateObject);
    }

    String time(String milliseconds)
    {
        long time=Long.parseLong(milliseconds);
        Date dateObject=new Date(time);
        SimpleDateFormat dateFormat=new SimpleDateFormat("h:mm a");
        return dateFormat.format(dateObject);
    }
}
