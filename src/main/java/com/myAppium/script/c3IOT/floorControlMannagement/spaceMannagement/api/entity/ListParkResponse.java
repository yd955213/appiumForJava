package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity;

import java.util.ArrayList;

public class ListParkResponse {
    ArrayList<ParkInfo> list;
    Page page;


    public class ParkInfo {
        String id;
        String parkName;
        String tenantCode;
        String tenantName;
        String province;
        String city;
        String district;
        String address;
        Integer longitude;
        Integer latitude;
        String contactName;
        String contactPhone;
        Integer state;
        Integer isDeleted;
        String createTime;
        String createBy;
        String createByName;
        String modifyTime;
        String modifyBy;
        String modifyByName;

        @Override
        public String toString() {
            return "ParkInfo{" +
                    "id='" + id + '\'' +
                    ", parkName='" + parkName + '\'' +
                    ", tenantCode='" + tenantCode + '\'' +
                    ", tenantName='" + tenantName + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", district='" + district + '\'' +
                    ", address='" + address + '\'' +
                    ", longitude=" + longitude +
                    ", latitude=" + latitude +
                    ", contactName='" + contactName + '\'' +
                    ", contactPhone='" + contactPhone + '\'' +
                    ", state=" + state +
                    ", isDeleted=" + isDeleted +
                    ", createTime='" + createTime + '\'' +
                    ", createBy='" + createBy + '\'' +
                    ", createByName='" + createByName + '\'' +
                    ", modifyTime='" + modifyTime + '\'' +
                    ", modifyBy='" + modifyBy + '\'' +
                    ", modifyByName='" + modifyByName + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public ParkInfo setId(String id) {
            this.id = id;
            return this;
        }

        public String getParkName() {
            return parkName;
        }

        public ParkInfo setParkName(String parkName) {
            this.parkName = parkName;
            return this;
        }

        public String getTenantCode() {
            return tenantCode;
        }

        public ParkInfo setTenantCode(String tenantCode) {
            this.tenantCode = tenantCode;
            return this;
        }

        public String getTenantName() {
            return tenantName;
        }

        public ParkInfo setTenantName(String tenantName) {
            this.tenantName = tenantName;
            return this;
        }

        public String getProvince() {
            return province;
        }

        public ParkInfo setProvince(String province) {
            this.province = province;
            return this;
        }

        public String getCity() {
            return city;
        }

        public ParkInfo setCity(String city) {
            this.city = city;
            return this;
        }

        public String getDistrict() {
            return district;
        }

        public ParkInfo setDistrict(String district) {
            this.district = district;
            return this;
        }

        public String getAddress() {
            return address;
        }

        public ParkInfo setAddress(String address) {
            this.address = address;
            return this;
        }

        public Integer getLongitude() {
            return longitude;
        }

        public ParkInfo setLongitude(Integer longitude) {
            this.longitude = longitude;
            return this;
        }

        public Integer getLatitude() {
            return latitude;
        }

        public ParkInfo setLatitude(Integer latitude) {
            this.latitude = latitude;
            return this;
        }

        public String getContactName() {
            return contactName;
        }

        public ParkInfo setContactName(String contactName) {
            this.contactName = contactName;
            return this;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public ParkInfo setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
            return this;
        }

        public Integer getState() {
            return state;
        }

        public ParkInfo setState(Integer state) {
            this.state = state;
            return this;
        }

        public Integer getIsDeleted() {
            return isDeleted;
        }

        public ParkInfo setIsDeleted(Integer isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public String getCreateTime() {
            return createTime;
        }

        public ParkInfo setCreateTime(String createTime) {
            this.createTime = createTime;
            return this;
        }

        public String getCreateBy() {
            return createBy;
        }

        public ParkInfo setCreateBy(String createBy) {
            this.createBy = createBy;
            return this;
        }

        public String getCreateByName() {
            return createByName;
        }

        public ParkInfo setCreateByName(String createByName) {
            this.createByName = createByName;
            return this;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public ParkInfo setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
            return this;
        }

        public String getModifyBy() {
            return modifyBy;
        }

        public ParkInfo setModifyBy(String modifyBy) {
            this.modifyBy = modifyBy;
            return this;
        }

        public String getModifyByName() {
            return modifyByName;
        }

        public ParkInfo setModifyByName(String modifyByName) {
            this.modifyByName = modifyByName;
            return this;
        }
    }

    public class Page {
        Integer pageNum;
        Integer pageSize;
        Integer pages;
        Integer total;

        @Override
        public String toString() {
            return "Page{" +
                    "pageNum=" + pageNum +
                    ", pageSize=" + pageSize +
                    ", pages=" + pages +
                    ", total=" + total +
                    '}';
        }

        public Integer getPageNum() {
            return pageNum;
        }

        public Page setPageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public Page setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Integer getPages() {
            return pages;
        }

        public Page setPages(Integer pages) {
            this.pages = pages;
            return this;
        }

        public Integer getTotal() {
            return total;
        }

        public Page setTotal(Integer total) {
            this.total = total;
            return this;
        }
    }

    @Override
    public String toString() {
        return "ListParkResponse{" +
                "list=" + list +
                ", page=" + page +
                '}';
    }

    public ArrayList<ParkInfo> getList() {
        return list;
    }

    public ListParkResponse setList(ArrayList<ParkInfo> list) {
        this.list = list;
        return this;
    }

    public Page getPage() {
        return page;
    }

    public ListParkResponse setPage(Page page) {
        this.page = page;
        return this;
    }
}
