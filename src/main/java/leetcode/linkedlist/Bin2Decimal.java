package leetcode.linkedlist;

import leetcode.struct.ListNode;
import leetcode.struct.ListNodeUtil;

/**
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 * <p>
 * 请你返回该链表所表示数字的 十进制值 。
 * <p>
 * 来源：力扣（LeetCode）
 * <p>
 * 链接：https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer
 * <p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dingdong
 * @since 2021/4/23
 */
public class Bin2Decimal {

    public static void main(String[] args) {
        System.out.println(getDecimalValue(ListNodeUtil.toList(1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0)));
    }

    public static int getDecimalValue(ListNode head) {
        if (head == null) {
            return 0;
        }
        ListNode newHead = head;
        ListNode current = head.next;
        newHead.next = null;
        while (current != null) {
            ListNode oldHead = newHead;
            newHead = current;
            current = current.next;
            newHead.next = oldHead;
        }
        int ret = 0;
        int index = 0;
        current = newHead;
        while (current != null) {
            if (current.val != 0) {
                ret += (1 << index);
            }
            index++;
            current = current.next;
        }
        return ret;
    }
}
