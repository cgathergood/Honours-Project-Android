package com.example.calum.honoursproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.Date;
import java.util.List;

/**
 * Created by Calum on 01/10/2015.
 */
public class ListViewAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ParseObject> postList;

    public ListViewAdapter(Activity activity, List<ParseObject> postList) {
        this.activity = activity;
        this.postList = postList;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int location) {
        return postList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);


        TextView serial = (TextView) convertView.findViewById(R.id.serial);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        final ImageView imageView = (ImageView) convertView.findViewById(R.id.postedImage);

        serial.setText(postList.get(position).getString("user"));
        title.setText(postList.get(position).getString("platform"));

        Date createdDate = postList.get(position).getCreatedAt();
        time.setText(createdDate.toString());

        ParseFile image = (ParseFile) postList.get(position).get("image");
        image.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageView.setImageBitmap(bmp);
            }
        });

        return convertView;
    }

}
