
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Party {
    private List<Character> members = new ArrayList<>();

    public void add(Character c) { members.add(c); }
    public void remove(Character c) { members.remove(c); }
    public int size() { return members.size(); }

    public List<Character> getAlive() {
        List<Character> survivors = new ArrayList<>();
        for (Character c : members) {
            if (c.isAlive()) survivors.add(c);
        }
        return survivors;
    }

    /**
     * Stretch: HP-ээр нь буурах эрэмбээр ангилсан жагсаалт буцаах.
     */
    public List<Character> sortByHp() {
        // members-ийг өөрчлөхгүйн тулд хуулбар үүсгэж байна
        List<Character> sortedList = new ArrayList<>(this.members);

        // Comparator ашиглан b.hp - a.hp (Буурах эрэмбэ)
        sortedList.sort((hero1, hero2) -> Integer.compare(hero2.getHp(), hero1.getHp()));

        return sortedList;
    }

    /**
     * Bonus: Stream API ашиглан амьд гишүүдийг тоолох
     */
    public long getAliveStream() {
        return members.stream()
                .filter(Character::isAlive)
                .count();
    }
}