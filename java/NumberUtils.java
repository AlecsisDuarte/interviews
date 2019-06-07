import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class NumberUtils {
    public Integer findLonelyNumber(ArrayList<Integer> numbers) {
        final HashSet<Integer> numSet = new HashSet<Integer>(numbers.size());
        for (int index = 0; index < numbers.size(); index++) {
            final int number = numbers.get(index);
            if (numSet.contains(number)) {
                numSet.remove(number);
            } else {
                numSet.add(number);
            }
        }
        Iterator<Integer> iterator = numSet.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }

}