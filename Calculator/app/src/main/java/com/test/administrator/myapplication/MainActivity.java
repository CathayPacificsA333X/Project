package com.test.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;
import com.test.administrator.myapplication.InfixInToDuffix;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_point;
    Button btn_clear;
    Button btn_del;
    Button btn_plus;
    Button btn_minus;
    Button btn_multiply;
    Button btn_divide;
    Button btn_equal;
    Button btn_left;
    Button btn_right;
    Button btn_exchange;
    Button btn_change;
    private TextView enter_Input;
    private StringBuilder st_number = new StringBuilder();

    private void initView() {
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_clear = (Button) findViewById(R.id.btn_Clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        enter_Input = (TextView) findViewById(R.id.enter_input);
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_right = (Button) findViewById(R.id.btn_right);
        btn_exchange = (Button) findViewById(R.id.btn_Exchange);
        btn_change = (Button) findViewById(R.id.btn_change);


        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_exchange.setOnClickListener(this);
        btn_change.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

@Override
    public void onClick(View V1) {
        int last = 0;

        if(st_number.length()!=0)
        {
            last = st_number.codePointAt(st_number.length()-1);

        }
        switch (V1.getId()) {
            case R.id.btn_0:
                st_number = st_number.append("0");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_1:
                st_number = st_number.append("1");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_2:
                st_number = st_number.append("2");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_3:
                st_number = st_number.append("3");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_4:
                st_number = st_number.append("4");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_5:
                st_number = st_number.append("5");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_6:
                st_number = st_number.append("6");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_7:
                st_number = st_number.append("7");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_8:
                st_number = st_number.append("8");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_9:
                st_number = st_number.append("9");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_plus:

                st_number = st_number.append("+");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_minus:
                st_number = st_number.append("-");

                enter_Input.setText(st_number);
                break;
            case R.id.btn_multiply:

                st_number = st_number.append("*");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_divide:
                st_number = st_number.append("/");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_point:
                if (judge_1()) {
                    st_number = st_number.append(".");
                    enter_Input.setText(st_number);
                }
                break;
            case R.id.btn_right:// )右括号
                if((last>='0' &&last<='9'||last==')')&&Judge_2()==1) {
                    st_number = st_number.append(")");
                    enter_Input.setText(st_number);
                }
                break;
            case R.id.btn_left:// （左括号
                if((last!='(')||(last<='0' &&last>='9')){
                    st_number = st_number.append("(");
                    enter_Input.setText(st_number);
                }
                break;

            case R.id.btn_Exchange:
              st_number.insert(0,"(");
              st_number.append(")*(0-1)");

              enter_Input.setText(st_number);
              break;


              case R.id.btn_change:
                Intent intent=new Intent(MainActivity.this,AdvanceCalculator.class);
                startActivity(intent);
                break;



            case R.id.btn_del: //删除
                if (st_number.length() != 0) {
                    st_number = st_number.delete(st_number.length()-1,st_number.length());
                    enter_Input.setText(st_number);
                }
                break;
            case R.id.btn_Clear:
                st_number = st_number.delete(0, st_number.length());
                enter_Input.setText(st_number);//此时清空屏幕
                break;
            case R.id.btn_equal:
                if ((st_number.length() > 1)) {
                    InfixInToDuffix inf = new InfixInToDuffix();
                    String Result;
                    try {
                        String a = inf.toSuffix(st_number);
                        Result = inf.dealEquation(a);

                    } catch (Exception ex) {
                        Result = "出错";
                    }
                    enter_Input.setText(st_number + "=" + Result);
                    st_number = st_number.delete(0, st_number.length());
                    if (Character.isDigit(Result.charAt(0))) {
                        st_number = st_number.append(Result);
                    }
                }
                break;
            default:
                break;
        }
    }

    public boolean judge_1() {
        String a = "+-*/.";
        int[] b = new int[a.length()];
        int max;
        for (int i = 0; i < a.length(); i++) {
            String c = "" + a.charAt(i);
            b[i] = st_number.lastIndexOf(c);
        }
        Arrays.sort(b);
        if (b[a.length() - 1] == -1) {
            max = 0;
        } else {
            max = b[a.length() - 1];
        }
        if (st_number.indexOf(".", max) == -1) {
            return true;
        } else {
            return false;
        }
    }
    public int Judge_2(){
        int a=0,b=0;
        for(int i = 0; i < st_number.length() ; i++){
            if(st_number.charAt(i)=='(' ) {
                a++;
            }
            if(st_number.charAt(i)==')' ) {
                b++;
            }
        }
        if(a == b)
            return 0;
        if(a > b)
            return 1;
        return 2;
    }




}