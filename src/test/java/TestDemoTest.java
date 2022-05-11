import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;

class TestDemoTest {
	
	private TestDemo testDemo;
	
	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, Boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b))
			.isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	static Stream<Arguments> argumentsForAddPositive () {
		return Stream.of(
				Arguments.of(1, 3, 4, false),
				Arguments.of(15, 22, 37, false),
				Arguments.of(-1, 15, 14, true),
				Arguments.of(3, 3, 6, false),
				Arguments.of(0, 2, 4, true),
				Arguments.of(10, 81, 91, false),
				Arguments.of(3, 3, 6, false),
				Arguments.of(12, 0, 12, true),
				Arguments.of(53, 16, 69, false),
				Arguments.of(206, -3, 203, true));
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect () {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}

}
