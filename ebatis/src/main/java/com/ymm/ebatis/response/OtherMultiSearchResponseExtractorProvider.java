package com.ymm.ebatis.response;

import com.ymm.ebatis.meta.ResultType;

/**
 * @author 章多亮
 * @since 2020/1/18 18:48
 */
public class OtherMultiSearchResponseExtractorProvider extends AbstractMultiSearchResponseExtractorProvider {
    public OtherMultiSearchResponseExtractorProvider() {
        super(ResultType.OTHER);
    }

    @Override
    protected boolean isWrapped() {
        return false;
    }
}