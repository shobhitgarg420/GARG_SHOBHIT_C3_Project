import java.sql.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime=getCurrentTime();
        int timeCompareWithOpenTime= currentTime.compareTo(this.openingTime);
        int timeCompareWithCloseTime= currentTime.compareTo(this.closingTime);

        if (timeCompareWithOpenTime >= 0 && timeCompareWithCloseTime <=0){return true;}
        else return false;

        }


    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    public Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    //Functional is created to calculate the Order Value. It is following TDD approach.
    public int calculateOrderValue(List<String> searchByItemName) {
        int orderValue=0;
        Item searchItem;

        for(String itemName:searchByItemName) {
            searchItem = findItemByName(itemName);
            orderValue = orderValue + searchItem.getPrice();
        }
        return orderValue;

    }
}
