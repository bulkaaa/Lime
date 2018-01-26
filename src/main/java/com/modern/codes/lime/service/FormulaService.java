package com.modern.codes.lime.service;

import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.dao.IFormulaDAO;
import com.modern.codes.lime.model.Formula;
import com.modern.codes.lime.pojo.FormulaPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormulaService extends BasicCRUDService<Formula, FormulaPOJO, IFormulaDAO> implements IFormulaService {

    private final IFormulaDAO dao;
    @Autowired
    public FormulaService(final IFormulaDAO dao) {
        super(dao, Formula.class, FormulaPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<FormulaPOJO> findByProductName(final String productName) {
        return ParseTools.parseList(dao.findByProductName(productName), FormulaPOJO.class);
    }

    @Override
    public List<FormulaPOJO> findByProductId(final String id) {
        return ParseTools.parseList(dao.findByProductId(id), FormulaPOJO.class);
    }

    @Override
    public List<FormulaPOJO> findByResourceId(final String id) {
        return ParseTools.parseList(dao.findByResourceId(id), FormulaPOJO.class);
    }
}