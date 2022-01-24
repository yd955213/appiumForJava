package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement;

import com.myAppium.app.WebUtil;
import com.myAppium.driver.OkHttpDriver;
import com.myAppium.script.c3IOT.comm.entity.ResponseGson;
import com.myAppium.script.c3IOT.comm.entity.WebUICase;
import com.myAppium.script.c3IOT.floorControlMannagement.header.HeaderUI;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity.ListParkResponse;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.ui.locationExpression.AddParkUI;
import com.myAppium.script.c3IOT.login.LoginUICase;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.ui.locationExpression.ParkUI;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.ui.locationExpression.SpaceTree;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.springframework.util.ObjectUtils;

import java.util.List;

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
        // 页面标题
        webUtil.waitTitle("C3 IoT");
        WebElement webElement = webUtil.clickByXPath(HeaderUI.Floor_control_business);
        // 找不到就在执行一次
        if (webElement == null) {
            webUtil.clickByXPath(HeaderUI.Floor_control_business);
        }
        webUtil.sleep(50);
    }

    public void intoSpaceManagementUI(){
        intoFloorControlSystem();
        webUtil.clickByXPath(SpaceTree.SPACE);
        // 防止UI 刷新不及时， 这里等待100毫秒， 若不考虑 测试的时长， 等待时间越长越好
        webUtil.sleep(100);
    }

    public void intoParkManagementUI(){
        intoSpaceManagementUI();
        webUtil.clickByXPath(SpaceTree.PARK);
        webUtil.sleep(100);

    }
    public void intoBuildingManagementUI(){
        intoSpaceManagementUI();
        webUtil.clickByXPath(SpaceTree.BUILDING);
        webUtil.sleep(100);

    }
    public void intoFloorManagementUI(){
        intoSpaceManagementUI();
        webUtil.clickByXPath(SpaceTree.FLOOR);
        webUtil.sleep(100);

    }
    public void intoRoomManagementUI(){
        intoSpaceManagementUI();
        webUtil.clickByXPath(SpaceTree.ROOM);
        webUtil.sleep(100);
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
        ResponseGson<ListParkResponse> listPark = new ParkApiCase().listPack(parkName, 1, size);
        System.out.println(listPark);
        if (listPark.getData().getList().isEmpty()){
            Assertions.assertNotNull(webUtil.findElementXpath(ParkUI.empty_description));
            return;
        }
        Integer total = listPark.getData().getPage().getTotal();
        Integer pageSize1 = listPark.getData().getPage().getPageSize();

        Assertions.assertTrue(webUtil.getTextByXPath(ParkUI.TOTAL_INFO).contains(String.valueOf(total)));
        Assertions.assertTrue(webUtil.getTextByXPath(ParkUI.PAGE_COMBOBOX_CLASS).contains(String.valueOf(pageSize1)));

        // 断言表中的 第一条数据是想要查找到 的园区
        String locationExpression = "//td[contains(@title,\""+parkName+"\")]";
        Assertions.assertTrue(webUtil.getTextByXPath(locationExpression).contains(parkName));
    }


    public void addParkCase(String ... params){
        // 进入编辑页面
        intoParkManagementUI();

        webUtil.clickByXPath(ParkUI.ADD_BUTTON_XPATH);
        webUtil.sleep(100);

        webUtil.getWindowHandles();

        int length = params.length;
//        // 不输入任何参数直接确定
//        if(length == 0) {
//            webUtil.clickByXPath(AddParkUI.confirmButton);
//            // 断言
//        }

        webUtil.input(AddParkUI.parkNameInput, "test01");

//        webUtil.input(AddParkUI.keywordAddressInput, "达实");
        // 进行城市选择
        webUtil.click(AddParkUI.parkAddressInput);
        webUtil.sleep(100);
        webUtil.hover(AddParkUI.parkAddressList);
        String cityLocationExpression = "//li[contains(@title,\"%s\")]";
        webUtil.click(String.format(cityLocationExpression, "广东"));
        webUtil.click(String.format(cityLocationExpression, "深圳"));
        webUtil.click(String.format(cityLocationExpression, "南山"));
        webUtil.input(AddParkUI.keywordAddressInput, "达实");
        webUtil.hover(AddParkUI.keywordAddressList);
        webUtil.click(AddParkUI.keywordAddressInput);

        String keywordAddressList = "//span[contains(text() ,\"%s\")]";
        List<WebElement> cityList= webUtil.findElements(String.format(keywordAddressList, "达实"));
        if (!ObjectUtils.isEmpty(cityList)){
            // 默认点击第二个，其设计为：第一个为搜索，第二个开始为实际地址
            cityList.get(1).click();
        }

        webUtil.sleep(100);
        // 如果获取城市成功，会自动补齐经纬度
        System.out.println("longitudeInput = " + webUtil.getText(AddParkUI.longitudeInput));
//        webUtil.runJs("window.document.getElementById(\"longitude\").value");
        System.out.println("longitudeInput = " + webUtil.getTestByJs(AddParkUI.longitudeInput) );
//        System.out.println("latitudeInput" + webUtil.getText(AddParkUI.latitudeInput));

        webUtil.input(AddParkUI.contactNameForParkInput, "test1");
        webUtil.input(AddParkUI.contactPhoneInput, "123");
        System.out.println("contactPhoneInput = " + webUtil.getText(AddParkUI.contactPhoneInput));
        webUtil.click(AddParkUI.disableRadio);
    }
}
