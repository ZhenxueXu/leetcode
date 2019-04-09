package solution;

import java.util.ArrayList;

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
		traversal(orderList,root);
		return orderList;
	}

	public class TreeNode {
		int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }
}
