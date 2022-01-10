package com.myAppium.script.emun;

public enum PassRecordUi {
    SEARCH_TEXT("com.das.face:id/etSearchPerson"),
    SEARCH_BUTTON("com.das.face:id/btFilter"),
    START_TIME("com.das.face:id/tvStartTime"),
    END_TIME("com.das.face:id/tvEndTime"),
    EXPORT_RECORD("com.das.face:id/tvRight")
    ;
    private String value;

    PassRecordUi(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
