package by.mrk.kirleon.factory;

import by.mrk.kirleon.model.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ModelFactory {
	private final Random random = new Random();
	
	private interface ModelSupplier {
		AbstractModel<?> getModel(int modelNameAttribute);
	}
	
	public AbstractModel<?>[] createModels(int n, AbstractModel<?> model) {
		
		ModelSupplier supplier = getSupplier(model);
		List<AbstractModel<?>> models = getRandomModels(n, supplier);
		
		AbstractModel<?>[] modelArray = (AbstractModel<?>[]) new AbstractModel[n];
		return models.toArray(modelArray);
	}
	
	public AbstractModel<?> createModel(AbstractModel<?> model, int modelNumber) {
		return getSupplier(model).getModel(modelNumber);
	}
	
	private ModelSupplier getSupplier(AbstractModel<?> model) {
		return switch (model) {
			case Album ignored -> modelNameNumber -> new Album(("album" + modelNameNumber));
			case Band ignored -> modelNameNumber -> new Band(("band" + modelNameNumber));
			case Cafe ignored -> modelNameNumber -> new Cafe(("cafe" + modelNameNumber));
			case City ignored -> modelNameNumber -> new City(("city" + modelNameNumber));
			case Corporation ignored -> modelNameNumber -> new Corporation(("corporation" + modelNameNumber));
			case Laptop ignored -> modelNameNumber -> new Laptop(("laptop" + modelNameNumber));
			case Movie ignored -> modelNameNumber -> new Movie(("movie" + modelNameNumber));
			case Office ignored -> modelNameNumber -> new Office(("office" + modelNameNumber));
			case Smartphone ignored -> modelNameNumber -> new Smartphone(("smartphone" + modelNameNumber));
			case University ignored -> modelNameNumber -> new University(("university" + modelNameNumber));
			case Stub ignored -> modelNameAttribute -> new Stub();
			case null -> throw new IllegalArgumentException("Model cannot be null");
		};
	}
	
	private List<AbstractModel<?>> getRandomModels(int n, ModelSupplier supplier) {
		return random.ints(n, 0, n)
				.mapToObj(supplier::getModel)
				.collect(Collectors.toList());
	}
	
	
}
