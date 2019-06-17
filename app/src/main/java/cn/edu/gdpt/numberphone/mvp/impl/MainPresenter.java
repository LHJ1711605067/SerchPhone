package cn.edu.gdpt.numberphone.mvp.impl;
import android.util.Log;

import cn.edu.gdpt.numberphone.business.HttpUtil;
import cn.edu.gdpt.numberphone.model.Phone;
import cn.edu.gdpt.numberphone.mvp.impl.MvpMainView;
import cn.edu.gdpt.numberphone.view.MainActivity;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class MainPresenter extends BasePresenter  {
    private static final String mUrl = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm";
    private MvpMainView mvpMainView;
    private Phone phone;
    private Gson gson = new Gson();
    public MainPresenter(MvpMainView mvpMainView){
        this.mvpMainView = mvpMainView;
    }

    public Phone getPhone() {
        return phone;
    }

    public void searchPhoneInfo(String phoneString){
        if (phoneString.length() != 11){
            mvpMainView.showToast("手机号码不正确");
            return;
        }
        mvpMainView.showLoading();
        //http request method
        sendHttp(phoneString);
        //
    }

    private void sendHttp(String phoneString){
        Map<String,String> map = new HashMap<String, String>();
        map.put("tel",phoneString);
        HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object obj) {
                String json = obj.toString();
                Log.d("---", "onSuccess: "+json);
                int index = json.indexOf("{");
                json = json.substring(index,json.length());

                phone = gson.fromJson(json,Phone.class);
                mvpMainView.cancelLoading();
                mvpMainView.updateView();
            }

            @Override
            public void onFail(String error) {
                mvpMainView.showToast(error);
                mvpMainView.cancelLoading();
            }
        });
        httpUtil.sendGetHttp(mUrl,map);
    }

    public void attach(MainActivity mainActivity) {

    }
}
