package geekbang.skiplist;

/**
 * 跳表的一种实现方法。
 * 跳表中存储的是正整数，并且存储的是不重复的。
 *
 */
public class SkipList {

    // 如果一个节点有第i层(i>=1)指针（即节点已经在第1层到第i层链表中），那么它有第(i+1)层指针的概率
    private static final float SKIPLIST_P = 0.5f;
    // 允许的最大层数（首层是0层）
    private static final int MAX_LEVEL = 16;

    // 当前最大层数
    private int levelCount = 1;

    private final Node head = new Node();  // 带头链表

    // 查询
    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    // 插入新节点
    public void insert(int value) {
        int level = randomLevel();
        Node newNode = new Node(value);
        newNode.maxLevel = level;

        // 存放每一层插入位置的上一个节点
        Node[] updates = new Node[level];

        Node p = head;
        for (int i = level - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            updates[i] = p;
        }

        for (int i = 0; i < level; i++) {
            Node update = updates[i];
            newNode.forwards[i] = update.forwards[i];
            update.forwards[i] = newNode;
        }

        // 更新最大层数
        if (level > levelCount) {
            levelCount = level;
        }
    }

    // 删除节点
    public void delete(int value) {
        Node[] updates = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            updates[i] = p;
        }

        // 判断是否存在该节点
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; i--) {
                if (updates[i].forwards[i] != null && updates[i].forwards[i].data == value) {
                    updates[i].forwards[i] = updates[i].forwards[i].forwards[i];
                }
            }
        }

        // 更新最大层数
        while (levelCount > 1 && head.forwards[levelCount] == null) {
            levelCount--;
        }

    }

    // 插入新节点时，随机生成该节点的最大层数
    private int randomLevel() {
        int level = 1;

        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL)
            level += 1;
        return level;
    }

    // 打印所有节点
    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    public class Node {
        private int data = -1;
        // 该节点所有层的下一个节点的信息，如p.forwards[i]表示节点p的第i层的下一个节点
        private Node forwards[] = new Node[MAX_LEVEL];
        // 最大层级
        private int maxLevel = 0;

        public Node() {

        }

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(3);
        skipList.insert(5);
        skipList.insert(7);
        skipList.insert(44);
        skipList.insert(2);
        skipList.insert(35);
        skipList.printAll();
    }

}
