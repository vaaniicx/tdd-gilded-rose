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
                quality++;
            }
            sellIn--;

            if (sellIn < 0 && quality < 50) {
                quality++;
            }
        } else if (isBackstagePass) {
            if (quality < 50) {
                quality++;

                if (sellIn < 11 && quality < 50) {
                    quality++;
                }

                if (sellIn < 6 && quality < 50) {
                    quality++;
                }
            }
            sellIn--;

            if (sellIn < 0) {
                quality = 0;
            }
        } else if (!isSulfuras) {
            if (quality > 0) {
                quality--;
            }
            sellIn--;

            if (sellIn < 0 && quality > 0) {
                quality--;
            }
        }
    }
}
