package solution;

/**
 * @author Think
 * @className InsertionSortList
 * @description TODO
 * @date 2019/4/8
 **/
public class InsertionSortList {
	public static void main(String args[]){
		InsertionSortList insertionSortList = new InsertionSortList();
		ListNode head = new ListNode(3);
		head.next = new ListNode(22);
		head.next.next =new ListNode(1);
		ListNode result = insertionSortList.insertionSortList(head);
		System.out.println(result.val + "," + result.next.val + ","+result.next.next.val);

	}
	public ListNode insertionSortList(ListNode head) {
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
	}
	public static class ListNode {
		int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
