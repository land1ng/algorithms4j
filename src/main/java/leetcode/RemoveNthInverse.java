package leetcode;

import leetcode.struct.ListNode;
import leetcode.struct.ListNodeUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>s
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 * 提示：
 * <p>
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * <p>
 * 1,2,3,4,5
 *
 * 在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），
 * 它的 next 指针指向链表的头节点。这样一来，我们就不需要对头节点进行特殊的判断了。
 *
 * @author dingdong
 * @since 2021/4/19
 */
public class RemoveNthInverse {

    public static void main(String[] args) {
        System.out.println(stack(ListNodeUtil.toList(), 1));
        System.out.println(stack(ListNodeUtil.toList(1), 1));
        System.out.println(stack(ListNodeUtil.toList(1, 2), 1));
        System.out.println(stack(ListNodeUtil.toList(1, 2), 2));
        System.out.println(stack(ListNodeUtil.toList(1, 2, 3, 4, 5), 2));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 处理边界
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy, fast = head;
        // 计算快指针初始位置
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // 快慢指针距离为n，当快指针为尾节点的时候，慢指针就是待删除节点的前驱节点。
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    // 使用栈
    private static ListNode stack(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<>();
        for (ListNode current = dummy; current != null; current = current.next) {
            stack.push(current);
        }
        assert stack.size() > n;
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode node = stack.pop();
        node.next = node.next.next;
        return dummy.next;
    }
}
