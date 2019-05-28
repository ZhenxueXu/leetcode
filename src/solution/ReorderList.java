package solution;

/**
 * @author Think
 * @className ReorderList
 * @description TODO
 * @date 2019/5/20
 **/
public class ReorderList {

	public static void main(String[] args){
		ReorderList reorderList = new ReorderList();
		ListNode head = reorderList.new ListNode(1);
		head.next = reorderList.new ListNode(2);
		head.next.next = reorderList.new ListNode(3);
		head.next.next.next = reorderList.new ListNode(4);

		reorderList.reorderList(head);
	}

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
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
