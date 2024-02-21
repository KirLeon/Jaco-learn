package by.mrk.kirleon.service;

import by.mrk.kirleon.model.AbstractModel;

@SuppressWarnings({"rawtypes", "unchecked"})
public non-sealed class BubbleSort implements SortingService {
	
	@Override
	public AbstractModel[] sort(AbstractModel[] array) {
		if(array == null || array.length < 2) return array;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[j].compareToOtherModel(array[j + 1]) > 0) {
					AbstractModel swap = array[j];
					array[j] = array[j + 1];
					array[j + 1] = swap;
				}
			}
		}
		return array;
	}
}
