package com.gildedrose;

import java.util.List;

class GildedRose {
    private final List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        items.forEach(Item::updateItem);
    }
}
