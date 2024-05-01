package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Item {

    public String name;

    public int sellIn;

    public int quality;

    void updateItem() {
        boolean isAgedBrie = name.equals(ItemType.AGED_BRIE.getName());
        boolean isBackstagePass = name.equals(ItemType.BACKSTAGE_PASS.getName());
        boolean isSulfuras = name.equals(ItemType.SULFURAS.getName());

        if (isAgedBrie) {
            if (quality < 50) {
                increaseQuality();
            }
            decreaseSellIn();

            if (sellIn < 0 && quality < 50) {
                increaseQuality();
            }
        } else if (isBackstagePass) {
            if (quality < 50) {
                increaseQuality();

                if (sellIn < 11 && quality < 50) {
                    increaseQuality();
                }

                if (sellIn < 6 && quality < 50) {
                    increaseQuality();
                }
            }
            decreaseSellIn();

            if (sellIn < 0) {
                quality = 0;
            }
        } else if (!isSulfuras) {
            if (quality > 0) {
                decreaseQuality();
            }
            decreaseSellIn();

            if (sellIn < 0 && quality > 0) {
                decreaseQuality();
            }
        }
    }

    private void decreaseQuality() {
        quality--;
    }

    private void increaseQuality() {
        quality++;
    }

    private void decreaseSellIn() {
        sellIn--;
    }
}
