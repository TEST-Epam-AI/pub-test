package com.nguyen.app;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

public class MockitoTest {

    @Test
    public void getListSize() {
        List list = mock(List.class);
        when(list.size()).thenReturn(1);
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void getListSize_returnMultiValues() {
        List list = mock(List.class);
        when(list.size()).thenReturn(1).thenReturn(2);
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void getListItem() {
        List list = mock(List.class);
        when(list.get(anyInt())).thenReturn(1);
        assertThat(list.get(0)).isEqualTo(1);
    }

    @Test
    public void getListItem_returnMultiValues() {
        List list = mock(List.class);
        when(list.get(anyInt())).thenReturn("abc");
        assertThat(list.get(0)).isEqualTo("abc");
        assertThat(list.get(1)).isEqualTo("abc");
    }

    @Test
    public void getListSize_2() {
        List list = mock(List.class, "myListttttttttttttttttttttttttttttttttt");
        when(list.add(anyString())).thenReturn(false);
        list.add(RandomStringUtils.randomAlphabetic(6));
        assertThatThrownBy(() -> verify(list, times(2)).add(anyString()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Messageeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    }
}
