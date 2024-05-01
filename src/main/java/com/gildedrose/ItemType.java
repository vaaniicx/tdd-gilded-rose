package com.gildedrose;

import lombok.Getter;

@Getter
public enum ItemType {
    AGED_BRIE("Aged Brie"),
    BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros");

    private final String name;

    ItemType(String name) {
        this.name = name;
    }
}
