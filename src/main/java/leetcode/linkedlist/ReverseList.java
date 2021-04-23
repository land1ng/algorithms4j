package leetcode.linkedlist;

import leetcode.struct.ListNode;
import leetcode.struct.ListNodeUtil;

/**
 * 输入: 1->2->3->4->5->NULL
 * <p>
 * 输出: 5->4->3->2->1->NULL
 * <p>
 * 进阶:
 * <p>
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * @author dingdong
 * @since 2021/4/21
 */
public class ReverseList {

    public static void main(String[] args) {
        System.out.println(reverseList1(ListNodeUtil.toList(1, 2, 3, 4, 5)));
    }

    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head;
        ListNode current = head.next;
        newHead.next = null;
        while (current != null) {
            ListNode oldHead = newHead;
            newHead = current;
            current = current.next;
            newHead.next = oldHead;
        }
        return newHead;
    }
}
