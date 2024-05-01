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
        if (isSulfuras) {
            // Sulfuras never has to be sold
            // Sulfuras never decreases in quality
            return;
        }

        decreaseSellIn();
        if (isAgedBrie) {
            increaseQualityOfAgedBrie();
        } else if (isBackstagePass) {
            increaseQualityOfBackstagePass();

            if (sellIn < 0) {
                // Quality of Backstage Passes drops to 0 after the concert
                quality = 0;
            }
        } else {
            decreaseQualityOfRemaining();
        }
    }

    private void decreaseQualityOfRemaining() {
        if (quality > 0) {
            decreaseQuality();
        }

        if (sellIn < 0 && quality > 0) {
            decreaseQuality();
        }
    }

    private void increaseQualityOfBackstagePass() {
        if (quality < 50) {
            increaseQuality();

            if (sellIn <= 10 && quality < 50) {
                increaseQuality();
            }

            if (sellIn <= 5 && quality < 50) {
                increaseQuality();
            }
        }
    }

    private void increaseQualityOfAgedBrie() {
        if (quality < 50) {
            increaseQuality(); // Aged Brie increases in quality
        }

        if (sellIn < 0 && quality < 50) {
            // Aged Brie increases even more in quality, when SellIn drops under 0
            increaseQuality();
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
