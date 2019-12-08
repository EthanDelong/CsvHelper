import java.util.LinkedHashMap;
import java.util.Map;

public class InsertionSorter<TMappable extends Mappable> extends MappableSorter<TMappable>
{
    /**
     * Creates a new BubbleSorter from the input Map.
     */
    public InsertionSorter(Map<Integer, TMappable> input)
    {
        super(input);
    }

    /**
     * Sorts the Mappable object by the index column with the expected type T using BubbleSort.
     *
     * @param   input   The Map of ids and Mappable objects.
     * @param   index   The index of the column to sort.
     * @param   asc     True to sort in ascending order, false to sort in descending order.
     *
     * @return  A new Map, sorted by the specified index column.
     */
    protected <T extends Comparable<T>> LinkedHashMap<Integer, TMappable> sort(Map<Integer, TMappable> input, int index, boolean asc)
    {
        // Use our internal helper method to get a new map with just the specified column value.
        Map<Integer, T> mappedValues = getMappedColumnValues(input, index);

        // Convert mapped values to array of keys for sorting
        Integer[] sortedKeys = mappedValues.keySet().toArray(new Integer[mappedValues.size()]);

        for (int count = 1; count < sortedKeys.length; count++){
            boolean finished = false;
            int current = count;
            boolean moreToSearch = true;
            while (moreToSearch && !finished)
            {
                int currentIndex = sortedKeys[current];
                int currentSubIndex = sortedKeys[current - 1];
                T left = mappedValues.get(currentIndex);
                T right = mappedValues.get(currentSubIndex);
                if (asc ? left.compareTo(right) > 0 : left.compareTo(right) < 0)
                {
                    sortedKeys[current] = currentSubIndex;
                    sortedKeys[current-1] = currentIndex;
                    current--;
                    moreToSearch = (current != 0);
                }
                else
                    finished = true;
            }
        }
        // Bubble sort is simple, but not efficient. It costs O(N^2) to process.


        // Create our result map from the sorted keys.
        LinkedHashMap<Integer, TMappable> result = new LinkedHashMap<Integer, TMappable>(input.size());
        for(Integer key : sortedKeys)
        {
            result.put(key, input.get(key));
        }
        return result;
    }

}