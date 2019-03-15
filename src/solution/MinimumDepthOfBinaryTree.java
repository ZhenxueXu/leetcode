package solution;


import javax.swing.tree.TreeNode;
import java.util.LinkedList;

/**
 * Question:
 * Given a binary tree, find its minimum depth.The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class MinimumDepthOfBinaryTree {

	public static void main(String[] args){
		MinimumDepthOfBinaryTree solution = new MinimumDepthOfBinaryTree();
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(1);
		//root.right = new TreeNode(2);
		System.out.println(solution.runDFS(root));
	}

	//思路一：深度优先遍历，递归遍历左右子树，遇到叶子节点返回，比较左右子树中找的叶子节点的最小深度，返回
	public int run(TreeNode root) {
		if(root==null) {
			return 0;
		}
		if(root.left==null && root.right==null) {
			return 1;
		}
		int l=run(root.left);
		int r=run(root.right);
		if(l==0||r==0){ return 1+r+l;}
		return 1+Math.min(l,r);
	}

	//思路二：广度优先遍历，遇到叶子节点返回深度
	public int runDFS(TreeNode root){
		if (root == null){return 0;}
		LinkedList<TreeNode> linkedList = new LinkedList<>();
		root.val = 1;
		linkedList.add(root);
		while (!linkedList.isEmpty()){
			TreeNode node = linkedList.pollFirst();
			if (node.left == null && node.right == null){return node.val;}
			if (node.left != null){
				node.left.val = node.val + 1;
				linkedList.add(node.left);
			}
			if (node.right != null ){
				node.right.val = node.val + 1;
				linkedList.add(node.right);
			}
		}
		return  0;

	}


	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
