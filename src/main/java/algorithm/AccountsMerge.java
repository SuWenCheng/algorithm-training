package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 合并账户
 *
 * 给定一个列表 accounts，每个元素 accounts[i]是一个字符串列表，
 * 其中第一个元素 accounts[i][0]是名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 *
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
 * 请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
 * 一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 *
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。
 * 账户本身可以以任意顺序返回。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 输出：
 * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * 解释：
 * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。 
 * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 * 
 *
 * 提示：
 *
 * accounts的长度将在[1，1000]的范围内。
 * accounts[i]的长度将在[1，10]的范围内。
 * accounts[i][j]的长度将在[1，30]的范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/accounts-merge
 */
@Slf4j
public class AccountsMerge {

    /**
     * 并查集
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> emailToIndex = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int emailCounts = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int j = 1; j < size; j++) {
                String email = account.get(j);
                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, emailCounts++);
                    emailToName.put(email, name);
                }
            }
        }
        int[] parents = new int[emailCounts];
        for (int i = 0; i < emailCounts; i++) {
            parents[i] = i;
        }
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            for (int i = 2; i < account.size(); i++) {
                int nextIndex = emailToIndex.get(account.get(i));
                union(parents, firstIndex, nextIndex);
            }
        }
        Map<Integer, List<String>> indexToEmails = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToIndex.entrySet()){
            Integer index = find(parents, entry.getValue());
            List<String> emailList = indexToEmails.getOrDefault(index, new ArrayList<>());
            emailList.add(entry.getKey());
            indexToEmails.put(index, emailList);
        }

        for (List<String> emailList : indexToEmails.values()) {
            Collections.sort(emailList);
            String name = emailToName.get(emailList.get(0));
            List<String> merge = new ArrayList<>();
            merge.add(name);
            merge.addAll(emailList);
            result.add(merge);
        }
        return result;
    }

    private void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    private int find(int[] parent, int index) {
        while (index != parent[index]) {
            index = parent[index];
        }
        return index;
    }

    @Test
    public void test() {
        String str = "[\"John\",\"johnsmith@mail.com\",\"john_newyork@mail.com\"]";
        String str1 = "[\"John\",\"johnsmith@mail.com\",\"john00@mail.com\"]";
        String str2 = "[\"Mary\",\"mary@mail.com\"],[\"John\",\"johnnybravo@mail.com\"]";;
        List<String> stringList = Arrays.asList(str, str1, str2);
        List<List<String>> accounts = stringList.stream()
                .map(s -> JsonHelper.fromJsonToList(s, List.class, String.class))
                .collect(Collectors.toList());
        List<List<String>> lists = accountsMerge(accounts);
        log.info(JsonHelper.toJson(lists));

    }

}
