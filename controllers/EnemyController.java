package game.controllers;

import java.util.ArrayList;
import java.util.List;

import game.models.Entity;
import game.models.Point;

public class EnemyController {
    public static Point getBestNextPoint(Entity e, Entity player) {
        System.out.println(distanceBetweenTwoPoints(e.point, player.point));

        int min = Integer.MAX_VALUE;
        Point minPoint = null;
        for(Point p: getAllAvailablePoints(e)) {
            int distance = distanceBetweenTwoPoints(p, player.point);
            if (distance < min) {
                min = distance;
                minPoint = p; 
            }
        }
        return minPoint;
        
    }

    private static List<Point> getAllAvailablePoints(Entity e) {
        List<Point> paths = new ArrayList<>();
        int x = e.point.x;
        int y = e.point.y;
        paths.add(new Point(x+1, y)); // right
        paths.add(new Point(x, y+1));

        paths.add(new Point(x-1, y)); // left
        paths.add(new Point(x, y-1));

        return paths;
    }

    private static int distanceBetweenTwoPoints(Point p1, Point p2) {
        return (int) Math.sqrt(((p1.x - p2.x) * (p1.x - p2.x)) + ((p1.y - p2.y) * (p1.y - p2.y)));
    }
}
