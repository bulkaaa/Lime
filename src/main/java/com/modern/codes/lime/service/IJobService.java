package com.modern.codes.lime.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.modern.codes.lime.pojo.JobPOJO;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.pojo.UserPOJO;

/**
 * The interface Job service.
 */
public interface IJobService {
    /**
     * Find all list.
     *
     * @return the list
     */
    List<JobPOJO> findAll();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);

    /**
     * Save job pojo.
     *
     * @param t the t
     * @return the job pojo
     */
    JobPOJO save(Object t);

    /**
     * Exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean exists(String id);

    /**
     * Exists boolean.
     *
     * @param t the t
     * @return the boolean
     */
    boolean exists(Object t);

    /**
     * Count long.
     *
     * @return the long
     */
    long count();

    /**
     * Equals boolean.
     *
     * @param t the t
     * @param y the y
     * @return the boolean
     */
    boolean equals(Object t, Object y);

    /**
     * Delete all.
     */
    void deleteAll();

    /**
     * Find by id job pojo.
     *
     * @param id the id
     * @return the job pojo
     */
    JobPOJO findById(String id);

    /**
     * Delete.
     *
     * @param t the t
     */
    void delete(Object t);

    /**
     * Save list.
     *
     * @param l the l
     * @return the list
     */
    List<JobPOJO> save(List l);

    /**
     * Delete.
     *
     * @param l the l
     */
    void delete(List l);

    /**
     * Find by start date between list.
     *
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    List<JobPOJO> findByStartDateBetween(Date begin, Date end);

    /**
     * Find by end date between list.
     *
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    List<JobPOJO> findByEndDateBetween(Date begin, Date end);

    /**
     * Find by user id list.
     *
     * @param id the id
     * @return the list
     */
    List<JobPOJO> findByUserId(String id);

    /**
     * Find by product id list.
     *
     * @param id the id
     * @return the list
     */
    List<JobPOJO> findByProductId(String id);

    /**
     * Filter by user list.
     *
     * @param list     the list
     * @param userName the user name
     * @return the list
     */
    static List<JobPOJO> filterByUser(final List<JobPOJO> list, final String userName) {
        return filterByUser(list.stream(), userName).collect(Collectors.toList());
    }

    /**
     * Filter by user stream.
     *
     * @param stream   the stream
     * @param userName the user name
     * @return the stream
     */
    static Stream<JobPOJO> filterByUser(final Stream<JobPOJO> stream, final String userName) {
        return stream.filter(t -> t.getPOJOUser()
                                   .getName()
                                   .equals(userName));
    }

    /**
     * Filter by user list.
     *
     * @param list the list
     * @param user the user
     * @return the list
     */
    static List<JobPOJO> filterByUser(final List<JobPOJO> list, final UserPOJO user) {
        return filterByUser(list.stream(), user).collect(Collectors.toList());
    }

    /**
     * Filter by user stream.
     *
     * @param stream the stream
     * @param user   the user
     * @return the stream
     */
    static Stream<JobPOJO> filterByUser(final Stream<JobPOJO> stream, final UserPOJO user) {
        return stream.filter(t -> t.getPOJOUser()
                                   .equals(user));
    }

    /**
     * Filter by product list.
     *
     * @param list        the list
     * @param productName the product name
     * @return the list
     */
    static List<JobPOJO> filterByProduct(final List<JobPOJO> list, final String productName) {
        return filterByProduct(list.stream(), productName).collect(Collectors.toList());
    }

    /**
     * Filter by product stream.
     *
     * @param stream      the stream
     * @param productName the product name
     * @return the stream
     */
    static Stream<JobPOJO> filterByProduct(final Stream<JobPOJO> stream, final String productName) {
        return stream.filter(t -> t.getPOJOProduct()
                                   .getName()
                                   .equals(productName));
    }

    /**
     * Filter by product list.
     *
     * @param list    the list
     * @param product the product
     * @return the list
     */
    static List<JobPOJO> filterByProduct(final List<JobPOJO> list, final ProductPOJO product) {
        return filterByProduct(list.stream(), product).collect(Collectors.toList());
    }

    /**
     * Filter by product stream.
     *
     * @param stream  the stream
     * @param product the product
     * @return the stream
     */
    static Stream<JobPOJO> filterByProduct(final Stream<JobPOJO> stream, final ProductPOJO product) {
        return stream.filter(t -> t.getPOJOProduct()
                                   .equals(product));
    }

    /**
     * Filter by date between list.
     *
     * @param list      the list
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    static List<JobPOJO> filterByDateBetween(final List<JobPOJO> list, final Date startDate, final Date endDate) {
        return filterByDateBetween(list.stream(), startDate, endDate).collect(Collectors.toList());
    }

    /**
     * Filter by date between stream.
     *
     * @param stream    the stream
     * @param startDate the start date
     * @param endDate   the end date
     * @return the stream
     */
    static Stream<JobPOJO> filterByDateBetween(final Stream<JobPOJO> stream, final Date startDate, final Date endDate) {
        return stream.filter(t -> t.getEndDate()
                                   .before(endDate) && t.getEndDate()
                                                        .after(startDate));
    }

}
