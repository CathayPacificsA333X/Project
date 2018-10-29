package com.test.administrator.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;
import java.math.*;



public class AdvanceCalculator extends AppCompatActivity implements View.OnClickListener {
    Button btn_return;
    Button btn_A0;
    Button btn_A1;
    Button btn_A2;
    Button btn_A3;
    Button btn_A4;
    Button btn_A5;
    Button btn_A6;
    Button btn_A7;
    Button btn_A8;
    Button btn_A9;
    Button btn_ClearR; //清除
    Button btn_delR;   //删除
    Button btn_10ToER;
    Button btn_16To2;
    Button btn_2To10;
    Button btn_2To16;
    Button btn_a;
    Button btn_b ;
    Button btn_c;
    Button btn_d;
    Button btn_e;
    Button btn_f;
    Button btn_sin;
    Button btn_cos;
    Button btn_tan;



    private TextView enter_Input;
    private StringBuilder st_number = new StringBuilder();


    private void initView() {
        btn_return = (Button) findViewById(R.id.btn_return);
        btn_A0 = (Button) findViewById(R.id.btn_A0);
        btn_A1 = (Button) findViewById(R.id.btn_A1);
        btn_A2 = (Button) findViewById(R.id.btn_A2);
        btn_A3 = (Button) findViewById(R.id.btn_A3);
        btn_A4 = (Button) findViewById(R.id.btn_A4);
        btn_A5 = (Button) findViewById(R.id.btn_A5);
        btn_A6 = (Button) findViewById(R.id.btn_A6);
        btn_A7 = (Button) findViewById(R.id.btn_A7);
        btn_A8 = (Button) findViewById(R.id.btn_A8);
        btn_A9 = (Button) findViewById(R.id.btn_A9);
        btn_sin = (Button) findViewById(R.id.btn_sin);
        btn_cos=(Button) findViewById(R.id.btn_cos);
        btn_tan=(Button) findViewById(R.id.btn_tan);
        btn_ClearR = (Button) findViewById(R.id.btn_ClearR);
        btn_delR = (Button) findViewById(R.id.btn_delR);

        btn_a = (Button) findViewById(R.id.btn_a);
        btn_b = (Button) findViewById(R.id.btn_b);
        btn_c = (Button) findViewById(R.id.btn_c);
        btn_d = (Button) findViewById(R.id.btn_d);
        btn_f = (Button) findViewById(R.id.btn_f);
        btn_e = (Button) findViewById(R.id.btn_e);
        btn_return = (Button) findViewById(R.id.btn_return);
        enter_Input = (TextView) findViewById(R.id.enter_input);
        btn_10ToER = (Button) findViewById(R.id.btn_10ToER);
        btn_16To2 = (Button) findViewById(R.id.btn_16To2);
        btn_2To10 = (Button) findViewById(R.id.btn_2To10);
        btn_2To16 = (Button) findViewById(R.id.btn_2To16);







        btn_return.setOnClickListener(this);
        btn_A0.setOnClickListener(this);
        btn_A1.setOnClickListener(this);
        btn_A2.setOnClickListener(this);
        btn_A3.setOnClickListener(this);
        btn_A4.setOnClickListener(this);
        btn_A5.setOnClickListener(this);
        btn_A6.setOnClickListener(this);
        btn_A7.setOnClickListener(this);
        btn_A8.setOnClickListener(this);
        btn_A9.setOnClickListener(this);
        btn_a.setOnClickListener(this);
        btn_b.setOnClickListener(this);
        btn_c.setOnClickListener(this);
        btn_d.setOnClickListener(this);
        btn_f.setOnClickListener(this);
        btn_e.setOnClickListener(this);
        btn_delR.setOnClickListener(this);
        btn_10ToER.setOnClickListener(this);
        btn_16To2.setOnClickListener(this);
        btn_2To10.setOnClickListener(this);
        btn_2To16.setOnClickListener(this);
        btn_ClearR.setOnClickListener(this);
        btn_sin.setOnClickListener(this);
        btn_cos.setOnClickListener(this);
        btn_tan.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_calculator);

