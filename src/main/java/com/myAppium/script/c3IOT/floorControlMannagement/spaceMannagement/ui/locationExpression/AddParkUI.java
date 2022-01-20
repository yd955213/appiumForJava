package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.ui.locationExpression;

public interface AddParkUI {
    /**
     * 界面 新增园区、编辑远程、查看园区详情的定位表达式是一样的
     */
    String parkNameInput = "#parkName";
    String errorParkNameAlert = "//div[text()=\"请输入园区名称\"]";
    String parkAddressInput = ".ant-cascader-picker-label";
    /**输入选择城市弹出的下拉框*/
    String parkAddressList = "//ul[@class=\"ant-cascader-menu\"]";
    String errorParkAddressAlert = "//div[text()=\"请选择区域\"]";
    String keywordAddressInput  = "#address";
    /**输入关键地址弹出的下拉框*/
    String keywordAddressList = "//ul[@class=\"amap-ui-poi-picker-sugg-list\"]";
    String errorKeywordAddressAlert = "//div[text()=\"请输入园区地址\"]";
    /**这里可以找到两个元素，这里取第二个*/
    String locateIcon= "//div[@class=\"amap-control amap-geolocation\"][2]";
    String longitudeInput = "#longitude";
    String errorLongitudeAlert = "//div[text()=\"请输入经度\"]";
    String latitudeInput = "#latitude";
    String errorLatitude = "//div[text()=\"请输入经度\"]";
    String contactNameForParkInput = "#contactName";
    String errorTactNameForParkAlert = "//div[text()=\"请输入园区负责人\"]";
    String contactPhoneInput = "#contactPhone";
    String errorContactPhoneAlert = "//div[text()=\"请输入负责人电话\"]";
    String enabledRadio = "//span[text()=\"启用\"]";
    String disableRadio = "//span[text()=\"禁用\"]";
    String confirmButton = "//span[text()=\"确 定\"]";
    String cancelButton = "//span[text()=\"取 消\"]";
}