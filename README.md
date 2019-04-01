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
···
