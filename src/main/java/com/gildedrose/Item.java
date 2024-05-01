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

        if (isAgedBrie) {
            if (quality < 50) {
                quality = quality + 1;

                if (isBackstagePass) {
                    if (sellIn < 11 && quality < 50) {
                        quality = quality + 1;
                    }

                    if (sellIn < 6 && quality < 50) {
                        quality = quality + 1;
                    }
                }
            }

            if (!name.equals(ItemType.SULFURAS.getName())) {
                sellIn = sellIn - 1;
            }

            if (sellIn < 0 && quality < 50) {
                quality = quality + 1;
            }
        } else if (isBackstagePass) {
            if (quality < 50) {
                quality = quality + 1;

                if (sellIn < 11 && quality < 50) {
                    quality = quality + 1;
                }

                if (sellIn < 6 && quality < 50) {
                    quality = quality + 1;
                }
            }

            if (!name.equals(ItemType.SULFURAS.getName())) {
                sellIn = sellIn - 1;
            }

            if (sellIn < 0) {
                quality = 0;
            }
        } else {
            if (quality > 0 && !name.equals(ItemType.SULFURAS.getName())) {
                quality = quality - 1;
            }

            if (!name.equals(ItemType.SULFURAS.getName())) {
                sellIn = sellIn - 1;
            }

            if (sellIn < 0) {
                if (quality > 0 && !name.equals(ItemType.SULFURAS.getName())) {
                    quality = quality - 1;
                }
            }
        }
    }
}
