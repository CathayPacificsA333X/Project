package com.test.administrator.birthday;
import android.telephony.SmsManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity implements OnScrollListener,
        OnItemClickListener, OnItemLongClickListener {
    int cxs=1;
    private Context Contexts;
    private ListView listview;
    private SimpleAdapter simp_adapter;
    private List<Map<String, Object>> diaryList;
    private Button addDiary;
    public TextView title;
    private SearchView searchView;
    private BirthdayDBHelper DB;
    public Context cx1;
    private SQLiteDatabase dbread;
    private Button Check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                                                   //指示器，用于开机执行一次check，之后归零
        title = (TextView) findViewById(R.id.title);
        listview = (ListView) findViewById(R.id.listview);
        diaryList = new ArrayList<Map<String, Object>>();
        addDiary = (Button) findViewById(R.id.btn_add);
        Check = (Button) findViewById(R.id.btn_check);
        searchView = (SearchView) findViewById(R.id.searchView);
        Contexts = this;
        cx1 = this;



        Check.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {
                    check();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        addDiary.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Edit.ENTER_STATE = 0;
                Intent intent = new Intent(Contexts, Edit.class);
                Bundle bundle = new Bundle();
                bundle.putString("info", "");
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });
        DB = new BirthdayDBHelper(this);
        dbread = DB.getReadableDatabase();
        RefreshNotesList();

        listview.setOnItemClickListener(this);
        listview.setOnItemLongClickListener(this);
        listview.setOnScrollListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText)){
                    showList(newText);
                }else {
                    showList("");
                }
                return false;
            }
        });
    }

    //刷新
    public void RefreshNotesList() {

        int size = diaryList.size();
        if (size > 0) {
            diaryList.removeAll(diaryList);                 //先移除list
            simp_adapter.notifyDataSetChanged();            //察觉到数据发生变化时，刷新item里的数据
            listview.setAdapter(simp_adapter);             //
        }
        simp_adapter = new SimpleAdapter(this, getData(), R.layout.item,
                new String[]{"tv_content", "tv_date"}, new int[]{
                R.id.tv_content, R.id.tv_date});
        listview.setAdapter(simp_adapter);
       if(cxs==1){
           try {
               check();
               cxs=0;
               Log.d("CXSs",cxs+"");
           } catch (Exception e) {
               e.printStackTrace();
           }

       }
    }








    public void showList(String part) {

        Cursor cursor = dbread.rawQuery("select * from note where date like '%" + part + "%' and content!=''and date!='' ;", null);
        SimpleCursorAdapter adapter
                = new SimpleCursorAdapter(this, R.layout.item,cursor,
                new String[]{"content", "date"}, new int[]{
                R.id.tv_content, R.id.tv_date});
        listview.setAdapter(adapter);

    }

    private List<Map<String, Object>> getData() {

        Cursor cursor = dbread.query("note", null, "content!=\"\"", null, null,
                null, null);
//从数据库中读取数据
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("content"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tv_content", name);
            map.put("tv_date", date);
            diaryList.add(map);
        }
        cursor.close();
        return diaryList;

    }

    @Override
    public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    // 滑动listview监听事件
    @Override
    public void onScrollStateChanged(AbsListView arg0, int arg1) {
        // TODO Auto-generated method stub
        switch (arg1){
            case SCROLL_STATE_FLING:
            case SCROLL_STATE_IDLE:
            case SCROLL_STATE_TOUCH_SCROLL:

        }
    }

    public void check() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        SimpleDateFormat s2df = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String now = s2df.format(currentDate);
        String today = sdf.format(currentDate);
        String sql = "select * from note where content !=''";
        Cursor c = dbread.rawQuery(sql, null);
        List<String> list = new ArrayList<String>();            //循环里挑出满足要求的人名，存在list里
        String peoples = "";                                       //最终字符串，存储人名
        while (c.moveToNext()) {
            String name = c.getString(c.getColumnIndex("content"));
            String dates = c.getString(c.getColumnIndex("date"));
            String datex1;
            Log.d("??","执行了");
            Date dx1;
            dx1 = StrToDate.ConverToDate(dates);
            datex1 = sdf.format(dx1);
            Log.d("names",name);


            if (datex1.equals(today)) {
                list.add(name);
                list.add(" ");

            }
        }
        String[] people = new String[list.size()];              //存储list转过来的人名，是中转数组
        list.toArray(people);
        for (int i = 0; i < people.length; i++) {
            peoples = peoples + people[i];
        }

        if ((list != null && list.size() == 0))
            {
            Toast.makeText(this,"今天没人过生日！",Toast.LENGTH_SHORT).show();

            }
        else {

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(this);



            String names = "my_package_channel";//渠道名字
            String id = "my_package_channel_1"; // 渠道ID
            String description = "my_package_first_channel"; // 渠道解释说明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_LOW;
                NotificationChannel mChannel = null;
                if (mChannel == null) {
                    mChannel = new NotificationChannel(id, names, importance);
                    mChannel.setDescription(description);
                    notificationManager.createNotificationChannel(mChannel);
                }

                builder.setSmallIcon(R.mipmap.ic_launcher); // 这里使用的系统默认图标，可自行更换
                builder.setTicker("您有一条新消息！");
                builder.setContentTitle("您的好友" + peoples + "今天过生日!");
                builder.setContentText("今天的日期是" + now + ",赶快发短信祝福他吧！");
                builder.setAutoCancel(true);
                builder.setChannelId(id);
                notificationManager.notify(1, builder.build());

                Toast.makeText(this, "您的好友(们)" + peoples + "今天过生日，" + "今天的日期是" + now + ",赶快发短信祝福他吧！",
                        Toast.LENGTH_SHORT).show();


            }
        }
    }

        // 点击listview中某一项的监听事件
        @Override
        public void onItemClick (AdapterView < ? > arg0, View arg1,int arg2, long arg3){
            Edit.ENTER_STATE = 1;
            String content = listview.getItemAtPosition(arg2) + "";
            String content1 = content.substring(content.indexOf("tv_content=") + 11, content.indexOf("}"));

            String datex1 = listview.getItemAtPosition(arg2) + "";
            String datex2 = datex1.substring(datex1.indexOf("tv_date=") + 8, datex1.indexOf(","));
            Cursor c = dbread.query("note", null,
                    "content=" + "'" + content1 + "'" + "and date=" + "'" + datex2 + "'", null, null, null, null);

            while (c.moveToNext()) {
                String No = c.getString(c.getColumnIndex("_id"));

                Intent myIntent = new Intent();
                Bundle bundle1 = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle1.putString("info", content1);
                bundle2.putString("date", datex2);
                Edit.id = Integer.parseInt(No);
                myIntent.putExtras(bundle1);
                myIntent.putExtras(bundle2);
                myIntent.setClass(MainActivity.this, Edit.class);
                startActivityForResult(myIntent, 1);
                c.close();
            }

        }


    @Override
    // 接受上一个页面返回的数据，并刷新页面
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            RefreshNotesList();
        }
    }





    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
        final int n = arg2;
        Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("删除该日志");
        builder.setMessage("确认删除吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String content = listview.getItemAtPosition(n) + "";
                String content1 = content.substring(content.indexOf("tv_content=") + 11, content.indexOf("}"));
                Log.d("FKK",content1);
                String datex1 = listview.getItemAtPosition(n) + "";
                String datex2 = datex1.substring(datex1.indexOf("tv_date=") + 8, datex1.indexOf(","));
                Log.d("FFFKK",datex2);

                Cursor c = dbread.rawQuery("select * from note where content='"+content1+"'", null);
                while (c.moveToNext()) {
                    String id = c.getString(c.getColumnIndex("_id"));
                    String sql_del = "update note set content = '' where _id= '"+id+"'";

                    String sql_del1 = "update note set date = '' where _id='"+id+"'";

                    dbread.execSQL(sql_del);
                    dbread.execSQL(sql_del1);
                    Log.d("FFFKK",id);
                    RefreshNotesList();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
        return true;
    }



}