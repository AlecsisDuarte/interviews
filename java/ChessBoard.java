/**
 * In order to reach the destination we iterate through all possible paths for our
 * chessboard piece using breath first search
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ChessBoard {
  private static int BOARD_SIZE = 30;
  static class Coordinate {
    int x, y, distance;

    Coordinate(int x, int y) { this(x, y, 0); }

    Coordinate(int x, int y, int distance) {
      this.x = x;
      this.y = y;
      this.distance = distance;
    }

    @Override
    public String toString() {
      return String.valueOf(x) + String.valueOf(y);
    }

    /**
     * We validate wether or not the end coordinate is within our reach
     * @param end the destination
     * @return If it's possible to reach in next step
     */
    boolean validMove(Coordinate end) {
      int xDiff = Math.abs(end.x - this.x);
      int yDiff = Math.abs(end.y - this.y);

      // The difference between start and end in x and y should always be a
      // combination of 1 and 2 (ex, [1,2] and [2,1]) or if the end equals to
      // the start we should return true
      return ((xDiff == 1 && yDiff == 2) || (xDiff == 2 && yDiff == 1) ||
              (xDiff == 0 && yDiff == 0));
    }

    /**
     * We move with the help of the move coordinate by adding its values
     * and the current coordinate values and we increment the distance by one
     * @return The new position
     */
    Coordinate move(Coordinate move) {
      Coordinate newCoordinate =
          new Coordinate(x + move.x, y + move.y, distance + 1);
      return newCoordinate;
    }

    /**
     * Validate whether or not the piece is inside the board
     * @return If it's inside the board
     */
    boolean isInsideTheBoard() {
      return !(x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE);
    }
  }

  private final static Coordinate[] moves = new Coordinate[] {
      new Coordinate(-1, 2),  new Coordinate(1, 2),  new Coordinate(2, 1),
      new Coordinate(2, -1),  new Coordinate(1, -2), new Coordinate(-1, -2),
      new Coordinate(-2, -1), new Coordinate(-2, 1)};

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Coordinate start = new Coordinate(in.nextInt(), in.nextInt());
    Coordinate end = new Coordinate(in.nextInt(), in.nextInt());

    int steps = shortestPath(start, end);
    System.out.println(steps);
    in.close();
  }

  public static int shortestPath(Coordinate start, Coordinate end) {
    HashSet<String> visited = new HashSet<>();
    visited.add(start.toString());
    return shortestPath(start, end, visited, 0);
  }

  public static int shortestPath(Coordinate start, Coordinate end,
                                 HashSet<String> visited, int steps) {
    // Validate that the pieces are inside the board
    if (!start.isInsideTheBoard() || !end.isInsideTheBoard()) {
      return -1;
    }

    Queue<Coordinate> paths = new LinkedList<>();
    paths.add(start);

    while (!paths.isEmpty()) {
      Coordinate p = paths.remove();

      if (p.validMove(end)) {
          //We add the final move to the end
        return p.distance + 1;
      }

      paths.addAll(getAllPaths(p, visited));
    }

    return Integer.MAX_VALUE;
  }

  /**
   * We check all 8 possible moves and add all of them that are inside the board and we haven't
   * visited
   * @param start Starting position
   * @param visited All visited coordinates
   * @return A list of all the possible paths
   */
  private static ArrayList<Coordinate> getAllPaths(Coordinate start,
                                                   HashSet<String> visited) {
    ArrayList<Coordinate> paths = new ArrayList<>();
    for (Coordinate move : moves) {
      Coordinate next = start.move(move);

      if (next.isInsideTheBoard() && visited.add(next.toString())) {
        paths.add(next);
      }
    }

    return paths;
  }
}