package leetcode.struct;

import lombok.Getter;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author dingdong
 * @since 2021/4/19
 */
@Getter
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
        Iterator<ListNode> it = iterator();
        Iterable<String> stringIt = () -> new Iterator<>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public String next() {
                return String.valueOf(it.next().val);
            }
        };
        return String.join(" -> ", stringIt);
    }
}
