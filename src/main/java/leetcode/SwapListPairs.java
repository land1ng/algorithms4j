package leetcode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 *
 * @author dingdong
 * @since 2021/4/19
 */
@SuppressWarnings("DuplicatedCode")
public class SwapListPairs {

    public static void main(String[] args) {
        ListNode head = new SwapListPairs().swapPairs(toList(1, 2, 3, 4, 5));
        while (true) {
            System.out.print(head.val);
            if (head.next == null) {
                break;
            }
            System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    private static ListNode toList(int... nums) {
        if (nums.length == 1) {
            return new ListNode(nums[0]);
        }
        ListNode head = new ListNode(nums[0]);
        ListNode tail = head;
        for (int i = 1; i < nums.length; i++) {
            tail.next = new ListNode(nums[i]);
            tail = tail.next;
        }
        return head;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode swapHead = null;
        ListNode lastHead = null;
        while (head != null && head.next != null) {
            if (swapHead == null) {
                lastHead = head;
                swapHead = head.next;
            } else {
                lastHead.next = head.next;
                lastHead = head;
            }
            // swap head and head.next
            ListNode temp = head.next.next;
            head.next.next = head;
            head.next = temp;
            head = temp;
        }
        return swapHead;
    }

    public static class ListNode {

        final int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
