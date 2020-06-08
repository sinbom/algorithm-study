package common;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public abstract class CommonTest {

    protected int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static class ArgsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of((Object) new int[]{10, 6, 4, 5, 8, 2, 1, 7, 9, 3}),
                    Arguments.of((Object) new int[]{1, 8, 4, 3, 6, 2, 10, 9, 7, 5}),
                    Arguments.of((Object) new int[]{5, 8, 10, 4, 3, 7, 1, 2, 9, 6}),
                    Arguments.of((Object) new int[]{1, 6, 2, 7, 10, 8, 4, 9, 5, 3}),
                    Arguments.of((Object) new int[]{3, 6, 8, 5, 7, 4, 1, 2, 9, 10})
            );
        }
    }

}
