package com.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    private GildedRose underTest;

    @Test
    @DisplayName("Aged Brie increases in quality")
    void test_agedBrieQualityIncrease() {
        Item[] items = { new Item("Aged Brie", 0, 0) };
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items[0].quality)
                .isEqualTo(2);
    }

    @Test
    @DisplayName("The quality of an item cannot be higher than 50")
    void test_maximumItemQuality() {
        Item[] items = { new Item("Aged Brie", 200, 50) };
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items[0].quality)
                .isEqualTo(50);
    }

    @Test
    @DisplayName("Sulfuras never has to be sold")
    void test_sulfurasSellIn() {
        Item[] items = { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items[0].sellIn)
                .isEqualTo(0);
    }

    @Test
    @DisplayName("Sulfuras never decreases in quality")
    void test_sulfurasQualityDecrease() {
        Item[] items = { new Item("Sulfuras, Hand of Ragnaros", 200, 80) };
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items[0].quality)
                .isEqualTo(80);
    }

    @Test
    @DisplayName("Quality of Backstage Passes increases by 2 when there are 10 days or less")
    void test_backstagePassQualityIncreaseByTwo() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) };
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items[0].quality)
                .isEqualTo(12);
    }

    @Test
    @DisplayName("Quality of Backstage Passes increases by 3 when there are 5 days or less")
    void test_backstagePassQualityIncreaseByThree() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items[0].quality)
                .isEqualTo(13);
    }

    @Test
    @DisplayName("Quality of Backstage Passes drops to 0 after the concert")
    void test_backstagePassQualityDropAfterConcert() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items[0].quality)
                .isEqualTo(0);
    }

    @Test
    @DisplayName("Once the sell by date has passed, Quality degrades twice as fast")
    void test_decreaseInQualityTwiceAsFast() {
        Item[] items = { new Item("+5 Dexterity Vest", -1, 10) };
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items[0].quality)
                .isEqualTo(8);
    }
}
