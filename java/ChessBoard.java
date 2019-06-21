import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ChessBoard {
    static class Coordinate {
        int x, y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.valueOf(x) + String.valueOf(y);
        }
    }

    private final static Coordinate[] moves = new Coordinate[] {
        new Coordinate(-1,2),
        new Coordinate(1,2),
        new Coordinate(2,1),
        new Coordinate(2,-1),
        new Coordinate(1,-2),
        new Coordinate(-1,-2),
        new Coordinate(-2,-1),
        new Coordinate(-2,1)
    };
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Coordinate start = new Coordinate(in.nextInt(), in.nextInt());
        Coordinate end = new Coordinate(in.nextInt(), in.nextInt());

        int steps = shortestPath(start, end);
        System.out.println(steps);
        in.close();
    }

    public static boolean validMove(Coordinate start, Coordinate end) {
        int xDiff = Math.abs(end.x - start.x);
        int yDiff = Math.abs(end.y - start.y);

        // The difference between start and end in x and y should always be a
        // combination of 1 and 2 (ex, [1,2] and [2,1]) or if the end equals to the
        // start
        // we should return true
        if (xDiff == 1 && yDiff == 2 || xDiff == 2 && yDiff == 1 || xDiff == 0 && yDiff == 0) {
            return true;
        }
        return false;
    }

    public static int shortestPath(Coordinate start, Coordinate end) {
        HashSet<String> visited = new HashSet<>();
        visited.add(start.toString());
        return shortestPath(start, end, visited, 0);
    }

    public static int shortestPath(Coordinate start, Coordinate end, HashSet<String> visited, int steps) {
        //We got out of the board
        if (start.x < 0 || start.x > 7 || start.y < 0 || start.y > 7) {
            return -1;
        } else if (validMove(start, end)) {
            return steps + 1;
        }

        ++steps;
        //We already when above the max amont of posible steps
        if (steps > 6) {
            return -1;
        }

        ArrayList<Coordinate> paths = getPaths(start, end, visited);
        int minSteps = Integer.MAX_VALUE;
        //If we only have one option we simply go through it
        //but if we have more than one we do some more validation
        if (paths.size() == 1) {
            minSteps = shortestPath(paths.get(0), end, visited, steps);
        } else {
            for (int i = 0; i < paths.size(); i++ ) {
                Coordinate next = paths.get(i);
                
                //We avoid going to the paths in the same X or Y position of the end
                //As these usually contain the longest paths
                if (next.x != end.x && next.y != end.y) {
                    visited.add(next.toString());
                    int pathSteps = shortestPath(next, end, visited, steps);
                    if (pathSteps > 0  && pathSteps < minSteps) {
                        minSteps = pathSteps;
                    }
                }
            }
        }
        
        return minSteps;
    }

    public static int distance(Coordinate start, Coordinate end) {
        return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
    }

    //We get all posible paths using start as our starting point
    public static ArrayList<Coordinate> getPaths(Coordinate start, Coordinate end, HashSet<String> visited) {
        HashMap<Integer, ArrayList<Coordinate>> pathsDistance = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;
        //We calculate all posible paths from the start
        for (Coordinate move : moves) {
            Coordinate next = new Coordinate(start.x + move.x, start.y + move.y);
            
            //We validate that we haven't gone through the next step
            if (!visited.contains(next.toString())) {
                int distance = distance(next, end);
                //We should avoid getting to the positon right next to the end to avoid
                //unnecessary steps (posible loops)
                if (distance > 1 && distance < minDistance) {
                    ArrayList<Coordinate> updatedPaths = pathsDistance.get(distance);
                    if (updatedPaths == null) {
                        updatedPaths = new ArrayList<Coordinate>();
                    }
                    updatedPaths.add(next);
                    pathsDistance.put(distance, updatedPaths);
                    minDistance = distance;
                }
            }
        }
        //We return the closest to the end and that is not 1 square next to it
        return pathsDistance.get(minDistance);
    }

}