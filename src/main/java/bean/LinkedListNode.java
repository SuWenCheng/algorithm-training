package bean;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 链表
 */
@AllArgsConstructor
@NoArgsConstructor
public class LinkedListNode {
    public int data;
    public LinkedListNode next;
    public LinkedListNode(int x) { data = x; }

    public List<Integer> toList() {
        List<Integer> res = new ArrayList<>();
        LinkedListNode root = this;
        while (root != null) {
            res.add(root.data);
            root = root.next;
        }
        return res;
    }
}
