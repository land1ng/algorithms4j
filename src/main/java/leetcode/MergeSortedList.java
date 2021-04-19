package leetcode;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author dingdong
 * @since 2021/4/19
 */
@SuppressWarnings("DuplicatedCode")
public class MergeSortedList {

    public static void main(String[] args) {
        ListNode head = mergeKLists(new ListNode[]{ toList(new int[]{ 1, 4, 5 }), toList(new int[]{ 1, 3, 4 }),
                                                    toList(new int[]{ 2, 6 }), });
        while (head != null) {
            System.out.print(head.val);
            if (head.next == null) {
                break;
            }
            head = head.next;
            System.out.print(" -> ");
        }
        System.out.println();
    }

    private static ListNode toList(int[] nums) {
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

    // 分治算法
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        return batchMerge(lists, 0, lists.length - 1);
    }

    private static ListNode batchMerge(ListNode[] lists, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        if (lo == hi) {
            return lists[lo];
        }
        if (lo + 1 == hi) {
            return merge(lists[lo], lists[hi]);
        } else {
            int mi = (hi - lo) / 2 + lo;
            ListNode merge1 = batchMerge(lists, lo, mi);
            ListNode merge2 = batchMerge(lists, mi + 1, hi);
            return merge(merge1, merge2);
        }
    }

    // ================================ 时间复杂度较高 ================================ //

    public static ListNode mergeKLists0(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode head = null;
        ListNode tail = null;
        while (true) {
            ListNode min = min(lists);
            if (min == null) {
                break;
            }
            if (head == null) {
                head = min;
                tail = min;
            } else {
                tail.next = min;
                tail = tail.next;
            }
        }
        return head;
    }

    private static ListNode min(ListNode[] lists) {
        ListNode min = null;
        int minIndex = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                if (min == null) {
                    min = lists[i];
                    minIndex = i;
                } else {
                    if (min.val > lists[i].val) {
                        min = lists[i];
                        minIndex = i;
                    }
                }
            }
        }
        if (min != null) {
            lists[minIndex] = min.next;
        }
        return min;
    }

    // ================================ 时间复杂度较高 ================================ //

    public static ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode merged = lists[0];
        for (int i = 1; i < lists.length; i++) {
            merged = merge(merged, lists[i]);
        }
        return merged;
    }

    private static ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head = null;
        ListNode tail = null;
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        while (true) {
            if (cur1 == null) {
                tail.next = cur2;
                break;
            }
            if (cur2 == null) {
                tail.next = cur1;
                break;
            }
            if (cur1.val < cur2.val) {
                if (head == null) {
                    head = cur1;
                    tail = cur1;
                } else {
                    tail.next = cur1;
                    tail = tail.next;
                }
                cur1 = cur1.next;
            } else {
                if (head == null) {
                    head = cur2;
                    tail = cur2;
                } else {
                    tail.next = cur2;
                    tail = tail.next;
                }
                cur2 = cur2.next;
            }
        }
        return head;
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
