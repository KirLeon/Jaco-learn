package by.mrk.kirleon.service;

import by.mrk.kirleon.model.AbstractModel;

public sealed interface SortingService permits BubbleSort, MergeSort, QuickSort, TreeSort {
	AbstractModel<?>[] sort(AbstractModel<?>[] model);
}
