package solution;

/**
 * @author Think
 * @className MaxPointsOnALine
 * @description TODO
 * @date 2019/3/30
 **/
public class MaxPointsOnALine {

	public int maxPoints(Point[] points) {

		if (points == null || points.length < 1){
			return 0;
		}
		int maxPoins = 1;
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				int num = 2;
				// 两点构成一条直线，然后遍历其余的点是否在这条直线上
				for (int k = 0; k < points.length; k++) {
					if (k == i || k == j){
						continue;
					}
					// 两点重合时，构成的直线只有一个点，找出其余重合点便为该直线上的点数量，如{[1,1],[1,1],[1,1],[1,3]},在一条直线上的最大点数为3
					if (equal(points[i],points[j])){
						if (equal(points[i],points[k])) {
							num++;
						}
						continue;
					}
					// 两点不重合是，判断其余点是否与i，j两点在一条直线上
					if ((points[i].y - points[k].y) * (points[j].x - points[k].x) == (points[j].y - points[k].y) * (points[i].x - points[k].x)){
						num++;
					}
				}
				maxPoins = Math.max(maxPoins,num);
			}
		}
		return maxPoins;
	}

	public  boolean equal(Point p1, Point p2){
		if (p1.x == p2.x && p1.y == p2.y){
			return true;
		}
		return false;
	}

	class Point {
		int x;
		int y;
		Point() { x = 0; y = 0; }
		Point(int a, int b) { x = a; y = b; }

		@Override
		public String toString() {
			return "Point{" +
					"x=" + x +
					", y=" + y +
					'}';
		}
	}

}
