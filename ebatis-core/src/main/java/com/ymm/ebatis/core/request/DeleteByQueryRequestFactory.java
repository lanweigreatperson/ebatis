package com.ymm.ebatis.core.request;

import com.ymm.ebatis.core.annotation.DeleteByQuery;
import com.ymm.ebatis.core.common.DslUtils;
import com.ymm.ebatis.core.meta.MethodMeta;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author duoliang.zhang
 */
public class DeleteByQueryRequestFactory extends AbstractRequestFactory<DeleteByQuery, DeleteByQueryRequest> {
    public static final DeleteByQueryRequestFactory INSTANCE = new DeleteByQueryRequestFactory();

    private DeleteByQueryRequestFactory() {
    }

    @Override
    protected void setOptionalMeta(DeleteByQueryRequest request, DeleteByQuery deleteByQuery) {
        request.setSlices(deleteByQuery.slices())
                .setRefresh(deleteByQuery.refresh())
                .setTimeout(TimeValue.parseTimeValue(deleteByQuery.timeout(), "查询删除超时"))
                .setMaxRetries(deleteByQuery.maxRetries())
                .setWaitForActiveShards(DslUtils.getActiveShardCount(deleteByQuery.waitForActiveShards()))
                .setShouldStoreResult(deleteByQuery.shouldStoreResult())
                .setBatchSize(deleteByQuery.batchSize())
                .setConflicts(deleteByQuery.conflicts());

        int maxDocs = deleteByQuery.maxDocs();
        if (maxDocs > 0) {
            request.setMaxDocs(maxDocs);
        }

        TimeValue keepAlive = DslUtils.getScrollKeepAlive(deleteByQuery.scrollKeepAlive());
        if (keepAlive != null) {
            request.setScroll(keepAlive);
        }

        request.getSearchRequest().routing(DslUtils.getRouting(deleteByQuery.routing()));
    }

    @Override
    protected DeleteByQueryRequest doCreate(MethodMeta meta, Object[] args) {
        SearchRequest searchRequest = SearchRequestFactory.INSTANCE.create(meta, args);
        SearchSourceBuilder source = searchRequest.source();
        DeleteByQueryRequest request = new DeleteByQueryRequest();
        request.getSearchRequest().source(source);
        searchRequest.source(source);
        return request;
    }
}
