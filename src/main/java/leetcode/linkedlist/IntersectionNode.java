package leetcode.linkedlist;

import com.burndy.algorithms.utils.Tuple2;
import com.burndy.algorithms.utils.mark.Graceful;
import leetcode.struct.ListNode;
import leetcode.struct.ListNodeUtil;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * <image src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png" />
 * <p>
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * <p>
 * 在返回结果后，两个链表仍须保持原有的结构。
 * <p>
 * 可假定整个链表结构中没有循环。
 * <p>
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * <p>
 * 来源：力扣（LeetCode）
 * <p>
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * <p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dingdong
 * @since 2021/4/22
 */
@Graceful
public class IntersectionNode {

    public static void main(String[] args) {
        Tuple2<ListNode, ListNode> tuple = ListNodeUtil.buildIntersectionNodes(new int[]{1, 2, 3, 4},
                new int[]{5, 6, 7, 8},
                new int[]{11, 22, 33});
        System.out.println(getIntersectionNode(tuple._1(), tuple._2()));
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode aP = headA;
        ListNode bP = headB;
        // 第一轮遍历消除长度差
        // 第二轮遍历从两个链表上距离尾节点相同距离的地方开始，那么如果在到达末尾时相遇，即为交汇点。
        while (aP != bP) {
            aP = aP == null ? headB : aP.next;
            bP = bP == null ? headA : bP.next;
        }
        return aP;
    }
}
