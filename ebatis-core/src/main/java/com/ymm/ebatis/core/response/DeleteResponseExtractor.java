package com.ymm.ebatis.core.response;

import org.elasticsearch.action.delete.DeleteResponse;

public interface DeleteResponseExtractor<T> extends ConcreteResponseExtractor<T, DeleteResponse> {
}
