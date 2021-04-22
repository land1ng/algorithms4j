package leetcode.linkedlist;

import leetcode.struct.ListNode;
import leetcode.struct.ListNodeUtil;

/**
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 返回同样按升序排列的结果链表。
 * <p>
 * 来源：力扣（LeetCode）
 * <p>
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * <p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dingdong
 * @since 2021/4/22
 */
public class RemoveDuplicateElements2 {

    public static void main(String[] args) {
        System.out.println(deleteDuplicates(ListNodeUtil.toList(1, 2, 3, 3, 4, 4, 5)));
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        ListNode newTail = null;
        ListNode current = head, last = null;
        while (current != null) {
            if (((last == null) || (last.val != current.val)) && ((current.next == null) || (current.val != current.next.val))) {
                if (newHead == null) {
                    newHead = current;
                    newTail = current;
                } else {
                    newTail.next = current;
                    newTail = newTail.next;
                }
            }
            last = current;
            current = current.next;
            if (newTail != null) {
                newTail.next = null;
            }
        }
        return newHead;
    }
}
