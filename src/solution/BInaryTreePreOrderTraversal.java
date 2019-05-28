package solution;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Think
 * @className BInaryTreePreOrderTraversal
 * @description TODO
 * @date 2019/4/28
 **/
public class BInaryTreePreOrderTraversal {

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
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
