package com.ymm.ebatis.request;

import com.ymm.ebatis.annotation.Cat;
import com.ymm.ebatis.meta.MetaUtils;
import com.ymm.ebatis.meta.MethodMeta;

class CatCountRequestFactory extends AbstractCatRequestFactory<CatCountRequest> {
    static final CatCountRequestFactory INSTANCE = new CatCountRequestFactory();

    private CatCountRequestFactory() {
    }

    @Override
    protected void setAnnotationMeta(CatCountRequest request, Cat cat) {
        MetaUtils.findFirstElement(cat.count())
                .ifPresent(count -> request.index(count.index())
                        .format(count.format()));
    }

    @Override
    protected CatCountRequest doCreate(MethodMeta meta, Object[] args) {
        return new CatCountRequest();
    }
}
