package com.ymm.ebatis.core.builder;

import com.ymm.ebatis.core.annotation.MatchAll;
import com.ymm.ebatis.core.meta.ConditionMeta;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author 章多亮
 * @since 2020/1/7 9:29
 */
public class MatchAllQueryBuilderFactory extends AbstractQueryBuilderFactory<MatchAllQueryBuilder, MatchAll> {
    public static final MatchAllQueryBuilderFactory INSTANCE = new MatchAllQueryBuilderFactory();

    private MatchAllQueryBuilderFactory() {
    }

    @Override
    protected MatchAllQueryBuilder doCreate(ConditionMeta<?> conditionMeta, Object condition) {
        return QueryBuilders.matchAllQuery();
    }
}
