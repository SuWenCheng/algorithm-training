package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 设计哈希集合
 *
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 *
 * 实现 MyHashSet 类：
 *
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 * 
 * 示例：
 *
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 *
 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）
 * 
 *
 * 提示：
 *
 * 0 <= key <= 106
 * 最多调用 104 次 add、remove 和 contains 。
 *
 * 进阶：你可以不使用内建的哈希集合库解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-hashset
 */
@Slf4j
public class MyHashSet {
    private final static int BASE_SIZE = 1000;
    /** 链表 */
    private LinkedList[] data;
    int size;
    /** Initialize your data structure here. */
    public MyHashSet() {
        data = new LinkedList[BASE_SIZE];
        for (int i = 0; i < BASE_SIZE; i++) {
            data[i] = new LinkedList<Integer>();
        }
        size = 0;
    }

    public void add(int key) {
        int hash = hash(key);
        if (contains(key)) {
            return;
        }
        data[hash].addLast(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = hash(key);
        Iterator<Integer> iterator = data[hash].iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(key)) {
                return true;
            }
        }
        return false;
    }

    private int hash(int key) {
        return key % BASE_SIZE;
    }

    public LinkedList<Integer>[] data() {
        return data;
    }

    @Test
    public void test() {
        MyHashSet myHashSet = new MyHashSet();
        log.info(JsonHelper.toJson(myHashSet.data));
    }
}
/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */