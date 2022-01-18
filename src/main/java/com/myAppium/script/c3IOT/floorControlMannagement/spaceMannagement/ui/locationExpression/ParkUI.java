package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.ui.locationExpression;

public interface ParkUI {
    /**
     * 园区管理UI
     */
    /**搜索园区名称*/
    String SEARCH_PARK_NAME_XPATH ="//input[contains(@class, \"ant-input\")]";
    /**园区状态下拉框*/
    String SEARCH_PARK_STATUS_COMBOBOX_TEXT_XPATH= "//span[@class=\"ant-select-selection-placeholder\"]";
    String PARK_STATUS_COMBOBOX_ENABLE_XPATH  ="//div[text()=\"启用\"]";
    String PARK_STATUS_COMBOBOX_DISABLE_XPATH  ="//div[text()=\"禁用\"]";
    String SEARCH_BUTTON_XPATH ="//span[text()=\"查 询\"]";
    String CLEAR_BUTTON_XPATH ="//span[text()=\"清 空\"]";
    String ADD_BUTTON_XPATH ="//span[text()=\"添 加\"]";
    String EDITOR_BUTTON_XPATH ="//span[text()=\"编 辑\"]";
    String DELETE_BUTTON_XPATH ="//span[text()=\"删 除\"]";
    String DETAIL_BUTTON_XPATH ="//span[text()=\"查看详情\"]";
    String TOTAL_INFO = "//span[@class=\"ui-total\"]";
    String UP_BUTTON = "//li[contains(@title, \"上一页\")]";
    String DOWN_BUTTON = "//li[contains(@title, \"上一页\")]";
    String PAGE_COMBOBOX_CLASS = "//span[@class=\"ant-select-selection-item\"]";
    String PAGE_COMBOBOX_INDEX_0 = "//div[contains(text(), \"15 条/页\")]";
    String PAGE_COMBOBOX_INDEX_1 = "//div[contains(text(), \"30 条/页\")]";
    String PAGE_COMBOBOX_INDEX_2 = "//div[contains(text(), \"50 条/页\")]";
    String PAGE_COMBOBOX_INDEX_3 = "//div[contains(text(), \"100 条/页\")]";
    String PAGE_COMBOBOX_INDEX_4 = "//div[contains(text(), \"500 条/页\")]";

    /**索园区时返回空lists时提示*/
    String empty_description = "//p[@class=\"ant-empty-description\"]";
}
