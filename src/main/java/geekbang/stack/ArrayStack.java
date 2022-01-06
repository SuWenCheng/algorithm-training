package geekbang.stack;

/**
 * 基于数组的顺序栈
 */
public class ArrayStack {

    private String[] items; // 数组
    private int count;      // 栈中元素个数
    private int size;       // 栈大小

    public ArrayStack(int size) {
        count = 0;
        this.size = size;
        items = new String[size];
    }

    public boolean push(String item) {
        if (count == size) {
            return false;
        }
        items[count] = item;
        count++;
        return true;
    }

    public String pop() {
        if (count == 0) {
            return null;
        }
        String result = items[--count];
        return result;
    }
}
