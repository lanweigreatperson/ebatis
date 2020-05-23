package com.ymm.ebatis.core.response;

import com.ymm.ebatis.core.common.DslUtils;
import com.ymm.ebatis.core.domain.Page;
import com.ymm.ebatis.core.meta.RequestType;
import com.ymm.ebatis.core.meta.ResultType;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.core.ResolvableType;

import java.util.List;

/**
 * @author 章多亮
 * @since 2020/1/17 16:01
 */
public abstract class AbstractSearchResponseExtractorProvider extends AbstractResponseExtractorProvider {
    protected AbstractSearchResponseExtractorProvider(ResultType... resultTypes) {
        super(RequestType.SEARCH, resultTypes);
    }

    @Override
    protected ResponseExtractor<?> getResponseExtractor(ResolvableType resolvedResultType) {
        Class<?> resultClass = resolvedResultType.resolve();

        if (SearchResponse.class == resultClass) {
            return RawSearchResponseExtractor.INSTANCE;
        } else if (Long.class == resultClass || long.class == resultClass) {
            return TotalHitsSearchResponseExtractor.INSTANCE;
        } else if (Page.class.isAssignableFrom(resultClass)) {
            return new DocumentPageExtractor<>(DocumentMapper.of(resolvedResultType.resolveGeneric(0)));
        } else if (List.class.isAssignableFrom(resultClass)) {
            return new DocumentExtractor<>(DocumentMapper.of(resolvedResultType.resolveGeneric(0)), Integer.MAX_VALUE);
        } else if (!DslUtils.isBasicClass(resultClass)) {
            return new SingleDocumentExtractor<>(DocumentMapper.of(resultClass));
        } else {
            throw new UnsupportedOperationException();
        }

    }
}
