package LinkedList;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        int index = 1;

        while (head != null) {
            if (index < left) { // 后插
                cur.next = head;
                head = head.next;
                cur = cur.next;
                cur.next = null;

            } else if (index >= left && index <= right) { // 前插
                ListNode insertedNode = head;
                head = head.next;
                insertedNode.next = cur.next;
                cur.next = insertedNode;

            } else { // 后插
                cur = getLast(cur);
                cur.next = head; // 直接将剩余的全部后插即可
                break;
            }
            index++;

        }

        return res.next;
    }

    public ListNode getLast(ListNode cur) {
        ListNode last = cur;
        while (last.next != null) {
            last = last.next;
        }
        return last;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode res = new ReverseLinkedListII().reverseBetween(head, 2, 4);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }

    }
}
