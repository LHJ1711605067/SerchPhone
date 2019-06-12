package cn.edu.gdpt.numberphone.mvp.impl;



public interface MvpMainView extends MvpLoadingView{
    void showToast(String msg);
    void updateView();

    void showLoading();

    void cancelLoading();
}

