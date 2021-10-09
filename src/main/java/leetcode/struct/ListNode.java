package leetcode.struct;

import lombok.Getter;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        return StreamSupport.stream(spliterator(), false)
                .map(ListNode::getVal)
                .map(String::valueOf)
                .collect(Collectors.joining(" -> "));
    }
}
