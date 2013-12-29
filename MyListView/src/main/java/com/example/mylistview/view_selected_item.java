package com.example.mylistview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class view_selected_item extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_selected_item);
        Bundle bundle = this.getIntent().getExtras();
        TextView make = (TextView) findViewById(R.id.make);
        TextView year = (TextView) findViewById(R.id.year);
        TextView condition = (TextView) findViewById(R.id.condition);
        ImageView iv = (ImageView) findViewById(R.id.icon);
        make.setText(bundle.getString("make"));
        year.setText(bundle.getInt("year")+"");
        condition.setText(bundle.getString("condition"));
        iv.setImageResource(bundle.getInt("icon"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
