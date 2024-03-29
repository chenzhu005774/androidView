package com.amtzhmt.launcher.login;
import com.amtzhmt.launcher.util.utils.bean.CustomerEntity;
import com.amtzhmt.launcher.mvp.BasePresenterImpl;
import com.amtzhmt.launcher.util.utils.net.Api;
import com.amtzhmt.launcher.util.utils.sqlite.CustomerInfoDB;
import com.amtzhmt.launcher.util.utils.LogUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.amtzhmt.launcher.util.utils.CheckNet.getMacDefault;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    @Override
    public void login(final String name, final String pwd,final String mac) {
        //登录
        Map<String, String> hashMap = new HashMap();
        hashMap.put("iptvAccount", name);
        hashMap.put("mac", mac);
        hashMap.put("password", pwd);
        Api.getDefault().login(hashMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    JSONObject jSONObject = new JSONObject(result);
                    String orgCode = jSONObject.getJSONObject("data").getString("orgCode");
                    String customerCode=jSONObject.getJSONObject("data").getString("customerCode");

                    CustomerInfoDB customerInfoDB = new CustomerInfoDB(mView.getContext());
                    CustomerEntity customerEntity = new CustomerEntity();
                    customerEntity.setName(name);
                    customerEntity.setPwd(pwd);
                    customerEntity.setCode(customerCode);
                    customerEntity.setMac(getMacDefault(mView.getContext()));
                    customerInfoDB.saveObject(customerEntity);

                    Api.getDefault().getPage(orgCode, customerCode).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String result  = response.body().string();
                                mView.loginsuccess(result);
                            } catch ( Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            mView.loginfail();
                        }
                    });

                } catch ( Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mView.loginfail();
            }

        });

    }
}
