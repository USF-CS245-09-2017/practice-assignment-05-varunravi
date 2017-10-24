// Varun Ravi
// Practice Assignment 5
// Oct 18, 2017

import java.util.*;  
import java.io.*;  
import java.lang.*;

class BST
{
	String data;
	BST left;
	BST right;
}

public class BSTree 
{
	BST root;
	int iter=0;

	public BSTree()
	{
		root = new BST();
		root.data = null;
		root.left = null;
		root.right = null;
	}

	public void insert(String s)
	{
		boolean done = false;
		BST currentNode = new BST();
		BST finalnode = new BST();
		finalnode.data = s;

		if (root.data == null)
		{
			root.data = s;
			done = true;
		}

		else if (s.compareTo(root.data) < 0)
		{
			if(nodeExists(root.left))
				currentNode = root.left;
			else
			{
				root.left = finalnode;
				done = true;
			}
		}

		else if (s.compareTo(root.data) >= 0)
		{
			if(nodeExists(root.right))
				currentNode = root.right;
			else
			{
				root.right = finalnode;
				done = true;
			}
		}

		while (done == false)
		{
			if (s.compareTo(currentNode.data) >= 0)
			{
				if (nodeExists(currentNode.right))
					currentNode = getright(currentNode);
				else
				{
					currentNode.right = finalnode;
					done = true;
				}

			}
			else
			{
				if (nodeExists(currentNode.left))
					currentNode = getleft(currentNode);
				else
				{
					currentNode.left = finalnode;
					done = true;
				}
			}
		}
	}


	// Returns the right node.
	public BST getright(BST node)
	{
		return node.right;
	}

	// Returns the left node.
	public BST getleft(BST node)
	{
		return node.left;
	}



	// returns true if node exists
	public boolean nodeExists(BST node)
	{
		return node != null;
	}

	public boolean find(String s)
	{
		boolean done = false;
		BST currentNode = new BST();
		BST finalnode = new BST();
		finalnode.data = s;

		if (root.data == null)
			return false;

		else if( s.compareTo(root.data) == 0)
			return true;

		else if (s.compareTo(root.data) < 0)
		{
			if(nodeExists(root.left))
				currentNode = root.left;
			else
				return false;
		}

		else if (s.compareTo(root.data) > 0)
		{
			if(nodeExists(root.right))
				currentNode = root.right;
			else
				return false;
		}

		while (done == false)
		{
			if (s.compareTo(currentNode.data) == 0)
				return true;
			else if (s.compareTo(currentNode.data) >= 0)
			{
				if (nodeExists(currentNode.right))
					currentNode = getright(currentNode);
				else
					return false;
			}
			else
			{
				if (nodeExists(currentNode.left))
					currentNode = getleft(currentNode);
				else
					return false;			
			}
		}
		return false;
	}

	

	public void delete(String s)
	{
		boolean done = false;
		BST currentNode = new BST();
		BST finalnode = new BST();
		finalnode.data = s;

		if (root.data == null)
			done = true;

		else if( s.compareTo(root.data) == 0)
		{
			root.data = null;
			if (nodeExists(root.left) && nodeExists(root.right))
			{
				root.data = removeSmallest(root);
				System.out.println(root.data);
			}
			else if(nodeExists(root.left))
				root = root.left;

			else if(nodeExists(root.right))
				root = root.right;
			done = true;
		}

		else if (s.compareTo(root.data) < 0)
		{
			if(nodeExists(root.left))
				currentNode = root.left;
			else
				done = true;
		}

		else if (s.compareTo(root.data) > 0)
		{
			if(nodeExists(root.right))
				currentNode = root.right;
			else
				done = true;
		}

		while (done == false)
		{
			if (s.compareTo(currentNode.data) == 0)
			{
				currentNode.data = null;
				if (nodeExists(currentNode.left) && nodeExists(currentNode.right))
				{
					currentNode.data = removeSmallest(currentNode);
				}
				else if(nodeExists(currentNode.left))
					currentNode = currentNode.left;

				else if(nodeExists(currentNode.right))
					currentNode = currentNode.right;
			}

			else if (s.compareTo(currentNode.data) > 0)
			{
				if (nodeExists(currentNode.right))
					currentNode = getright(currentNode);
				else
					done = true;
			}
			else
			{
				if (nodeExists(currentNode.left))
					currentNode = getleft(currentNode);
				else
					done = true;			
			}
		}
	}

	String removeSmallest(BST tree)
	{
		if (tree.left.left == null)
		{
			String smallest = tree.left.data;
			tree.left = tree.left.right;
			return smallest;
		}
		else
			return removeSmallest(tree.left);
	}

	// returns a space-separated copy of the contents stored in the BST in 
	// (sorted) order.
	public String toStringInOrder()
	{
		String preorder = inOrder(root);

		return preorder.substring(0, preorder.length() - 1);
	}

	public String inOrder(BST node)
	{
		String temp="";

		if (node == null)
			return "";

		temp = inOrder(node.left);

		temp = temp + node.data + " ";

		temp = temp + inOrder(node.right);

		return temp;

	}

	// returns a space-separated copy of the contents stored in the BST in 
	// pre-order: the contents of the root node, followed by the contents 
	// of the left child and the contents of the right child.
	public String toStringPreOrder()
	{
		String preorder = preOrder(root);

		return preorder.substring(0, preorder.length() - 1);
	}

	public String preOrder(BST node)
	{
		String temp;
		
		if (node == null)
			return "";

		temp = node.data + " ";

		temp = temp + preOrder(node.left);

		temp = temp + preOrder(node.right);

		return temp;

	}



	public static void main(String[] args) {
		BSTree tree = new BSTree();

		tree.insert("C");
		tree.insert("A");
		tree.insert("B");
		tree.insert("D");
		tree.delete("D");
		System.out.println(tree.find("his"));
		System.out.println(tree.find("hs"));
		System.out.println(tree.find("hi"));
	}

}
