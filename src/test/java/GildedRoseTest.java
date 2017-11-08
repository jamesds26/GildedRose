package test.java;

import static org.junit.Assert.assertEquals;

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
        
        int[] expectedQuality = new int[]{19,1,6,80,21,4};
        int[] expectedsellIn = new int[]{9,1,4,0,14,2};
        
        new GildedRose(items);
        int count = 0;
        
        for (Item i : items){
        assertEquals(expectedQuality[count], i.getQuality()); //expected to fail on last element until 'Conjured' is implemented
        assertEquals(expectedsellIn[count], i.getSellIn());
        count++;
        }
        }
	}