package com.levelup.services.demo.dao.core.model;

public enum Section {

    Beauty(1, "Beauty", "�������"),
    Health(2, "Health", "��������"),
    Law(3, "Law services", "����������� ������"),
    Tourism(4, "Tourism", "������"),
    Animals(5, "Services for animals", "������ ��� ��������");

    private long id;
    private String enLabel;
    private String ruLabel;

    Section(long id, String enLabel, String ruLabel) {
        this.id = id;
        this.enLabel = enLabel;
        this.ruLabel = ruLabel;
    }

    public long getId() {
        return id;
    }

    public String getEnLabel() {
        return enLabel;
    }

    public String getRuLabel() {
        return ruLabel;
    }
}
