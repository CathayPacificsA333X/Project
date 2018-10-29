package com.test.administrator.cx333;


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

    private Context Contexts;
    private ListView listview;
    private SimpleAdapter simp_adapter;
    private List<Map<String, Object>> diaryList;
    private Button addDiary;
    public TextView title;
    private SearchView searchView;
    private DiaryDBHelper DB;
    public Context cx1;
    private SQLiteDatabase dbread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (TextView) findViewById(R.id.title);
        listview = (ListView) findViewById(R.id.listview);
        diaryList = new ArrayList<Map<String, Object>>();
        addDiary = (Button) findViewById(R.id.btn_add);
        searchView = (SearchView) findViewById(R.id.searchView);
        Contexts = this;
        cx1 = this;




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
        DB = new DiaryDBHelper(this);
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
    }








    public void showList(String part) {
        Cursor cursor = dbread.rawQuery("select * from note where content like '%" + part + "%';", null);
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
        switch (arg1) {
            case SCROLL_STATE_FLING:
            case SCROLL_STATE_IDLE:
            case SCROLL_STATE_TOUCH_SCROLL:

        }
    }

    // 点击listview中某一项的监听事件
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Edit.ENTER_STATE = 1;
        String content = listview.getItemAtPosition(arg2) + "";
        String content1 = content.substring(content.indexOf("tv_content=") + 11, content.indexOf("}"));

        Cursor c = dbread.query("note", null,
                "content=" + "'" + content1 + "'", null, null, null, null);

        while (c.moveToNext()) {
            String No = c.getString(c.getColumnIndex("_id"));

            Intent myIntent = new Intent();
            Bundle bundle1 = new Bundle();
            bundle1.putString("info", content1);
            Edit.id = Integer.parseInt(No);
            myIntent.putExtras(bundle1);

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
                Cursor c = dbread.query("note", null, "content=" + "'"
                        + content1 + "'", null, null, null, null);
                while (c.moveToNext()) {
                    String id = c.getString(c.getColumnIndex("_id"));
                    String sql_del = "update note set content='' where _id="
                            + id;
                    dbread.execSQL(sql_del);
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