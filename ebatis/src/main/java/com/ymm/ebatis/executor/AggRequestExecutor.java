package com.ymm.ebatis.executor;

import com.ymm.ebatis.request.RequestFactory;
import com.ymm.ebatis.session.ClusterSession;
import org.elasticsearch.action.search.SearchRequest;

/**
 * @author 章多亮
 * @since 2020/1/2 20:22
 */
class AggRequestExecutor extends AbstractRequestExecutor<SearchRequest> {
    static final AggRequestExecutor INSTANCE = new AggRequestExecutor();

    private AggRequestExecutor() {
    }

    @Override
    protected RequestFactory<SearchRequest> getRequestFactory() {
        return RequestFactory.agg();
    }

    @Override
    protected RequestAction<SearchRequest> getRequestAction(ClusterSession session) {
        return session::searchAsync;
    }
}
