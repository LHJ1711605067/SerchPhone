package cn.edu.gdpt.numberphone.view;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Currency;
import java.util.HashMap;
import java.util.Random;

import cn.edu.gdpt.numberphone.model.Phone;
import cn.edu.gdpt.numberphone.mvp.impl.MvpMainView;
import cn.edu.gdpt.numberphone.mvp.impl.MainPresenter;
import cn.edu.gdpt.numberphone.view.LoadingDialog;

import cn.edu.gdpt.numberphone.R;
import cn.edu.gdpt.numberphone.mvp.impl.MvpLoadingView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MvpMainView {
    private MyDatabaseHelper dbHelper;
    private EditText mEtPhoneInput,name,password;
    private Button mBtnSearch,register;
    private TextView mTvProvince,luck;
    private TextView mTvCatName;
    private TextView mTvCarrier;
    private TextView mTvPhoneNum;
    private TextView find,find1;
    private MainPresenter mainPresenter;
    private LoadingDialog loadingDialog;
    private String name1;
    private String password1;
    private MyDatabaseHelper myDatabaseHelper;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtPhoneInput = findViewById(R.id.et_phone_input);
        mBtnSearch = findViewById(R.id.btn_search);
        mTvPhoneNum = findViewById(R.id.tv_phone_num);
        mTvProvince = findViewById(R.id.tv_province);
        mTvCatName = findViewById(R.id.tv_catName);
        mTvCarrier = findViewById(R.id.tv_carrier);
       luck= findViewById(R.id.tv_luck);

       find1=findViewById(R.id.tv_find1);
        mBtnSearch.setOnClickListener(this);
        mainPresenter = new MainPresenter(this);
        mainPresenter.attach(this);
    }


    private boolean isEmpty(){
        return mEtPhoneInput.getText().toString().isEmpty();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_search){



     String number=mEtPhoneInput.getText().toString().trim();



    MyDatabaseHelper dbhelper=new MyDatabaseHelper(getApplicationContext(),"number_db",null,1);
    SQLiteDatabase db=dbhelper.getWritableDatabase();
ContentValues values =new ContentValues();
values.put("number",number);
db.insert("Phone",null,values);




Cursor cursor= db.query("Phone",new String[]{"number"},null,null,null,null,null);
while (cursor.moveToNext()){
    String number2=cursor.getString(cursor.getColumnIndex("number"));
                find1.setText(number2);
            }
             cursor.close();

            int num = (int) (Math.random()*(3+1));
           if (num == 0&&(number.length()== 11)){
               luck.setText("大吉");
           }  if (num==1&&(number.length()== 11)){
                luck.setText("大凶");
            }  if (num==2&&(number.length()== 11)){
                luck.setText("小吉");
            }  if (num==3&&(number.length()== 11)){
                luck.setText("小凶");
            }


            if (!isEmpty()){
                mainPresenter.searchPhoneInfo(mEtPhoneInput.getText().toString());
                mEtPhoneInput.setText("");
            }

        }
    }

    @Override
    public void showLoading() {
        if (loadingDialog == null){
            loadingDialog = new LoadingDialog(this);
            loadingDialog.setMessage("正在查询...");
        } else loadingDialog.setMessage("正在查询...");
        loadingDialog.show();
    }

    @Override
    public void cancelLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.cancel();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateView() {
        Phone phone = mainPresenter.getPhone();
        String carrier = phone.getCarrier();
        mTvPhoneNum.setText("手机号码："+phone.getTelString());
        mTvProvince.setText("省份："+phone.getProvince());
        mTvCatName.setText("运营商："+phone.getCatName());
        mTvCarrier.setText("运营者："+phone.getCarrier());
    }

}
