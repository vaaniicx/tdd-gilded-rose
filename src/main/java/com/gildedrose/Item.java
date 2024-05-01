package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Item {
    private static final int MAXIMUM_QUALITY = 50;
    private static final int LOWEST_QUALITY = 0;

    private String name;

    private int sellIn;

    private int quality;

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
                quality = LOWEST_QUALITY;
            }
        } else {
            decreaseQualityOfRemaining();
        }
    }

    private void decreaseQualityOfRemaining() {
        if (canDecreaseQuality()) {
            decreaseQuality();
        }

        if (sellIn < 0 && canDecreaseQuality()) {
            decreaseQuality();
        }
    }

    private void increaseQualityOfBackstagePass() {
        if (canImproveQuality()) {
            increaseQuality();

            if (sellIn <= 10 && canImproveQuality()) {
                increaseQuality();
            }

            if (sellIn <= 5 && canImproveQuality()) {
                increaseQuality();
            }
        }
    }

    private void increaseQualityOfAgedBrie() {
        if (canImproveQuality()) {
            increaseQuality(); // Aged Brie increases in quality
        }

        if (sellIn < 0 && canImproveQuality()) {
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

    private boolean canImproveQuality() {
        return quality < MAXIMUM_QUALITY;
    }

    private boolean canDecreaseQuality() {
        return quality > LOWEST_QUALITY;
    }
}
