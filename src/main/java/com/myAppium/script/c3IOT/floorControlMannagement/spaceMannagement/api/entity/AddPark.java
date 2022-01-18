package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity;

public class AddPark {
    
    public String address;
    public String city;
    public String contactName;
    public String contactPhone;
    public Integer createBy;
    public String createByName;
    public String createTime;
    public String district;
    public Integer id;
    /**是否删除：0已删除，1未删除*/
    public Integer isDeleted;
    public Number latitude;
    public Number longitude;
    public Integer modifyBy;
    public String modifyByName;
    public String modifyTime;
    public String parkName;
    public String province;
    // 	状态：1启用，0禁用
    public Integer state;
    public String tenantCode;
    public String tenantName;

    @Override
    public String toString() {
        return "AddPark{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", createBy=" + createBy +
                ", createByName='" + createByName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", district='" + district + '\'' +
                ", id=" + id +
                ", isDeleted=" + isDeleted +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", modifyBy=" + modifyBy +
                ", modifyByName='" + modifyByName + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", parkName='" + parkName + '\'' +
                ", province='" + province + '\'' +
                ", state=" + state +
                ", tenantCode='" + tenantCode + '\'' +
                ", tenantName='" + tenantName + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public AddPark setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddPark setCity(String city) {
        this.city = city;
        return this;
    }

    public String getContactName() {
        return contactName;
    }

    public AddPark setContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public AddPark setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public AddPark setCreateBy(Integer createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getCreateByName() {
        return createByName;
    }

    public AddPark setCreateByName(String createByName) {
        this.createByName = createByName;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public AddPark setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public AddPark setDistrict(String district) {
        this.district = district;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public AddPark setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public AddPark setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public Number getLatitude() {
        return latitude;
    }

    public AddPark setLatitude(Number latitude) {
        this.latitude = latitude;
        return this;
    }

    public Number getLongitude() {
        return longitude;
    }

    public AddPark setLongitude(Number longitude) {
        this.longitude = longitude;
        return this;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public AddPark setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
        return this;
    }

    public String getModifyByName() {
        return modifyByName;
    }

    public AddPark setModifyByName(String modifyByName) {
        this.modifyByName = modifyByName;
        return this;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public AddPark setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getParkName() {
        return parkName;
    }

    public AddPark setParkName(String parkName) {
        this.parkName = parkName;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public AddPark setProvince(String province) {
        this.province = province;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public AddPark setState(Integer state) {
        this.state = state;
        return this;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public AddPark setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
        return this;
    }

    public String getTenantName() {
        return tenantName;
    }

    public AddPark setTenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }
}
