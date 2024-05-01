package com.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lombok.val;

class GildedRoseTest {
    private GildedRose underTest;

    @ParameterizedTest
    @MethodSource(value = "provideParameterForAgedBrieQualityIncreases")
    @DisplayName("Aged Brie increases in quality, but never exceeds maximum quality")
    void test_agedBrieQualityIncreases(int expected, int sellIn, int quality) {
        val items = Collections.singletonList(new Item(ItemType.AGED_BRIE.getName(), sellIn, quality));
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items.getFirst().getQuality())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Sulfuras never has to be sold")
    void test_sulfurasSellIn() {
        val items = Collections.singletonList(new Item(ItemType.SULFURAS.getName(), 0, 80));
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items.getFirst().getSellIn())
                .isZero();
    }

    @Test
    @DisplayName("Sulfuras never decreases in quality")
    void test_sulfurasQualityDecrease() {
        val items = Collections.singletonList(new Item(ItemType.SULFURAS.getName(), 200, 80));
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items.getFirst().getQuality())
                .isEqualTo(80);
    }

    @Test
    @DisplayName("Quality of Backstage Passes increases by 2 when there are 10 days or less")
    void test_backstagePassQualityIncreaseByTwo() {
        val items = Collections.singletonList(new Item(ItemType.BACKSTAGE_PASS.getName(), 10, 10));
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items.getFirst().getQuality())
                .isEqualTo(12);
    }

    @Test
    @DisplayName("Quality of Backstage Passes increases by 3 when there are 5 days or less")
    void test_backstagePassQualityIncreaseByThree() {
        val items = Collections.singletonList(new Item(ItemType.BACKSTAGE_PASS.getName(), 5, 10));
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items.getFirst().getQuality())
                .isEqualTo(13);
    }

    @Test
    @DisplayName("Quality of Backstage Passes drops to 0 after the concert")
    void test_backstagePassQualityDropAfterConcert() {
        val items = Collections.singletonList(new Item(ItemType.BACKSTAGE_PASS.getName(), 0, 10));
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items.getFirst().getQuality())
                .isZero();
    }

    @Test
    @DisplayName("Once the sell by date has passed, Quality degrades twice as fast")
    void test_decreaseInQualityTwiceAsFast() {
        val items = Collections.singletonList(new Item(ItemType.DEXTERITY_VEST.getName(), -1, 10));
        underTest = new GildedRose(items);

        underTest.updateQuality();

        assertThat(items.getFirst().getQuality())
                .isEqualTo(8);
    }

    static Stream<Arguments> provideParameterForAgedBrieQualityIncreases() {
        return Stream.of(
                Arguments.of(1, 1, 0), // Aged Brie increases in quality
                Arguments.of(2, 0, 0), // Aged Brie increases twice in quality
                Arguments.of(50, 200, 50) // The quality of an item cannot be higher than 50
        );
    }
}
