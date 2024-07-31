package game.controllers;

import java.util.ArrayList;
import java.util.List;

import game.models.Entity;
import game.models.Point;

public class NpcController {
    // TODO this shares a lot of code with enemy, should be in a shared class or such
    public static Point getBestNextPoint(Entity e, Entity e1) {
        System.out.println("FAR:" + distanceBetweenTwoPoints(e.point, e1.point));

        int max = Integer.MIN_VALUE;
        Point maxPoint = null;
        for(Point p: getAllAvailablePoints(e)) {
            int distance = distanceBetweenTwoPoints(p, e1.point);
            if (distance > max) {
                max = distance;
                maxPoint = p; 
            }
        
        }
        return maxPoint;
        
    }

    private static List<Point> getAllAvailablePoints(Entity e) {
        List<Point> paths = new ArrayList<>();
        int x = e.point.x;
        int y = e.point.y;
        // TODO BUG there is a bug here with the values going too high
        if (x+1 < 10) paths.add(new Point(x+1, y)); // right
        if (y+1 < 10) paths.add(new Point(x, y+1));

        if (x-1 > 0) paths.add(new Point(x-1, y)); // left
        if (y-1 > 0) paths.add(new Point(x, y-1));
        return paths;
    }

    private static int distanceBetweenTwoPoints(Point p1, Point p2) {
        return (int) Math.sqrt(((p1.x - p2.x) * (p1.x - p2.x)) + ((p1.y - p2.y) * (p1.y - p2.y)));
    }
}
