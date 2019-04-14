/*
 * Copyright 2017-2017 ami.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of ami, Inc. ("Confidential Information")
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license
 * agreement you entered into with ami.
 */

package com.ami.constants;

/**
 * @author: Amit Khandelwal
 * Date: 4/17/17
 */

public class LLTest {
	private Node head;

	public LLTest() {
		this.head = null;
	}

	public void add(int data) {
		Node node = new Node(data);
		if(head == null) {
			node.setNextNode(head);
			head = node;
		}
		else {
			Node curr = head;
			while(curr.next != null) {
				curr = curr.getNextNode();
			}
			curr.setNextNode(node);
		}
	}

	public void removeEven() {
		if (this.head==null) return;

		Node prev=null, curr = head;
		while(curr != null) {
			if(curr.data%2 == 0) {
				if(prev == null) {
					Node node = curr.getNextNode();
					node.setNextNode(prev);
					prev = node;
					curr = curr.getNextNode();
				}
				else {
					Node node = curr.getNextNode();
					prev.setNextNode(node);
					curr = curr.getNextNode();
				}
				// curr = curr.getNextNode();
			}

			else{
				prev = curr;
				curr = curr.getNextNode();
			}
		}
	}

	public void print() {
		if(head == null) return;

		Node curr = head;
		while(curr != null) {
			System.out.println(curr);
			curr = curr.getNextNode();
		}
	}

	public static void main(String[] args)
	{
		LLTest h = new LLTest();
		h.add(10);
		h.add(1);
		h.add(11);
		h.add(8);
		h.removeEven();
		h.print();
	}
}

class Node {
	int data;
	Node next;

	public Node(int data) {
		this(data, null);
	}

	public Node(int data, Node next) {
		this.data = data;
		this.next = next;
	}

	public void setNextNode(Node next) {
		this.next = next;
	}

	public Node getNextNode() {
		return this.next;
	}

	@Override
	public String toString() {
		return "Data: "+this.data;
	}
}