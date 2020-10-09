package bean;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public List<Integer> toList() {
        List<Integer> res = new ArrayList<>();
        ListNode root = this;
        while (root != null) {
            res.add(root.val);
            root = root.next;
        }
        return res;
    }
}
