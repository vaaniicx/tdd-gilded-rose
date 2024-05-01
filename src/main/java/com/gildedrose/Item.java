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

        if (isAgedBrie) {
            if (quality < 50) {
                quality = quality + 1;

                if (name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (sellIn < 11 && quality < 50) {
                        quality = quality + 1;
                    }

                    if (sellIn < 6 && quality < 50) {
                        quality = quality + 1;
                    }
                }
            }

            if (!name.equals("Sulfuras, Hand of Ragnaros")) {
                sellIn = sellIn - 1;
            }

            if (sellIn < 0 && quality < 50) {
                quality = quality + 1;
            }

        } else {
            if (!name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (quality > 0 && !name.equals("Sulfuras, Hand of Ragnaros")) {
                    quality = quality - 1;
                }

            } else {
                if (quality < 50) {
                    quality = quality + 1;

                    if (name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (sellIn < 11 && quality < 50) {
                            quality = quality + 1;
                        }

                        if (sellIn < 6 && quality < 50) {
                            quality = quality + 1;
                        }

                    }
                }
            }

            if (!name.equals("Sulfuras, Hand of Ragnaros")) {
                sellIn = sellIn - 1;
            }

            if (sellIn < 0) {
                if (!name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (quality > 0 && !name.equals("Sulfuras, Hand of Ragnaros")) {
                        quality = quality - 1;
                    }
                } else {
                    quality = 0;
                }
            }
        }
    }
}
