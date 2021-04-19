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

}
