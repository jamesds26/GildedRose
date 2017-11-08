package test.java;

import static org.junit.Assert.assertEquals;

//Test cases:
//1. whole list at provided values
//2. whole list at 0 quality 		- The Quality of an item is never negative
//3. whole list at -1 sellIn		- Once the sell by date has passed, Quality degrades twice as fast / backstage Quality drops to 0 after the concert
//4. backstage at 10 days			- Quality increases by 2 when there are 10 days or less
//5. backstage at 5 days			- and by 3 when there are 5 days or less
//6. brie & backstage at 50 quality	- The Quality of an item is never more than 50

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.java.GildedRose;
import main.java.Item;

public class GildedRoseTest {
	
	private List<Item> items = null;
	
	@Test
	public void testupdateQuality_providedValues() {
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));
        
        int[] expectedsellIn = new int[]{9,1,4,0,14,2};
        int[] expectedQuality = new int[]{19,1,6,80,21,4};
        
        new GildedRose(items);
        int count = 0;
        
        for (Item i : items){
            assertEquals(expectedsellIn[count], i.getSellIn());
        	assertEquals(expectedQuality[count], i.getQuality()); //expected to fail on last element until 'Conjured' is implemented
        	count++;
        }
	}
	
	@Test
	public void testupdateQuality_zeroQuality() {
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 0));
        //items.add(new Item("Aged Brie", 2, 0)); //not applicable (Quality increases)
        //items.add(new Item("Elixir of the Mongoose", 5, 7)); //superfluous - case covered by item 0
        //items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80)); //not applicable
        //items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)); //not applicable (Quality increases)
        items.add(new Item("Conjured Mana Cake", 3, 0));
        
        int[] expectedsellIn = new int[]{9,2};
        int[] expectedQuality = new int[]{0,0};
                
        new GildedRose(items);
        int count = 0;
        
        for (Item i : items){
        	assertEquals(expectedsellIn[count], i.getSellIn());
        	assertEquals(expectedQuality[count], i.getQuality());
        	count++;
        }
	}
	
	@Test
	public void testupdateQuality_negativeSellIn() {
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", -1, 20));
        items.add(new Item("Aged Brie", -1, 0));
        items.add(new Item("Elixir of the Mongoose", -1, 7));
        //items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80)); //not applicable
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20));
        items.add(new Item("Conjured Mana Cake", -1, 6));
        
        int[] expectedsellIn = new int[]{-2,-2,-2,-2,-2,-2};
        int[] expectedQuality = new int[]{18,1,5,0,2};
        
        new GildedRose(items);
        int count = 0;
        
        for (Item i : items){
            assertEquals(expectedsellIn[count], i.getSellIn());
        	assertEquals(expectedQuality[count], i.getQuality()); //expected to fail on last element until 'Conjured' is implemented
        	count++;
        }
	}
	
	@Test
	public void testupdateQuality_backstageAtTenDays() {
        items = new ArrayList<Item>();
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
        
        int expectedsellIn = 9;
        int expectedQuality = 22;
        
        new GildedRose(items);
        
        assertEquals(expectedsellIn, items.get(0).getSellIn());
        assertEquals(expectedQuality, items.get(0).getQuality());
	}
	
	@Test
	public void testupdateQuality_backstageAtFiveDays() {
        items = new ArrayList<Item>();
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
        
        int expectedsellIn = 4;
        int expectedQuality = 23;
        
        new GildedRose(items);
        
        assertEquals(expectedsellIn, items.get(0).getSellIn());
        assertEquals(expectedQuality, items.get(0).getQuality());
	}
	
	@Test
	public void testupdateQuality_fiftyQualityCap() {
        items = new ArrayList<Item>();
        items.add(new Item("Aged Brie", 2, 50));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49));
        
        int[] expectedsellIn = new int[]{1,14,4,9};
        int[] expectedQuality = new int[]{50,50,50,50};
                
        new GildedRose(items);
        int count = 0;
        
        for (Item i : items){
        	assertEquals(expectedsellIn[count], i.getSellIn());
        	assertEquals(expectedQuality[count], i.getQuality());
        	count++;
        }
	}
}