package com.test.administrator.birthday;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends Activity {
    private TextView tv_date;
    private EditText et_content;
    private EditText et_date;
    private EditText et_rank;
    private Button btn_ok;
    private Button btn_cancel;
    private BirthdayDBHelper DB;
    private SQLiteDatabase dbread;
    public static int ENTER_STATE = 0;
    public static String last_content;
    public static String last_date;
    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);

        tv_date = (TextView) findViewById(R.id.tv_date);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = sdf.format(date);
        tv_date.setText(dateString);

        et_content = (EditText) findViewById(R.id.et_content);
        et_date = (EditText)findViewById(R.id.et_date);
        //et_rank = (EditText)findViewById(R.id.et_rank);
        // 设置软键盘自动弹出
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        DB = new BirthdayDBHelper(this);
        dbread = DB.getReadableDatabase();

        Bundle myBundle = this.getIntent().getExtras();
        last_content = myBundle.getString("info");
        last_date = myBundle.getString("date");
        et_date.setText(last_date);
        et_content.setText(last_content);

        // 确认按钮的点击事件
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(
                new OnClickListener()
                {
                    public void onClick(View arg0) {
                        // 获取日志内容
                        String content = et_content.getText().toString();
                        String datex = et_date.getText().toString();
                       // String rank = et_rank.getText().toString();
                        try {
                            StrToDate.ConverToDate(datex);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // 获取写日志时间
                       tv_date.setText(datex);
                        String sql;
                        String sql_count = "SELECT COUNT(*) FROM note";
                        SQLiteStatement statement = dbread.compileStatement(sql_count);
                        long count = statement.simpleQueryForLong();

                        // 添加一个新的日志
                        if (ENTER_STATE == 0) {
                            if (!content.equals("")) {
                                sql = "insert into " + BirthdayDBHelper.TABLE_NAME_NOTES
                                        + " values(" + count + "," + "'" + content
                                        + "'" + "," + "'" + datex + "')";
                                Log.d("CX",""+count);
                                dbread.execSQL(sql);
                            }
                        }
                        // 查看并修改一个已有的日志
                        else {
                            String updatesql = "update note set content='"
                                    + content + "' where _id=" + id;
                            String updatesql2 = "update note set date='"
                                    + datex + "' where _id=" + id;
                            dbread.execSQL(updatesql);
                            dbread.execSQL(updatesql2);
                            // et_content.setText(last_content);
                        }
                        Intent data = new Intent();
                        setResult(2, data);
                        finish();
                    }
                });
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

    }
}