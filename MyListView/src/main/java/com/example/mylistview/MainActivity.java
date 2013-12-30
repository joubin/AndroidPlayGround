package com.example.mylistview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends Activity {

    private List<Car> myCars = new ArrayList<Car>();
    private NotificationManager notificationManager;
    private static final String YES_ACTION = "android.intent.action.YES_ACTION";
    private static final String NO_ACTION = "android.intent.action.NO_ACTION";
    private static final String MAYBE_ACTION = "android.intent.action.MAYBE_ACTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateCars();
        populateListView();
        registerClickCallBack();
        notificationManager = (NotificationManager)
        getSystemService(NOTIFICATION_SERVICE);
        IntentFilter inf = new IntentFilter();
        NotificationReceiverActivity nm = new NotificationReceiverActivity();

        inf.addAction(YES_ACTION);
        inf.addAction(NO_ACTION);
        inf.addAction(MAYBE_ACTION);
        registerReceiver(nm, inf);
    }

    private void registerClickCallBack() {
        ListView listView = (ListView) findViewById(R.id.carsListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Car clickedCar = myCars.get(position);
//                String message = "Your password \""+clickedCar.getMake() + "\" has been copied to your clipboard ";
//                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
//
//                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//                ClipData clip = ClipData.newPlainText("label", clickedCar.getMake().toString());
//                clipboard.setPrimaryClip(clip);
//                clipboard.setText(clickedCar.getMake().toString());
//                Intent intent = new Intent(getApplicationContext(), view_selected_item.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("make", clickedCar.getMake());
//                bundle.putInt("year", clickedCar.getYear());
//                bundle.putString("condition", clickedCar.getCondition());
//                bundle.putInt("icon", clickedCar.getIconID());
//                intent.putExtras(bundle);
//
//
//                startActivityForResult(intent, 0);



//                Intent intent = new Intent(getApplicationContext(), NotificationReceiverActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("make", clickedCar.getMake());
//                bundle.putInt("year", clickedCar.getYear());
//                bundle.putString("condition", clickedCar.getCondition());
//                bundle.putInt("icon", clickedCar.getIconID());
//                intent.putExtras(bundle);
//
//                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);


                //Yes intent
//                Intent yesReceive = new Intent(MainActivity.this, NotificationReceiverActivity.class);
//                PendingIntent pendingIntentYes = PendingIntent.getActivity(MainActivity.this, 0, yesReceive, 0);

                //Yes intent
                Intent yesReceive = new Intent();
                Bundle yesBundle = new Bundle();
                yesBundle.putString("1",clickedCar.getMake());
                yesReceive.putExtras(yesBundle);
                yesReceive.setAction(YES_ACTION);
                PendingIntent pendingIntentYes = PendingIntent.getBroadcast(MainActivity.this, 12345, yesReceive, PendingIntent.FLAG_UPDATE_CURRENT);
//                mBuilder.addAction(R.drawable.calendar_v, "Yes", pendingIntentYes);

//Maybe intent
                Intent maybeReceive = new Intent();
                Bundle maybeBundle = new Bundle();
                maybeBundle.putString("2",clickedCar.getCondition());
                maybeReceive.putExtras(maybeBundle);
                maybeReceive.setAction(MAYBE_ACTION);
                PendingIntent pendingIntentMaybe = PendingIntent.getBroadcast(MainActivity.this, 12345, maybeReceive, PendingIntent.FLAG_UPDATE_CURRENT);
//                mBuilder.addAction(R.drawable.calendar_question, "Partly", pendingIntentMaybe);

//No intent
                Intent noReceive = new Intent();
                Bundle noBundle = new Bundle();
                noBundle.putString("3","true");
                noReceive.putExtras(noBundle);
                noReceive.setAction(NO_ACTION);
                PendingIntent pendingIntentNo = PendingIntent.getBroadcast(MainActivity.this, 12345, noReceive, PendingIntent.FLAG_UPDATE_CURRENT);
//                mBuilder.addAction(R.drawable.calendar_x, "No", pendingIntentNo);


// build notification
// the addAction re-use the same intent to keep the example short

                Notification n  = new Notification.Builder(getApplicationContext())
                        .setContentTitle("New mail from " + "test@gmail.com")
                        .setContentText("Subject")
                        .setSmallIcon(R.drawable.bullets)

                        .addAction(R.drawable.user, "user", pendingIntentYes)
                        .addAction(R.drawable.star3, "pass", pendingIntentNo)
                        .addAction(R.drawable.history, "clear", pendingIntentMaybe)
                        .build();


                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                notificationManager.notify(12345, n);



            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void populateListView() {
        ArrayAdapter<Car> adapter = new MyListAdapter<Car>();
        ListView listView = (ListView) findViewById(R.id.carsListView);
        listView.setAdapter(adapter);
        listView.setFastScrollAlwaysVisible(true);
        listView.setFooterDividersEnabled(true);

    }

    private void populateCars() {
        myCars.add(new Car("Twitter", "joubin.j@gmail.com", 1999, R.drawable.bullets));
        myCars.add(new Car("Subaru", "Very Good", 2010, R.drawable.telescope));
        myCars.add(new Car("Toyota", "Good", 1999, R.drawable.camera));
        myCars.add(new Car("Honda", "Good", 1999, R.drawable.m1911));
        myCars.add(new Car("Ford", "Good", 1999, R.drawable.bullets));
        myCars.add(new Car("Subaru", "Very Good", 2010, R.drawable.telescope));

        Collections.sort(myCars, new Comparator<Car>() {
            @Override
            public int compare(Car car, Car car2) {
                return car.getMake().compareTo(car2.getMake());
            }
        });
    }


    private class MyListAdapter<T> extends ArrayAdapter<Car> {

        public MyListAdapter() {

            super(MainActivity.this, R.layout.item_view, myCars);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure the view we got is not null
            View itemView = convertView;
            if (itemView == null)
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);

            Car currentCar = myCars.get(position);

            ImageView icon = (ImageView) itemView.findViewById(R.id.icon);
            TextView make = (TextView) itemView.findViewById(R.id.make);
            TextView condition = (TextView) itemView.findViewById(R.id.condition);
            TextView year = (TextView) itemView.findViewById(R.id.year);

            icon.setImageResource(currentCar.getIconID());
            make.setText(currentCar.getMake().toString());
            year.setText(currentCar.getYear()+"");
            condition.setText(currentCar.getCondition().toString());

            return itemView;

        }
    }

    private class AlarmReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(YES_ACTION.equals(action)) {
                Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_LONG).show();
            } else if(MAYBE_ACTION.equals(action)) {
                Toast.makeText(MainActivity.this, "no", Toast.LENGTH_LONG).show();
            } else if(NO_ACTION.equals(action)) {
                Toast.makeText(MainActivity.this, "other", Toast.LENGTH_LONG).show();
            }
        }
    }
}
