/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.core.api;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import java.util.Iterator;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * Tests for <code>{@link Assertions#assertThat(Iterator)}</code>.
 *
 * @author Julien Meddah
 * @author Joel Costigliola
 * @author Mikhail Mazursky
 */
public class Assertions_assertThat_with_Stream_Test {

    @Test
    public void should_create_Assert() {
        Object assertions = (AbstractStreamAssert) assertThat(newLinkedHashSet().stream());
        assertThat(assertions).isNotNull();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void should_initialise_actual() {
        Stream<String> iterator = asList("Luke", "Leia").stream();
        Stream<? extends String> actual = assertThat(iterator).actual;
        assertThat((Iterable<String>) actual).containsOnly("Leia", "Luke");
    }

    @Test
    public void should_allow_null() {
        assertThat(assertThat((Iterator<String>) null).actual).isNull();
    }

    @Test
    public void should_not_consume_iterator_when_asserting_non_null() throws Exception {
        Stream<?> iterator = mock(Stream.class);
        assertThat(iterator).isNotNull();
        verifyZeroInteractions(iterator);
    }

    @Test
    public void iterator_can_be_asserted_twice_even_though_it_can_be_iterated_only_once() throws Exception {
        Stream<String> names = asList("Luke", "Leia").stream();
        assertThat(names).containsExactly("Luke", "Leia").containsExactly("Luke", "Leia");
    }
}
