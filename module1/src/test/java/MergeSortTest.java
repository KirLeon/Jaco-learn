import by.mrk.kirleon.factory.ModelFactory;
import by.mrk.kirleon.model.*;
import by.mrk.kirleon.service.MergeSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SuppressWarnings({"rawtypes", "unchecked"})
public class MergeSortTest {
	
	private final Random random = new Random();
	private final ModelFactory factory = new ModelFactory();
	private static AbstractModel<?> model;
	private static boolean isOrderAscending;
	private final MergeSort sortingService = Mockito.spy(new MergeSort());
	
	@BeforeAll
	public static void setup() {
		String modelName = System.getProperty("model");
		if (modelName == null || modelName.length() < 3) throw new RuntimeException("Please pass correct model value");
		model = switch (modelName.toLowerCase()) {
			case "album" -> {
				isOrderAscending = false;
				yield new Album("STUB", 0);
			}
			case "cafe" -> {
				isOrderAscending = true;
				yield new Cafe("STUB", 0);
			}
			case "band" -> {
				isOrderAscending = true;
				yield new Band("STUB", 0);
			}
			case "city" -> {
				isOrderAscending = false;
				yield new City("STUB", 0);
			}
			case "corp", "corporation" -> {
				isOrderAscending = true;
				yield new Corporation("STUB", 0);
			}
			case "lap", "laptop" -> {
				isOrderAscending = false;
				yield new Laptop("STUB", 0);
			}
			case "movie" -> {
				isOrderAscending = false;
				yield new Movie("STUB", 0);
			}
			case "office" -> {
				isOrderAscending = true;
				yield new Office("STUB", 0);
			}
			case "phone", "smartphone" -> {
				isOrderAscending = false;
				yield new Smartphone("STUB", 0);
			}
			case "uni", "university" -> {
				isOrderAscending = true;
				yield new University("STUB", 0);
			}
			case "stub" -> {
				System.out.println("Working in a test mode");
				isOrderAscending = true;
				yield new Stub(0);
			}
			case null, default -> throw new RuntimeException("Please pass correct model value");
		};
	}
	
	
	@Test
	public void shouldNotCauseNullPointerExceptionWhenGivenNullArray() {
		// given
		AbstractModel<?>[] nullArray = null;
		
		//when
		sortingService.sort(nullArray);
		
		// assert
		Assertions.assertDoesNotThrow(() -> sortingService.sort(nullArray));
	}
	
	@Test
	public void shouldNotCallInnerMethodsWhenGivenEmptyArray() {
		// given
		AbstractModel<?>[] emptyArray = new AbstractModel[]{};
		
		// when
		sortingService.sort(emptyArray);
		
		// then
		verify(sortingService, never()).mergeSort(any(), any(), anyInt(), anyInt());
		verify(sortingService, never()).merge(any(), any(), anyInt(), anyInt(), anyInt());
		
	}
	
	@Test
	public void shouldCallInnerMethodsAtLeastFewTimesWhenGivenNonEmptyArray() {
		
		// given
		AbstractModel<?>[] array = factory.createModels(10, model);
		
		// when
		sortingService.sort(array);
		
		// then
		verify(sortingService, atLeast(3)).mergeSort(any(), any(), anyInt(), anyInt());
		verify(sortingService, atLeast(3)).merge(any(), any(), anyInt(), anyInt(), anyInt());
	}
	
	@Test
	public void shouldCompareArrayElementsInCorrectOrder() {
		//given
		AbstractModel modelToCompare = factory.createModel(model, 1);
		
		//assert
		Assertions.assertNotEquals(0, modelToCompare.compareToOtherModel(model));
		Assertions.assertEquals((modelToCompare.compareToOtherModel(model) > 0), isOrderAscending);
	}
	
	@Test
	public void shouldReturnTheSameLinkToArrayAfterSorting() {
		// given
		AbstractModel<?>[] array = factory.createModels(10, model);
		
		// when
		AbstractModel<?>[] linkToSortedArray = sortingService.sort(array);
		
		// then
		Assertions.assertSame(array, linkToSortedArray);
	}
	
	@Test
	public void shouldReturnArrayWithTheSameLengthAsInputArray() {
		// given
		AbstractModel<?>[] emptyArray = new AbstractModel[]{};
		int smallArrayLength = random.nextInt(10, 100);
		int largeArrayLength = random.nextInt(1000, 10000);
		AbstractModel<?>[] smallArray = factory.createModels(smallArrayLength, model);
		AbstractModel<?>[] largeArray = factory.createModels(largeArrayLength, model);
		
		// assert
		Assertions.assertEquals(sortingService.sort(emptyArray).length, 0);
		Assertions.assertEquals(sortingService.sort(smallArray).length, smallArray.length);
		Assertions.assertEquals(sortingService.sort(largeArray).length, largeArray.length);
	}
	
	@Test
	public void shouldReturnSortedArray() {
		
		// given
		int smallArrayLength = random.nextInt(10, 100);
		int largeArrayLength = random.nextInt(1000, 10000);
		
		AbstractModel<?>[] smallArray = factory.createModels(smallArrayLength, model);
		AbstractModel<?>[] largeArray = factory.createModels(largeArrayLength, model);
		AbstractModel<?>[] smallSortedArray = new AbstractModel[smallArrayLength];
		AbstractModel<?>[] largeSortedArray = new AbstractModel[largeArrayLength];
		
		System.arraycopy(smallArray, 0, smallSortedArray, 0, smallArrayLength);
		System.arraycopy(largeArray, 0, largeSortedArray, 0, largeArrayLength);
		
		//expected
		Comparator<AbstractModel> comparator = (AbstractModel o1, AbstractModel o2)
				-> isOrderAscending ? o1.compareToOtherModel(o2) : o2.compareToOtherModel(o1);
		Arrays.sort(smallSortedArray, comparator);
		Arrays.sort(largeSortedArray, comparator);
		
		//actual
		sortingService.sort(smallArray);
		sortingService.sort(largeArray);
		
		// assert
		Assertions.assertArrayEquals(smallSortedArray, smallArray);
		Assertions.assertArrayEquals(largeSortedArray, largeArray);
	}
}
