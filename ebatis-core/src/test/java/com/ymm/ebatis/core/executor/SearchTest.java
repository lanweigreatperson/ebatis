package com.ymm.ebatis.core.executor;

import com.ymm.ebatis.core.meta.RequestType;
import org.junit.Test;

/**
 * @author 章多亮
 * @since 2020/5/22 16:23
 */
public class SearchTest {
    @Test
    public void search() {
        MethodExecutor executor = RequestType.SEARCH.getExecutor();
    }
}
