package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.FormulaPOJO;

import java.util.List;

public interface IFormulaService {
    List<FormulaPOJO> findAll();
    void delete(String id);
    void save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    FormulaPOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);
}
