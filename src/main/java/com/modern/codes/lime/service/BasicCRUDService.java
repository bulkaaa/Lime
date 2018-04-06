package com.modern.codes.lime.service;

import java.util.List;
import java.util.Optional;

import com.modern.codes.lime.dao.IBasicCRUDRepository;
import com.modern.codes.lime.exception.IllegalDataException;
import com.modern.codes.lime.exception.NotFoundException;
import com.modern.codes.lime.pojo.BasicPOJO;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type Basic crud service.
 *
 * @param <T>      the type parameter
 * @param <T_POJO> the type parameter
 * @param <T_DAO>  the type parameter
 */

public class BasicCRUDService<T, T_POJO, T_DAO extends IBasicCRUDRepository<T, String>> implements IBasicCRUDService {
    /**
     * Instantiates a new Basic crud service.
     *
     * @param dao        the dao
     * @param Ttype      the ttype
     * @param T_POJOtype the t poj otype
     */
    BasicCRUDService(final T_DAO dao, final Class<T> Ttype, final Class<T_POJO> T_POJOtype) {
        this.dao = dao;
        this.Ttype = Ttype;
        this.T_POJOtype = T_POJOtype;
    }

    @Override
    public List<T_POJO> findAll() {
        final Optional<List<T>> t = Optional.ofNullable(dao.findAll());
        if (!t.isPresent()) {
            throw new NotFoundException(Ttype + " object could not be found in DB");
        }
        return ParseTools.parseList(t.get(), T_POJOtype);
    }

    @Override
    public void delete(final String id) {
        if (!exists(id)) {
            throw new NotFoundException(Ttype + " object could not be found in DB");
        }
        dao.delete(id);
    }

    @Override
    public T_POJO save(final Object t) {
        return ParseTools.parse(dao.save(ParseTools.parse(t, Ttype)), T_POJOtype);
    }

    @Override
    public boolean exists(final String id) {
        return dao.exists(id);
    }

    @Override
    public boolean exists(final Object t) {
        return exists(((BasicPOJO) t).getId());
    }

    @Override
    public long count() {
        return dao.count();
    }

    @Override
    public boolean equals(final Object t, final Object y) {
        try {
            return t == y;
        } catch (final Exception e) {
            throw new IllegalDataException("Trying to compare wrong type of objects it's "
                                           + t.getClass()
                                           + " and "
                                           + y.getClass()
                                           + " objects, should be "
                                           + T_POJOtype);
        }
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public T_POJO findById(final String id) {
        try {
            return ParseTools.parse(dao.findOne(id), T_POJOtype);
        } catch (final Exception e) {
            throw new NotFoundException(Ttype + " object could not be found in DB");
        }
    }

    @Override
    public void delete(final Object t) {
        if (!exists(((BasicPOJO) t).getId())) {
            throw new NotFoundException(Ttype + " object could not be found in DB");
        }
        dao.delete(ParseTools.parse(t, Ttype));
    }

    @Override
    public List<T_POJO> save(final List l) {
        return (List<T_POJO>) dao.save(ParseTools.parseList(l, Ttype));
    }

    @Override
    public void delete(final List l) {
        dao.delete(ParseTools.parseList(l, Ttype));
    }

    private final T_DAO dao;
    private final Class<T> Ttype;
    private final Class<T_POJO> T_POJOtype;
}
