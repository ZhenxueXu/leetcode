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
#### 第三周

#### 链表插入排序
###### 题目
Sort a linked list using insertion sort.

###### 解题思路
1. 一个指针指从原始列表头部开始逐步往后移指向插入元素；
2. 一个指针从头遍历已排序链表，找到插入节点，将元素插入：

###### 代码

```java
 if (head == null || head.next == null){
			return head;
		}
		ListNode first = new ListNode(0);
		first.next = head;
		ListNode cur = head.next;
		head.next = null;
		while (cur != null){
			ListNode p = first;
			while(p!=null && p.next != null){
				if (cur.val < p.next.val ){
					break;
				}
				p = p.next;
			}
			ListNode temp = cur.next;
			cur.next = p.next;
			p.next = cur;
			cur = temp;
		}
		return first.next;

```

#### 1、二叉树后序遍历
###### 题目
Given a binary tree, return the postorder traversal of its nodes' values.  
For example:
Given binary tree{1,#,2,3}, return[3,2,1].  
Note: Recursive solution is trivial, could you do it iteratively?

###### 解法一，递归遍历
递归遍历比较简单，直接上代码
###### 代码

```java
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> orderList = new ArrayList<>();
		traversal(orderList,root);
		return orderList;
	}
	
    public void traversal(ArrayList<Integer> list, TreeNode node){
		if (node == null ){
			return;
		}
		traversal(list, node.left);
		traversal(list, node.right);
		list.add(node.val);
	}

```
###### 解法二，非递归遍历
1. 将根节点如栈
2. 循环访问栈顶节点，若栈顶节点是叶子节点或是上次出栈节点的父节点，将其值放入列表中，并将该元素出栈；
3. 若右节点不为空则将右节点入栈，左节点不为空也将左节点入栈

###### 代码

```java
    public ArrayList<Integer> postorderTraversalItera(TreeNode root) {
		ArrayList<Integer> orderList = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		if (root != null){
			stack.push(root);
		}
		TreeNode head = root;
		while (!stack.empty()){
			TreeNode p = stack.peek();
			if ( (p.left == null && p.right == null) || p.right == head || p.left == head ){
				orderList.add(p.val);
				head = p;
				stack.pop();
			}else {

				if (p.right != null){
					stack.push(p.right);
				}

				if (p.left != null){
					stack.push(p.left);
				}

			}
		}
		return orderList;
	}

```
#### 第四周
#### 二叉树前序遍历
###### 题目  
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree{1,#,2,3},
return[1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?

###### 解题思路
###### 递归方法
递归方法比较简单，把左右子树当成一个完整二叉树递归遍历即可。  

代码：
```java
public ArrayList<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		traversal(list,root);
		return list;
	}

	public void traversal(ArrayList<Integer> list, TreeNode root){
		if (root == null){
			return;
		}
		list.add(root.val);
		traversal(list,root.left);
		traversal(list,root.right);

	}
```
###### 非递归方法
1. 根节点不为空，则将节点入栈
2. 将栈内节点依次出栈，直至栈空
3. 将栈顶节点出栈后加入List中
4. 若出栈节点有右节点，将右节点入栈
5. 若出栈节点有左节点，将左节点入栈

代码：
```java
public ArrayList<Integer> preOrderTraversal2(TreeNode root){
		ArrayList<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		if (root != null){
			stack.push(root);
		}
		while (!stack.empty()){
			TreeNode p = stack.pop();
			list.add(p.val);
			if (p.right != null){
				stack.push(p.right);
			}
			if (p.left != null){
				stack.push(p.left);
			}
		}
		return list;
	}
```
##### 哈希
####### 题目
Given an array of integers, find two numbers such that they add up to a specific target number.  
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9  
Output: index1=1, index2=2
###### 解题思路
1. 遍历数组，将数组值作为key，数据下标作为value，放到hashmap中
2. 遍历数组时，计算当前值与target的差值，将差值作为key，冲哈希表中取出下标，若哈希表中有值，则返回这组下标

代码：
```java
public int[] twoSum(int[] numbers, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int[] res = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			int diff =  target - numbers[i];
			Integer value = map.get(diff);
			if (value != null && value != i){
				if (value > i){
					res[0] = i + 1;
					res[1] = value + 1;
				}else {
					res[0] = value + 1;
					res[1] = i + 1;
				}
				return res;
			}
			map.put(numbers[i], i);
		}
		return res;
	}
```

#### 第五周
##### 链表
##### 题目
Given a singly linked list L: L 0→L 1→…→L n-1→L n,
reorder it to: L 0→L n →L 1→L n-1→L 2→L n-2→…

You must do this in-place without altering the nodes' values.

For example,
Given{1,2,3,4}, reorder it to{1,4,2,3}.

##### 解题思路
1. 首先利用快慢指针找到中间节点
2. 从中间节点截断链表，并将中间节点之后得链表进行反转操作
3. 同时遍历前半段链表和后半段链表，将后半段插入前半段

##### 代码
```java
public void reorderList(ListNode head) {
		if (head == null){
			return;
		}
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next !=null && fast.next.next != null){
			fast = fast.next.next;
			slow = slow.next;
		}
		if (fast == head){
			return;
		}
		ListNode h = new ListNode(0);
		h.next = slow.next;
		slow.next = null;
		ListNode cur = h.next;
		ListNode newHead = new ListNode(0);
		while (cur != null){
			ListNode temp = cur;
			cur = cur.next;
			temp.next = newHead.next;
			newHead.next = temp;
		}
		ListNode preInsert = head;
		cur = newHead.next;
		while (cur != null ){
			ListNode temp = cur;
			cur = cur.next;
			temp.next = preInsert.next;
			preInsert.next = temp;
			preInsert = preInsert.next.next;
		}
	}
```