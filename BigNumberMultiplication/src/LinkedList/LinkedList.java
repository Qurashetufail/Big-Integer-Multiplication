package LinkedList;

public class LinkedList {
	private Node head;
	private int listCount = 0;

	public boolean add(String d) {
		listCount++;
		Node newNode = new Node(d);
		if (head == null) {
			head = newNode;
			return true;
		} else {
			newNode.next = head;
			head = newNode;
			return true;
		}
	}

	public void show() {
		Node currentNode = head;
		while (currentNode.next != null) {
			System.out.print(currentNode.data + "->");
			currentNode = currentNode.next;
		}
		System.out.print(currentNode.data);
	}

	public String addNodesData() {
		String result="";
		Node currentNextNode = head.next;
		if (listCount > 1) {
			int len = head.data.length();
			while (currentNextNode.next != null) {
				int diff = len - currentNextNode.data.length();
				while (diff > 0) {
					currentNextNode.data = "0" + currentNextNode.data;
					diff--;
				}
				currentNextNode = currentNextNode.next;
			}
			int diff = len - currentNextNode.data.length();
			while (diff > 0) {
				currentNextNode.data = "0" + currentNextNode.data;
				diff--;
			}
			return addition();
		} else {
			return head.data;
		}
	}

	private String addition() {
		// System.out.println(listCount);
		String result = "";
		Node currentNode = head;
		int sum = 0, carry = 0;
		int len = currentNode.data.length();
		for (int i = len - 1; i >= 0; i--) {
			sum = 0;
			sum = carry + sum;
			carry = 0;
			currentNode = head;
			while (currentNode.next != null) {
				//System.out.println(currentNode.data);
				sum = sum + Integer.parseInt(String.valueOf(currentNode.data.charAt(i)));
				currentNode = currentNode.next;
			}

			 //System.out.println(currentNode.data);
			sum = sum + Integer.parseInt(String.valueOf(currentNode.data.charAt(i)));
			// System.out.println("Sum:"+sum);
			if (sum > 9 && i > 0) {
				carry = sum / 10;
				sum = sum % 10;
				result = sum + result;
				// System.out.println("Temp result:" + result);
			} else {
				result = sum + result;
				//System.out.println("Temp result:" + result);
			}
		}
		//System.out.println(result);
		return result;
	}
}
