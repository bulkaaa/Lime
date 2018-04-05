package com.modern.codes.lime.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.Job;

/**
 * The interface Job dao.
 */
@Repository
public interface IJobDAO extends IBasicCRUDRepository<Job, String> {
    /**
     * Find by start date between list.
     *
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    List<Job> findByStartDateBetween(final Date begin, final Date end);

    /**
     * Find by end date between list.
     *
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    List<Job> findByEndDateBetween(final Date begin, final Date end);

    /**
     * Find by user id list.
     *
     * @param id the id
     * @return the list
     */
    List<Job> findByUserId(final String id);

    /**
     * Find by product id list.
     *
     * @param id the id
     * @return the list
     */
    List<Job> findByProductId(final String id);
}
