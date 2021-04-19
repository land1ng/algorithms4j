package leetcode;

import leetcode.struct.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dingdong
 * @since 2021/4/16
 */
public class LinkedListCycle {

    public static void main(String[] args) {

        ListNode tail = new ListNode(12);
        ListNode node2 = new ListNode(12, tail);
        ListNode node3 = new ListNode(12, node2);
        ListNode node4 = new ListNode(12, node3);
        ListNode node5 = new ListNode(12, node4);
        ListNode node6 = new ListNode(12, node5);
        ListNode node7 = new ListNode(12, node6);

        ListNode head = new ListNode(12, node7);

        // create cycle
        tail.next = node4;

        System.out.println(hasCycle(head));
        System.out.println(hasCycleUseHash(head));
    }

    public static boolean hasCycle(ListNode head) {
        // 处理边界
        if (head == null || head.next == null) {
            return false;
        }
        // 快慢指针
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCycleUseHash(ListNode head) {
        // 处理边界
        if (head == null || head.next == null) {
            return false;
        }
        Set<ListNode> walked = new HashSet<>();
        for (ListNode current = head; current != null; current = current.next) {
            if (walked.contains(current)) {
                return true;
            }
            walked.add(current);
        }
        return false;
    }

}
