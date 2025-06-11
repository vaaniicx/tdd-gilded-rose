# Gilded Rose Refactoring Kata

## Content
- [Requirements](#requirements)
- [Testing Strategy](#testing-strategy)
- [Challenges Faced](#challenges-faced)

---

## Requirements
See [Gilded Rose Requirements Specification](https://github.com/emilybache/GildedRose-Refactoring-Kata/blob/main/GildedRoseRequirements.md)

---

## Testing Strategy
To make sure the system behaves correctly, we wrote a set of unit tests that check how different types of items behave when `updateQuality()` is called.
We made sure each test checks for only one thing. This made the tests easier to read and also easier to fix if something goes wrong. 
All the tests use fixed inputs and check the output after one update.

Each test checks one specific rule or behavior from the requirements. Here's what we tested:
- **Aged Brie**:
    - We tested that its quality increases over time.
    - We tested that its quality it increases by 2 when the sell-by date has passed.
    - We tested that its quality never goes over 50.

- **Sulfuras**:
    - We tested that it never needs to be sold (sellIn does not change).
    - We tested that its quality stays the same and never decreases.

- **Backstage Passes**:
    - We tested that its quality increases by 2 when there are 10 days or fewer.
    - We tested that its quality increases by 3 when there are 5 days or fewer.
    - We tested that its quality drops to 0 after the concert.

- **Normal Items**:
    - We tested that when the sell-by date has passed, quality decreases twice as fast.

_Test framework used: **JUnit 5**_

---

## Challenges Faced
While writing the tests, we faced a few challenges:
- **Understanding item rules**: Items like Aged Brie, Sulfuras, and Backstage Passes all behave differently. It took some time to read and understand the rules, and to write the tests for them.
- **Edge cases**: We had to watch out for special situations, like when an item’s quality is already at 0 or 50, or when the sell-by date has passed.
- **Sulfuras is special**: Sulfuras is the only item that never changes. We needed to test that both `sellIn` and `quality` stayed exactly the same after an update.