package com.gildedrose;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class GildedRose {
    private final List<Item> items;

    public void updateQuality() {
        items.forEach(Item::updateItem);
    }
}
