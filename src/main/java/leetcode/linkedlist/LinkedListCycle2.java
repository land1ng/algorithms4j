package leetcode.linkedlist;

import leetcode.struct.ListNode;
import leetcode.struct.ListNodeUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * <p>
 * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 * 进阶：你是否可以使用 O(1) 空间解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * <p>
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 * <p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dingdong
 * @since 2021/4/22
 */
public class LinkedListCycle2 {

    public static void main(String[] args) {
        ListNode junction = detectCycle(ListNodeUtil.buildCycleList(1, 3, 2, 0, -4));
        System.out.println(junction == null ? null : junction.val);
    }

    @SuppressWarnings("ConditionalBreakInInfiniteLoop")
    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public static ListNode detectCycleUseHash(ListNode head) {
        Set<ListNode> walked = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (walked.contains(current)) {
                return current;
            }
            walked.add(current);
            current = current.next;
        }
        return null;
    }
}
