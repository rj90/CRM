package org.pw.rafalj.crm.repository;

/**
 * Created by rjozwiak on 2016-04-26.
 */
public interface TestRepository {
    default void testSelect(int index){}

    default void testInsert(int index){}

    default void testUpdate(int index){}

    default void testDelete(int index){}
}
