package com.myAppium.script.c3IOT.login.api.interfaces;


import com.myAppium.script.c3IOT.comm.entity.WebUrl;

public interface LoginUrl  {
    String  LOGIN_URL = WebUrl.url +"/login";
    String token = WebUrl.url + "/auth/api/v1.0/client/token";
    String getDetailByToken = WebUrl.url + "/admin/system/api/User/GetDetailByToken";
}
