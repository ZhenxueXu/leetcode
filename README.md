# leetcode刷题打卡
接下来，坚持每周至少刷一道LeetCode题，刷题顺利按照牛客网LeetCode经典题目顺序进行，记录解题思路，与大家共享，同时也做个自我监督

#### 第一周

##### 知识点：树  
##### 题目：  
Given a binary tree, find its minimum depth.The minimum depth is the number of nodes along the shortest path from the root node down to the  nearest leaf node.  
##### 解题思路：  
思路一：深度优先遍历，递归遍历左右子树，遇到叶子节点时返回 1，返回过程中，比较左右子树，较小深度逐层加1。  
思路二：广度优先遍历，逐层遍历节点，遇到第一个叶子节点返回其深度；  
代码：https://github.com/ZhenxueXu/leetcode/blob/master/src/solution/MinimumDepthOfBinaryTree.java

##### 知识点：栈  
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
 ```
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