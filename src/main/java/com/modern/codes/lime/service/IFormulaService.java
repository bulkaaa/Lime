package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.FormulaPOJO;

import java.util.List;

public interface IFormulaService {
    List<FormulaPOJO> findAll();
    FormulaPOJO delete(String id);
    void save(Object t);
    boolean exists(String id);
}
