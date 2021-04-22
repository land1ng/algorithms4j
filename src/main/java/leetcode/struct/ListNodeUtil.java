package leetcode.struct;

import lombok.experimental.UtilityClass;


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
}
