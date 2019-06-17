package cn.edu.gdpt.numberphone.view;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.gdpt.numberphone.R;

public class RegisterActivity extends AppCompatActivity {
private MyDatabaseHelper dbHelper;
private Button register;
private EditText name,password;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper=new MyDatabaseHelper(getApplicationContext(),"Phone.db",null,1);
     name=(EditText)findViewById(R.id.edt_name);
     password=(EditText)findViewById(R.id.edt_password);
      register=(Button)findViewById(R.id.btn_register);


        register.setOnClickListener(new View.OnClickListener() {
          private String name1;
          private String password1;
            @Override
            public void onClick(View v) {
           name1=name.getText().toString();
           password1=Integer.valueOf(String.valueOf(password)).toString().trim();
           if (name1==null||password1==null){
               Toast.makeText(getApplicationContext(),"账号或密码不能为空",Toast.LENGTH_LONG).show();
           }else{
               SQLiteDatabase db=dbHelper.getWritableDatabase();
               ContentValues values=new ContentValues();

           }
            }
        });
    }
}
