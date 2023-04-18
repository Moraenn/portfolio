import java.math.BigInteger;
import java.util.*;
import java.util.Stack;

class Solution {
    // 112. Path Sum
    private boolean answer = false;
    private void calculate(int curSum, TreeNode node, int targetSum){
        if (answer) return;
        if (node.left == null && node.right == null) {
            if (curSum == targetSum) answer = true;
            return;
        }
        if (node.left != null) calculate(curSum + node.left.val, node.left, targetSum);
        if (node.right != null) calculate(curSum + node.right.val, node.right, targetSum);
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root != null) calculate(root.val, root, targetSum);
        return answer;
    }

    // 20. Valid Parentheses
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            if (cur == '(' || cur == '[' || cur == '{'){
                stack.push(cur);
            } else {
                if (stack.isEmpty()) return false;
                char pr = stack.pop();
                if (cur == ')') {
                    if (pr != '(') return false;
                }
                else if (cur == ']') {
                    if (pr != '[') return false;
                }
                else if (cur == '}') {
                    if (pr != '{') return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // 206. Reverse Linked List
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode cur = head;
        ListNode next = cur.next;
        ListNode pr;
        cur.next = null;
        pr = cur;
        cur = next;
        while (cur != null) {
            next = cur.next;
            cur.next = pr;
            pr = cur;
            cur = next;
        }
        return pr;
    }


    // 21. Merge Two Sorted Lists
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        if (head.next == null) return head.val == val ? null : head;

        ListNode curAns = new ListNode();
        ListNode ans = null;
        int i = 0;
        while (head != null){
            if (head.val != val){
                i++;
                curAns.next = new ListNode(head.val);
                curAns = curAns.next;
                if (i == 1) ans = curAns;
            }
            head = head.next;
        }

        if (i == 0) return null;
        return ans;
    }

    // 21. Merge Two Sorted Lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode cur = head;
        while (list1 != null || list2 != null){
            if (list1 == null){
                cur.val = list2.val;
                list2 = list2.next;
            } else if(list2 == null){
                cur.val = list1.val;
                list1 = list1.next;
            } else if (list1.val >= list2.val){
                cur.val = list2.val;
                list2 = list2.next;
            } else{
                cur.val = list1.val;
                list1 = list1.next;
            }
            if (list1 == null && list2 == null) break;
            cur.next = new ListNode();
            cur = cur.next;
        }
        return head;
    }


    // 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> hashSet = new HashSet<>();
        ListNode cur = new ListNode(head.val);
        cur.next = head.next;
        do{
            if (hashSet.contains(cur)) return true;
            hashSet.add(cur);
        } while ((cur = cur.next) != null);
        return false;
    }
    public boolean isPalindromic(int number){
        StringBuilder sb = new StringBuilder();
        while (number > 0){
            sb.append(Integer.toString(number % 10));
            number /= 10;
        }
        int l = 0, r = sb.length() - 1;
        while (l <= r){
            if (sb.charAt(l) != sb.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
    public boolean isPalindromic(int number, int base){
        StringBuilder sb = new StringBuilder();
        while (number > 0){
            sb.append(Integer.toString(number % base));
            number /= base;
        }
        int l = 0, r = sb.length() - 1;
        while (l <= r){
            if (sb.charAt(l) != sb.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
    public int myAtoi(String s) {
        if (s.length() == 0) return 0;
        final int INT_MAX = 2147483647, INT_MIN = -2147483648;
        int sign = 1;
        if (s.charAt(0) == '+' || s.charAt(0) == '-'){
            sign = s.charAt(0) == '-' ? -1 : 1;
            s = s.substring(1);
        }
        int i = 0;
        int curNumber = 0;
        boolean hasDigits = false;
        while (i != s.length()){
            if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                hasDigits = true;
                BigInteger bigCurNumber = new BigInteger(Integer.toString(curNumber) + s.charAt(i));

                if (sign == 1){
                    if (bigCurNumber
                            .compareTo(new BigInteger(Integer.toString(INT_MAX))) != -1)
                        return INT_MAX;
                } else{
                    if (bigCurNumber
                            .compareTo(new BigInteger(Integer.toString(INT_MIN).substring(1))) != -1)
                        return INT_MIN;
                }
                curNumber = 10 * curNumber + (s.charAt(i) - '0');
            } else break;
            i++;
        }
        if (hasDigits) return curNumber * sign;
        return 0;
    }
}

// 232. Implement Queue using Stacks
class MyQueue {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    public MyQueue() { }
    public void push(int x) {
        stack1.push(x);
    }
    public int pop() {
        if (!stack2.isEmpty()){
            return stack2.pop();
        }
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
    public int peek() {
        if (!stack2.isEmpty()){
            return stack2.peek();
        }
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.peek();
    }
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}