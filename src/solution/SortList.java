package solution;

/**
 * @author Think
 * @className SortList
 * @description TODO
 * @date 2019/4/3
 **/
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


	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
