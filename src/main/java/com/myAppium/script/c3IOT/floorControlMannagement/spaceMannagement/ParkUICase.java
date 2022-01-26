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

    private final String defaultParkName = "test01";
    private final String defaultCity = "广东-深圳-南山";
    private final String defaultKeywordAddress= "达实";
    private final String defaultLongitude = "0";
    private final String defaultLatitude = "0";
    private final String defaultHeadOfThePark = "test01";
    private final String defaultContactPhone = "13528487583";



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

    public boolean searchPackByName(String parkName){
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
            return true;
        }
        Integer total = listPark.getData().getPage().getTotal();
        Integer pageSize1 = listPark.getData().getPage().getPageSize();

        Assertions.assertTrue(webUtil.getTextByXPath(ParkUI.TOTAL_INFO).contains(String.valueOf(total)));
        Assertions.assertTrue(webUtil.getTextByXPath(ParkUI.PAGE_COMBOBOX_CLASS).contains(String.valueOf(pageSize1)));

        // 断言表中的 第一条数据是想要查找到 的园区
        String locationExpression = "//td[contains(@title,\""+parkName+"\")]";
        Assertions.assertTrue(webUtil.getTextByXPath(locationExpression).contains(parkName));
        return true;
    }

    public void addParkCase(String type, String value, String expected){

        switch(type.toLowerCase()){
            case "parkname":
                modifyParkName(value, expected);
                break;
            default:
                System.out.println("园区自动化测试用例：暂不支持该类型，期望：parkname、; 输入的是：" + type);
                break;
        }
//        // 如果获取城市成功，会自动补齐经纬度
//
//        // 如果经度参数不为空，则进行经度测试测试
//
//        // 如果维度参数不为空，则进行维度测试测试
//        System.out.println("contactPhoneInput = " + webUtil.getText(AddParkUI.contactPhoneInput));
//        webUtil.click(AddParkUI.disableRadio);
    }

    public boolean modifyParkName(String parkName, String expected){
        String oldParkName = webUtil.getText(AddParkUI.parkNameInput);
        intoAddParkUI();
        doModifyParkName(parkName);
        doModifyCities(defaultCity);
        doModifyKeywordAddress(defaultKeywordAddress);
        doModifyLongitude(defaultLongitude, false);
        doModifyLatitude(defaultLatitude, false);
        doModifyContactName(defaultHeadOfThePark);
        doModifyContactPhone(defaultContactPhone);
        parkStateEnable();
        saveParkInfo();

        if (ObjectUtils.isEmpty(parkName)){
            webUtil.sleep(100);
            Assertions.assertNotNull(webUtil.findElement(AddParkUI.errorParkNameAlert));
            return true;
        }

        if(parkName.equals(expected)){
            // 调用查询 case 能够查询到 修改后的数据
            Assertions.assertTrue(searchPackByName(parkName));
        }
        return true;
    }

    private void doModifyParkName(String parkName){
        webUtil.input(AddParkUI.parkNameInput, parkName);
    }

    /**
     * 城市 的 省、市、区 使用 “-” 进行分割
     * @param city
     */
    private void doModifyCities(String city){
        // 进行城市选择
        webUtil.click(AddParkUI.parkAddressInput);
        webUtil.sleep(100);
        webUtil.hover(AddParkUI.parkAddressList);

        String[] cities = city.split("-");
        if(cities.length != 3){
            System.out.println("添加园区界面：城市选择下拉框输入参数错误，自动化用例失败");
            return;
        }
        String cityLocationExpression = "//li[contains(@title,\"%s\")]";
        webUtil.click(String.format(cityLocationExpression, cities[0]));
        webUtil.click(String.format(cityLocationExpression, cities[1]));
        webUtil.click(String.format(cityLocationExpression, cities[2]));
    }

    /**
     * 修改关键地址
     * @param keywordAddress 关键地址
     */
    private void doModifyKeywordAddress(String keywordAddress){
        // 界面功能：输入关键信息，可自动调用高德地图接口，返回地址信息
        webUtil.input(AddParkUI.keywordAddressInput, keywordAddress);
        webUtil.hover(AddParkUI.keywordAddressList);
        webUtil.click(AddParkUI.keywordAddressInput);
        String keywordAddressList = "//span[contains(text() ,\"%s\")]";
        List<WebElement> cityList= webUtil.findElements(String.format(keywordAddressList, "达实"));
        if (!ObjectUtils.isEmpty(cityList)){
            // 默认点击第二个，其设计为：第一个为搜索，第二个开始为实际地址
            cityList.get(1).click();
        }
        webUtil.sleep(100);
    }

    /**
     * 修改自动获取的经度
     * @param longitude 经度
     * @param isModify 是否修改
     */
    private void doModifyLongitude(String longitude, boolean isModify){
        if (isModify){
            webUtil.input(AddParkUI.longitudeInput, longitude);
        }
        System.out.println("longitudeInput = " + webUtil.getText(AddParkUI.longitudeInput));
    }

    /**
     * 修改自动获取的维度
     * @param latitude 维度
     * @param isModify 是否修改
     */
    private void doModifyLatitude(String latitude, boolean isModify){
        if (isModify){
            webUtil.input(AddParkUI.latitudeInput, latitude);
        }
        System.out.println("longitudeInput = " + webUtil.getTestByJs(AddParkUI.latitudeInput) );
    }

    /**
     * 修改园区联系人
     * @param headOfThePark 联系人
     */
    private void doModifyContactName(String headOfThePark){
        webUtil.input(AddParkUI.contactNameForParkInput, headOfThePark);
    }

    /**
     * 修改园区联系人电话
     * @param contactPhoto 联系人电话
     */
    private void doModifyContactPhone(String contactPhoto){
        webUtil.input(AddParkUI.contactPhoneInput, contactPhoto);
    }

    /**
     * 点击确定
     */
    private void saveParkInfo(){
        webUtil.click(AddParkUI.confirmButton);
    }

    /**
     * 取消
     */
    private void cancel(){
        webUtil.click(AddParkUI.cancelButton);
    }

    private void parkStateDisable(){
        webUtil.click(AddParkUI.disableRadio);
    }
    private void parkStateEnable(){
        webUtil.click(AddParkUI.enabledRadio);
    }

    private void intoAddParkUI(){

        // 进入编辑页面
        intoParkManagementUI();

        webUtil.clickByXPath(ParkUI.ADD_BUTTON_XPATH);
        webUtil.sleep(100);
    }

    private void intoEditParkUI(){
        // 进入编辑页面
        intoParkManagementUI();
        webUtil.clickByXPath(ParkUI.DETAIL_BUTTON_XPATH);
        webUtil.sleep(100);
    }

}
