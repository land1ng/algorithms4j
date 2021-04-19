package leetcode;

import leetcode.struct.ListNode;
import leetcode.struct.ListNodeUtil;

import java.util.HashMap;
import java.util.Map;

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
 * @author dingdong
 * @since 2021/4/19
 */
public class RemoveNthInverse {

    public static void main(String[] args) {
        System.out.println(removeNthFromEnd(ListNodeUtil.toList(1), 1));
        System.out.println(removeNthFromEnd(ListNodeUtil.toList(1, 2), 1));
        System.out.println(removeNthFromEnd(ListNodeUtil.toList(1, 2, 3, 4, 5), 2));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> indexMap = new HashMap<>();
        int index = 0;
        for (ListNode current = head; current != null; current = current.next) {
            indexMap.put(index++, current);
        }
        // 总共 index 个节点
        ListNode prev = indexMap.get(index - n - 1);
        ListNode removable = indexMap.get(index - n);
        if (prev != null) {
            prev.next = removable.next;
            return indexMap.get(0);
        } else {
            return removable.next;
        }
    }
}
