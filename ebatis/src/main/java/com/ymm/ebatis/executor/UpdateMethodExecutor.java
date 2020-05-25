package com.ymm.ebatis.executor;

import com.ymm.ebatis.cluster.Cluster;
import com.ymm.ebatis.meta.MethodMeta;
import com.ymm.ebatis.request.UpdateRequestFactory;
import com.ymm.ebatis.response.ResponseExtractor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.TriFunction;

/**
 * @author 章多亮
 * @since 2019/12/19 14:54
 */
@Slf4j
class UpdateMethodExecutor extends AbstractMethodExecutor<UpdateRequest> {
    static final MethodExecutor INSTANCE = new UpdateMethodExecutor();

    private UpdateMethodExecutor() {
    }

    @Override
    protected TriFunction<Cluster, UpdateRequest, ResponseExtractor<?>, Object> getExecutor(MethodMeta method) {
        return method.getResultType()::update;
    }

    @Override
    protected UpdateRequest createActionRequest(MethodMeta method, Object[] args) {
        UpdateRequest request = UpdateRequestFactory.INSTANCE.create(MethodMeta.of(method.getMethod()), args);
        request.index(method.getIndex());

        return request;
    }
}