        initView();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_A0:
                st_number = st_number.append(0);
                enter_Input.setText(st_number);
                break;
            case R.id.btn_A1:
                st_number = st_number.append(1);
                enter_Input.setText(st_number);
                break;
            case R.id.btn_A2:
                st_number = st_number.append(2);
                enter_Input.setText(st_number);
                break;
            case R.id.btn_A3:
                st_number = st_number.append(3);
                enter_Input.setText(st_number);
                break;
            case R.id.btn_A4:
                st_number = st_number.append(4);
                enter_Input.setText(st_number);
                break;
            case R.id.btn_A5:
                st_number = st_number.append(5);
                enter_Input.setText(st_number);
                break;
            case R.id.btn_A6:
                st_number = st_number.append(6);
                enter_Input.setText(st_number);
                break;
            case R.id.btn_A7:
                st_number = st_number.append(7);
                enter_Input.setText(st_number);
                break;
            case R.id.btn_A8:
                st_number = st_number.append(8);
                enter_Input.setText(st_number);
                break;
            case R.id.btn_A9:
                st_number = st_number.append(9);
                enter_Input.setText(st_number);
                break;
            case R.id.btn_a:
                st_number = st_number.append("A");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_b:
                st_number = st_number.append("B");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_c:
                st_number = st_number.append("C");
                enter_Input.setText(st_number);
                break;

            case R.id.btn_d:
                st_number = st_number.append("D");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_e:
                st_number = st_number.append("E");
                enter_Input.setText(st_number);
                break;
            case R.id.btn_f:
                st_number = st_number.append("F");
                enter_Input.setText(st_number);
                break;

            case R.id.btn_ClearR:
                st_number = st_number.delete(0, st_number.length());//该方法从字符串起点，一直删到终点。
                enter_Input.setText(st_number);
                break;
            case R.id.btn_delR: //删除
                if (st_number.length() != 0) {
                    st_number = st_number.delete(st_number.length() - 1, st_number.length());//通过DeleteCharAt函数直接定位删掉最后一位的字符.
                    enter_Input.setText(st_number);
                }
                break;
            case R.id.btn_return:

                Intent intent = new Intent(AdvanceCalculator.this, MainActivity.class);
                startActivity(intent);

            break;

            case R.id.btn_10ToER:
               if(st_number.length()>=1) {
                   int i = Integer.parseInt(String.valueOf(st_number));
                enter_Input.setText(Integer.toBinaryString(i));
                }

               else
                    enter_Input.setText("Error");
                    break;
            case R.id.btn_16To2:
                if(st_number.length()>=1){
                int i =Integer.parseInt(String.valueOf(st_number));
                    enter_Input.setText(Integer.toHexString(Integer.parseInt(String.valueOf(i))));
                }
                else
                    enter_Input.setText("Error");
                break;
            case R.id.btn_2To10:

                if(st_number.length()>=1){
                    int i =Integer.parseInt(String.valueOf(st_number));;
                enter_Input.setText(Integer.valueOf(String.valueOf(i), 2).toString());
                }
                else
                    enter_Input.setText("Error");
                break;
            case R.id.btn_2To16:
                if(st_number.length()>=1){
                    int i =Integer.parseInt(String.valueOf(st_number));;
                  enter_Input.setText(Integer.toHexString(Integer.parseInt(String.valueOf(i), 2)));
                }
                else
                    enter_Input.setText("Error");
                break;
            case R.id.btn_sin:
                double i=0;
                i=Double.parseDouble(String.valueOf(st_number));
                double angle = i * Math.PI / 180;
                double j = Math.sin(angle);


                enter_Input.setText(""+ j);
                break;


                case R.id.btn_cos:
                double a=0;
                a=Double.parseDouble(String.valueOf(st_number));
                double angleB = a * Math.PI / 180;
                double b = Math.cos(angleB);
                    enter_Input.setText(""+ b);
                    break;
            case R.id.btn_tan:
                double i1 = 0;
                i1=Double.parseDouble(String.valueOf(st_number));
                double angleC = i1 * Math.PI / 180;
                double c = Math.tan(angleC);
                enter_Input.setText(""+ c);
                break;

                default:
                    break;

        }
    }
}