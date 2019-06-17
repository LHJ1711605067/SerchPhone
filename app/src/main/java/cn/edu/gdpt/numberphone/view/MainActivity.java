package cn.edu.gdpt.numberphone.view;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView mTvProvince;
    private TextView mTvCatName;
    private TextView mTvCarrier;
    private TextView mTvPhoneNum;
    private MainPresenter mainPresenter;
    private LoadingDialog loadingDialog;
    private String name1;
    private String password1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtPhoneInput = (EditText) findViewById(R.id.et_phone_input);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mTvPhoneNum = (TextView) findViewById(R.id.tv_phone_num);
        mTvProvince = (TextView) findViewById(R.id.tv_province);
        mTvCatName = (TextView) findViewById(R.id.tv_catName);
        mTvCarrier = (TextView) findViewById(R.id.tv_carrier);

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
