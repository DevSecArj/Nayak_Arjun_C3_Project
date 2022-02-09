import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Restaurant {
    private String name;
    private String location;
    private List<Item> menu = new ArrayList<Item>();
    public LocalTime openingTime;
    public LocalTime closingTime;

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime = this.getCurrentTime();
        return currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime);
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenu() {
        return this.menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    private Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }

    public int totalOrderCost(List<String> menuItems){
        List<Item> items = menu.stream().filter(i -> menuItems.contains(i.getName())).collect(Collectors.toList());
        return items.stream().mapToInt(Item::getPrice).sum();
    }

}
