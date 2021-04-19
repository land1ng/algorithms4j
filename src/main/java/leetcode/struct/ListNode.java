package leetcode.struct;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author dingdong
 * @since 2021/4/19
 */
public class ListNode implements Iterable<ListNode> {

    public final int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ListNode> iterator() {
        ListNode that = this;
        return new Iterator<>() {

            ListNode current = that;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public ListNode next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                ListNode next = current;
                current = current.next;
                return next;
            }
        };
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        this.forEach(node -> {
            result.append(node.val);
            if (node.next != null) {
                result.append(" -> ");
            }
        });
        return result.toString();
    }
}
