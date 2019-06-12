package cn.edu.gdpt.numberphone.mvp.impl;

public interface MvpLoadingView {
    interface MvpMainView {
        void showLoading();

        void cancelLoading();

        void showToast(String msg);

        void updateView();
    }
}
