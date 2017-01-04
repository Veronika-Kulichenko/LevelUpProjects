package com.levelup.services.demo.dao.core.model;

public enum Section {

    Beauty(1, "Beauty", "Красота"),
    Health(2, "Health", "Здоровье"),
    Law(3, "Law services", "Юридические услуги"),
    Tourism(4, "Tourism", "Туризм"),
    Animals(5, "Services for animals", "Услуги для животных");

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
