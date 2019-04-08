# leetcode刷题打卡
接下来，坚持每周至少刷一道LeetCode题，刷题顺利按照牛客网LeetCode经典题目顺序进行，记录解题思路，与大家共享，同时也做个自我监督

#### 第一周

#### 1、树  
##### 题目：  
Given a binary tree, find its minimum depth.The minimum depth is the number of nodes along the shortest path from the root node down to the  nearest leaf node.  
##### 解题思路：  
思路一：深度优先遍历，递归遍历左右子树，遇到叶子节点时返回 1，返回过程中，比较左右子树，较小深度逐层加1。  
思路二：广度优先遍历，逐层遍历节点，遇到第一个叶子节点返回其深度；  
代码：https://github.com/ZhenxueXu/leetcode/blob/master/src/solution/MinimumDepthOfBinaryTree.java

#### 2、栈  
##### 题目：  
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are+,-,*,/. Each operand may be an integer or another expression.

Some examples:
```
["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
```
 ##### 解题思路：  
 根据栈的原理，将数字依次入栈，遇到操作符时，从栈顶弹出两个数字，计算后将结果继续入栈；  
 代码：https://github.com/ZhenxueXu/leetcode/blob/master/src/solution/EvaluteReversePolishNotation.java
 ```java
public int evalRPN(String[] tokens) {
		if (tokens == null || tokens.length == 0){
			return 0;
		}
		Stack<Integer> value = new Stack<>();
		int left = 0;
		int right = 0;
		int  result = 0;
		for (String token : tokens) {
			switch (token){
				case "+":
					right = value.pop();
					left = value.pop();
					result = left + right;
					value.push(result);
					break;
				case "-":
					right = value.pop();
					left = value.pop();
					result = left - right;
					value.push(result);
					break;
				case "*":
					right = value.pop();
					left = value.pop();
					result = left * right;
					value.push(result);
					break;
				case "/":
					right = value.pop();
					left = value.pop();
					result = left / right;
					value.push(result);
					break;
					default:
						value.push(Integer.valueOf(token));
			}
		}
		return value.pop();
	}
```

#### 第二周

#### 1、穷举  
##### 题目：  
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
##### 解题思路：  
遍历所有的点，利用两个构成一条直线，然后在遍历其余的点，判断是否在该直线上，记下在该直线上的所有点的数量，最后得到最大值返回。
```java
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
``` 

 ##### 其他解题思路(来源于网络)
 1. 只用两层循环，连续两点构成直线，再依次判断其余点是否在该直线上
 ```java
  public int maxPoints(Point[] points) {
         if(points == null || points.length<3)
                 return points.length;
             int res =0;
             for(int i=1;i<points.length;i++){
                 int count = 0;
                 long a = points[i].x;
                 long b = points[i].y;
                 long dx = a - points[i-1].x;
                 long dy = b - points[i-1].y;
                 if(dx==0 && dy==0){
                     for(int j=0;j<points.length;j++){
                         if(points[j].x==a && points[j].y==b){
                             count++;
                         }
                     }
                 }else{
                     for(int j=0;j<points.length;j++){
                         if((points[j].x-a)*dy==(points[j].y-b)*dx){
                             count++;
                         }
                     }
                 }
                 res = Math.max(res,count);
             }
             return res;
     }
 ```
 2. 使用map结构  
 需要两重循环，第一重循环遍历起始点a，第二重循环遍历剩余点b，a和b如果不重合，就可以确定一条直线。对于每个点a，构建 斜率->点数 的map。(1)b与a重合，以a起始的所有直线点数+1 (用dup统一相加)，(2)b与a不重合，a与b确定的直线点数+1
 ```java
 public int maxPoints(Point[] points) {
         int n = points.length;
         if(n < 2) return n;
          
         int ret = 0;
         for(int i = 0; i < n; i++) {
             // 分别统计与点i重合以及垂直的点的个数
             int dup = 1, vtl = 0;
             Map<Float, Integer> map = new HashMap<>();
             Point a = points[i];
              
             for(int j = 0; j < n; j++) {
                 if(i == j) continue;
                 Point b = points[j];
                 if(a.x == b.x) {
                     if(a.y == b.y) dup++;
                     else vtl++;
                 } else {
                     float k = (float)(a.y - b.y) / (a.x - b.x);
                     if(map.get(k) == null) map.put(k, 1);
                     else map.put(k, map.get(k) + 1);
                 }
             }
              
             int max = vtl;
             for(float k: map.keySet()) {
                 max = Math.max(max, map.get(k));
             }
             ret = Math.max(ret, max + dup);
         }
         return ret;
     }
 ```
#### 2、链表
##### 题目
Sort a linked list in O(n log n) time using constant space complexity.
##### 解题思路
复杂度O(nlogn)，故使用归并排序算法，首先使用快慢指针找到链表中间节点，再分别对前后段使用归并排序。
##### 代码
```java
public class SortList {

	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null){
			return head;
		}
		ListNode midNode = getMidNode(head);
		ListNode midNodeNext = midNode.next;
		midNode.next = null;
		return mergeSort(sortList(head),sortList(midNodeNext));

	}

	private ListNode getMidNode(ListNode head){
		if (head == null || head.next == null){
			return head;
		}
		ListNode slow = head, quick = head;
		while (quick.next != null && quick.next.next != null){
			slow = slow.next;
			quick = quick.next.next;
		}
		return slow;
	}

	private ListNode mergeSort(ListNode n1, ListNode n2){
		ListNode head = new ListNode(0);
		ListNode cur = head;
		while (n1 != null && n2 != null){
			if (n1.val > n2.val){
				cur.next = n2;
				n2 = n2.next;
			}else {
				cur.next = n1;
				n1 = n1.next;
			}
			cur = cur.next;
		}
		cur.next = n1 == null ? n2 : n1;
		return head.next;

	}
}
```
