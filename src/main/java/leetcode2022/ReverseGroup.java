package leetcode2022;

import leetcode.struct.ListNode;
import leetcode.struct.ListNodeUtil;

/**
 * K个一组翻转链表
 *
 * @author Ran.Ding
 * @since 2022/8/22
 */
public class ReverseGroup {

    public static void main(String[] args) {

//        ListNode oldHead = ListNodeUtil.toList(1, 2, 3, 4, 5, 6, 7, 8);
//
//        ListNode newHead = reverseGroup(oldHead, 3);
//
//        System.out.println(newHead);

//        ListNode oldHead = ListNodeUtil.toList(1, 2, 3, 4, 5, 6);
//
//        ListNode newHead = reverseGroup(oldHead, 3);
//
//        System.out.println(newHead);
//
//        ListNode oldHead = ListNodeUtil.toList(1, 2);
//
//        ListNode newHead = reverseGroup(oldHead, 3);
//
//        System.out.println(newHead);

        ListNode oldHead = ListNodeUtil.toList(1, 2, 3);

        ListNode newHead = reverseGroup(oldHead, 2);

        System.out.println(newHead);
    }

    // oldHead1 -> oldTail2
    // oldHead2 -> oldTail3
    public static ListNode reverseGroup(ListNode head, int k) {

        if (k == 1) {
            return head;
        }

        ListNode newHead = head;

        // 获取下一组的头结点
        ListNode nextHead = head;
        for (int i = 0; i < k; i++) {
            // 链表长度小于k，直接返回原链表。
            if (nextHead == null) {
                return head;
            }
            newHead = nextHead;
            nextHead = nextHead.next;
        }
        ListNode nextTail = nextHead;

        ListNode currentHead = head;
        ListNode currentTail = head;

        int len = 1;

        ListNode current = head.next;
        currentHead.next = null;
        while (current != null) {
            ListNode oldHead = currentHead;
            currentHead = current;
            current = current.next;
            currentHead.next = oldHead;
            // nextTail
            if (nextTail != null) {
                nextTail = nextTail.next;
            }
            if (++len == k) {
                len = 1;
                if (current != null) {
                    current = current.next;
                }
                if (nextTail != null) {
                    // 临时保存更下一组链表的头结点
                    ListNode newNextHead = nextTail.next;
                    // 当前组链表的尾节点指向下一组链表的尾节点(翻转后成为头结点)
                    currentTail.next = nextTail;
                    // 当前头结点和尾节点初始化到下一组链表的头结点
                    currentTail = nextHead;
                    currentHead = nextHead;
                    // 将当前组链表的尾节点next设置为null防止循环引用
                    currentHead.next = null;
                    // 移动下一组节点引用
                    nextHead = newNextHead;
                    nextTail = newNextHead;
                } else {
                    // 当前组链表的尾节点指向剩下的链表
                    currentTail.next = nextHead;
                    // 下一组不够长，退出循环
                    break;
                }
            }
        }

        return newHead;
    }
}
