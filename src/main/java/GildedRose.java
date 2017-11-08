package main.java;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {

	private static List<Item> items = null;

	public static void main(String[] args) {
		
        System.out.println("OMGHAI!");
                
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

        new GildedRose(items);
	}
	
	public GildedRose (List<Item> items){
		updateQuality(items);
	}
	
	public void updateQuality(List<Item> items)
  {
      for (int i = 0; i < items.size(); i++)
      {
          if (("Aged Brie".equals(items.get(i).getName()))){
          	agedBrieUpdate(items.get(i));
          }
          else if(("Sulfuras, Hand of Ragnaros".equals(items.get(i).getName()))){
          	//Do nothing
          }
          else if(("Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getName()))){
          	backstagePassesUpdate(items.get(i));
          }
          else if(items.get(i).getName().contains("Conjured")){
          	standardUpdate(items.get(i), 2);
          }
          else{
          	standardUpdate(items.get(i));
          }
  }
  }
     
  public void standardUpdate(Item item, int... degradeMultiplier){
  	int multiplier = 1;
  	if (degradeMultiplier.length != 0){
  		multiplier = degradeMultiplier[0];
  	}
  	item.setSellIn(--item.sellIn);
  	if (item.sellIn < 0){
  		item.setQuality(item.quality-(multiplier*2));
  	}
  	else if (item.quality == 0 || item.quality == multiplier){
  		item.setQuality(0);
  	}
  	else{
  		item.setQuality(item.quality-multiplier);
  	}
  }
  
  public void agedBrieUpdate(Item item){
  	item.setSellIn(--item.sellIn);
  	if (item.quality < 50){
  		item.setQuality(++item.quality);
  	}
  }
     
  public void backstagePassesUpdate(Item item){
	  	item.setSellIn(--item.sellIn);
	  	if (item.sellIn < 0){
	  		item.setQuality(0);
	  	}
	  	else if (item.sellIn <= 5){
	  		item.setQuality(item.quality+3);
	  	}
	  	else if (item.sellIn <= 10){
	  		item.setQuality(item.quality+2);
	  	}
	  	else if (item.sellIn < 50){
	  		item.setQuality(++item.quality);
	  	}
	  }
}
