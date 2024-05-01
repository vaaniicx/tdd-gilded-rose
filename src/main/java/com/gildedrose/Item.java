package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Item {

    public String name;

    public int sellIn;

    public int quality;
}
