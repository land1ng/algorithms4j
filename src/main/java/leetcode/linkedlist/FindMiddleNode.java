package leetcode.linkedlist;

import leetcode.struct.ListNode;
import leetcode.struct.ListNodeUtil;

/**
 * @author dingdong
 * @since 2021/4/23
 */
public class FindMiddleNode {

    public static void main(String[] a) {
        System.out.println(middleNode(ListNodeUtil.toList(1)));
        System.out.println(middleNode(ListNodeUtil.toList(1, 2, 3, 4, 5)));
        System.out.println(middleNode(ListNodeUtil.toList(1, 2, 3, 4, 5, 6)));
    }

    public static ListNode middleNode(ListNode head) {
        ListNode slow = new ListNode(0, head), fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.next;
    }
}
