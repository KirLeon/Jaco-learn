package by.mrk.kirleon.model;

import java.util.Random;

/**
 * Абстракция для ограничения верхней границы типов данных, подаваемых для сортировки.
 * Sealed ограничивает возможность наследования данного интерфейса только классам, расположенным в том же файле,
 * либо указанным после permits и находящимся в том же пакете.
 */
public sealed interface AbstractModel<T extends AbstractModel<?>>
		permits Album, Band, Cafe, City, Corporation, Laptop, Movie, Office, Smartphone, Stub, University {
	default int compareToOtherModel(T model) {
		throw new UnsupportedOperationException();
	}
	
	Random random = new Random();
}
