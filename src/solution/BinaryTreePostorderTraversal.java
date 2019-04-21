package solution;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Think
 * @className BinaryTreePostorderTraversal
 * @description TODO
 * @date 2019/4/9
 **/
public class BinaryTreePostorderTraversal {

	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> orderList = new ArrayList<>();
		traversal(orderList,root);
		return orderList;
	}

	public static void main(String args[]){
		TreeNode treeNode = new TreeNode(1);
		treeNode.left = new TreeNode(2);
		treeNode.left.left = new TreeNode(3);
		treeNode.left.right = new TreeNode(4);
		treeNode.left.right.left = new  TreeNode(5);
		BinaryTreePostorderTraversal f = new BinaryTreePostorderTraversal();
		System.out.println(f.postorderTraversalItera(treeNode));

	}

	public void traversal(ArrayList<Integer> list, TreeNode node){
		if (node == null ){
			return;
		}
		traversal(list, node.left);
		traversal(list, node.right);
		list.add(node.val);
	}

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

	public static class TreeNode {
		int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }
}
