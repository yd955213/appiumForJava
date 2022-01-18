package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.myAppium.Utils.commm.JsonUtil;
import com.myAppium.app.WebUtil;
import com.myAppium.driver.OkHttpDriver;
import com.myAppium.script.c3IOT.comm.entity.ResponseGson;
import com.myAppium.script.c3IOT.comm.entity.WebUICase;
import com.myAppium.script.c3IOT.floorControlMannagement.header.HeaderUI;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity.ListParkRequest;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity.ListParkResponse;
import com.myAppium.script.c3IOT.login.LoginUICase;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.ui.locationExpression.ParkUI;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.ui.locationExpression.SpaceTree;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class ParkUICase extends WebUICase {
    OkHttpDriver okHttpDriver;
    public ParkUICase(){
        webUtil = new WebUtil();
        okHttpDriver = OkHttpDriver.getInstance();
    }
//    http://172.168.91.51/admin/lk
    public void intoFloorControlSystem(){
        LoginUICase login = new LoginUICase();
        if(!LoginUICase.isIsLogin()){
            login.login();
        }
        try {
            webUtil.clickByXPath(HeaderUI.Floor_control_business);
        }catch (Exception e){
            webUtil.waitTitle("http://172.168.91.51/admin/lk");
        }
    }

    public void intoSpaceManagementUI(){
        intoFloorControlSystem();
        webUtil.clickByXPath(SpaceTree.SPACE);
    }

    public void intoParkManagementUI(){
        intoSpaceManagementUI();
        webUtil.clickByXPath(SpaceTree.PARK);

    }
    public void intoBuildingManagementUI(){
        intoSpaceManagementUI();
        webUtil.clickByXPath(SpaceTree.BUILDING);

    }
    public void intoFloorManagementUI(){
        intoSpaceManagementUI();
        webUtil.clickByXPath(SpaceTree.FLOOR);

    }
    public void intoRoomManagementUI(){
        intoSpaceManagementUI();
        webUtil.clickByXPath(SpaceTree.ROOM);

    }

    public void searchPackByName(String parkName){
        intoParkManagementUI();
        webUtil.inputByXPath(ParkUI.SEARCH_PARK_NAME_XPATH, parkName);
        webUtil.clickByXPath(ParkUI.SEARCH_BUTTON_XPATH);
        webUtil.sleep(500);
//        String textByXPath = webUtil.getTextByXPath(ParkUI.TOTAL_INFO);
//        System.out.println(textByXPath);

        String pageSize = webUtil.getTextByXPath(ParkUI.SEARCH_PARK_STATUS_COMBOBOX_TEXT_XPATH);
        // 提取下拉框中的数字
        pageSize = pageSize.split("条/页")[0].replace(" ", "");
        Integer size = 0;
        try {
            size = Integer.parseInt(pageSize, 10);
        } catch (NumberFormatException e) {
            size = 15;
//            e.printStackTrace();
        }
        // 通过 园区管理分页列表 接口 获取结果，进行UI的预期断言

        ListParkResponse listPark = getListPark(parkName, 1, size);
        System.out.println(listPark);
        if (listPark.getList().isEmpty()){
            Assertions.assertNotNull(webUtil.findElementXpath(ParkUI.empty_description));
            return;
        }
        Integer total = listPark.getPage().getTotal();
        Integer pageSize1 = listPark.getPage().getPageSize();

        Assertions.assertTrue(webUtil.getTextByXPath(ParkUI.TOTAL_INFO).contains(String.valueOf(total)));
        Assertions.assertTrue(webUtil.getTextByXPath(ParkUI.PAGE_COMBOBOX_CLASS).contains(String.valueOf(pageSize1)));

        // 断言表中的 第一条数据是想要查找到 的园区
        String locationExpression = "//td[contains(@title,\""+parkName+"\")]";
        Assertions.assertTrue(webUtil.getTextByXPath(locationExpression).contains(parkName));
    }

    public ListParkResponse getListPark(String parkName, Integer pageNum, Integer pageSize){
        ListParkRequest listParkRequest = new ListParkRequest();
        listParkRequest.setPageNum(pageNum);
        listParkRequest.setPageSize(pageSize);
        listParkRequest.setKeyword(parkName);
        String s = JsonUtil.toJsonStringNotNull(listParkRequest, ListParkRequest.class);
        String responseData = new ParkApiCase().listPack(s);
        ResponseGson<ListParkResponse> object = JsonUtil.parseObject(responseData, new TypeReference<ResponseGson<ListParkResponse>>() {
        }.getType());
        return object.getData();
    }

}
