package leetcode.linkedlist;

import leetcode.struct.ListNode;
import leetcode.struct.ListNodeUtil;

/**
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * <p>
 * 返回同样按升序排列的结果链表。
 *
 * @author dingdong
 * @since 2021/4/22
 */
public class RemoveDuplicateElements {

    public static void main(String[] args) {
        System.out.println(deleteDuplicates(ListNodeUtil.toList()));
        System.out.println(deleteDuplicates(ListNodeUtil.toList(1)));
        System.out.println(deleteDuplicates(ListNodeUtil.toList(1, 1)));
        System.out.println(deleteDuplicates(ListNodeUtil.toList(1, 1, 2, 2, 3, 3, 3, 5)));
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = head;
        ListNode current = head;
        while (current != null) {
            if (current.val == last.val) {
                // remove current
                last.next = current.next;
            } else {
                last = current;
            }
            current = current.next;
        }
        return head;
    }
}
