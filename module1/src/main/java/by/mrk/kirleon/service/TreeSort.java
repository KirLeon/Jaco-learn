package by.mrk.kirleon.service;

import by.mrk.kirleon.model.AbstractModel;

@SuppressWarnings({"rawtypes", "unchecked"})
public non-sealed class TreeSort implements SortingService {
	public static class Node {
		AbstractModel value;
		Node leftNode;
		Node rightNode;
		
		public Node(AbstractModel value) {
			this.value = value;
		}
	}
	
	@Override
	public AbstractModel[] sort(AbstractModel[] array) {
		throw new UnsupportedOperationException();
		
	}
	
	public void addNote(Node node, AbstractModel value) {
		throw new UnsupportedOperationException();
	}
	
	
	public int inorderTraversal(Node node, AbstractModel[] array, int index) {
		throw new UnsupportedOperationException();
	}
}
