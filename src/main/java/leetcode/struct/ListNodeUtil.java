package leetcode.struct;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.Objects;


/**
 * @author dingdong
 * @since 2021/4/19
 */

@UtilityClass
public class ListNodeUtil {

    @SuppressWarnings("DuplicatedCode")
    public static ListNode toList(int... nums) {
        if (nums.length == 0) {
            return null;
        }
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

    public static ListNode buildCycleList(int pos, int... nums) {
        if (nums.length == 0) {
            return null;
        }
        if (pos > nums.length - 1) {
            throw new IllegalArgumentException();
        }
        ListNode junction = null;
        int i = 0;
        ListNode head = null;
        ListNode tail = null;
        do {
            if (i == 0) {
                head = new ListNode(nums[i]);
                tail = head;
            } else {
                tail.next = new ListNode(nums[i]);
                tail = tail.next;
            }
            if (i == pos) {
                junction = tail;
            }
            i++;
        } while (i < nums.length);
        if (junction != null) {
            tail.next = junction;
        }
        return head;
    }

    public static ListNodeTuple2 buildIntersectionNodes(int[] a, int[] b, int[] c) {
        ListNode headA = toList(a);
        ListNode headB = toList(b);
        ListNode tailA = Objects.requireNonNull(findTail(headA));
        ListNode tailB = Objects.requireNonNull(findTail(headB));
        ListNode headC = toList(c);
        tailA.next = headC;
        tailB.next = headC;
        return new ListNodeTuple2(headA, headB);
    }

    public static ListNode findTail(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    @RequiredArgsConstructor
    public static class ListNodeTuple2 {

        public final ListNode headA;

        public final ListNode headB;
    }

    public static void main(String[] args) {
        ListNodeTuple2 tuple = buildIntersectionNodes(new int[]{ 1, 2, 3, 4 },
                new int[]{ 5, 6, 7, 8 },
                new int[]{ 11, 22, 33 });
        System.out.println(tuple.headA);
        System.out.println(tuple.headB);
    }
}
