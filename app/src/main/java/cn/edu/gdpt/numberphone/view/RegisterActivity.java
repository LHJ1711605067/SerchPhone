package cn.edu.gdpt.numberphone.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.gdpt.numberphone.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button register=(Button)findViewById(R.id.btn_register);
        Button sign=(Button)findViewById(R.id.btn_sign);
        final EditText edt_name=(EditText)findViewById(R.id.edt_name);
        final EditText edt_psw=(EditText)findViewById(R.id.edt_password);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String name=edt_name.getText().toString().trim();
               String psw=edt_psw.getText().toString().trim();
                 if (!name.equals("")&&!psw.equals("")){
                        SharedPreferences.Editor editor=getSharedPreferences("Phone",MODE_PRIVATE).edit();
                        editor.putString("name",name);
                        editor.putString("psw", psw);
                        editor.commit();
                        Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();

                 }      if (name.equals("")){
                    Toast.makeText(getApplicationContext(),"账号不能为空",Toast.LENGTH_SHORT).show();
                }if (psw.equals("")){
                    Toast.makeText(getApplicationContext(),"账密码不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    sign.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name=edt_name.getText().toString().trim();
            String psw=edt_psw.getText().toString().trim();

                SharedPreferences pref= getSharedPreferences("Phone",MODE_PRIVATE);
                String name1=pref.getString("name","");
                String psw1=pref.getString("psw","");
                if (name.equals(name)&&psw.equals(psw1)){
                    Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                  Intent intent =new Intent(getApplicationContext(),Main2Activity.class);
                  startActivity(intent);
                }if(!name.equals(name1)||!psw.equals(psw1)){

                Toast.makeText(getApplicationContext(),"账号或密码错误",Toast.LENGTH_SHORT).show();
                }

        }
    });
    }
}
