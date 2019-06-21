import java.util.Scanner;

public class VisitDays {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numCities = in.nextInt();
        int numDays = in.nextInt();
        System.out.println(isPossible(numCities, numDays) ? "True" : "False");
        in.close();
    }

    private static boolean isPossible(int numCities, int numDays) {
        int maxDays = numDays - numCities;
        for (int day = 0; day < maxDays; day++) {
            boolean possible = isPossible(numCities, numDays, 0, day);
            if (possible) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPossible(int numCities, int numDays, int city, int day) {
        int maxDays = numDays - numCities + city;
        // If we are at the last city and this day is sunny then is possible
        // if not we would have to start from another day from the first city
        if (city == numCities - 1) {
            return isSunny(city, day);
        }
        while (day <= maxDays) {
            if (!isSunny(city, day)) {
                return false;
            }
            ++day;
            // If next city is not possible we'll try next day
            if (isPossible(numCities, numDays, city + 1, day)) {
                return true;
            }
        }

        // We return false because it means we iterate through all the possible city and
        // days
        // and never got through
        return false;
    }

    private static boolean isSunny(int city, int day) {
        //API method to get if the city in the specified day will be sunny or not
        return false;
    }

}