
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> items = new HashMap<>();
    private Map<String, ItemType> typeRegistry = new HashMap<>(); // Төрлийг бүртгэх хэсэг

    public void addItem(String name) {
        items.merge(name, 1, Integer::sum);
    }

    // Overload хийсэн addItem: Төрөлтэй нь хамт нэмэх
    public void addItem(String name, ItemType type) {
        addItem(name);
        typeRegistry.put(name, type);
    }

    public void removeItem(String name) {
        if (!items.containsKey(name)) return;
        int remaining = items.get(name) - 1;
        if (remaining <= 0) items.remove(name);
        else items.put(name, remaining);
    }

    public boolean hasItem(String name) {
        return items.getOrDefault(name, 0) > 0;
    }

    public int getCount(String name) {
        return items.getOrDefault(name, 0);
    }

    /**
     * Stretch: Төрлөөр нь бүлэглэх.
     * Жишээ нь: POTION -> [Healing Potion, Mana Potion]
     */
    public Map<ItemType, List<String>> groupByType() {
        Map<ItemType, List<String>> categoryMap = new HashMap<>();

        for (var entry : typeRegistry.entrySet()) {
            String name = entry.getKey();
            ItemType t = entry.getValue();

            // Зөвхөн одоо цүнхэнд байгаа (тоо нь 0-ээс их) барааг л бүлэглэнэ
            if (this.hasItem(name)) {
                categoryMap.computeIfAbsent(t, k -> new ArrayList<>()).add(name);
            }
        }
        return categoryMap;
    }
}