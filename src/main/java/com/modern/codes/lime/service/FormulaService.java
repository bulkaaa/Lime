package com.modern.codes.lime.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.dao.IFormulaDAO;
import com.modern.codes.lime.model.Formula;
import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.pojo.FormulaPOJO;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.tools.ParseTools;

@Service
public class FormulaService extends BasicCRUDService<Formula, FormulaPOJO, IFormulaDAO> implements IFormulaService {

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

    @Override
    public void addFormula(final Map<Resource, Double> iFormula, final ProductPOJO product) {
        final List<FormulaPOJO> formula = new ArrayList<>();
        iFormula.forEach((key, value) -> {
            final FormulaPOJO form = new FormulaPOJO();
            form.setPOJOProduct(product);
            form.setResource(key);
            form.setValue(value);
            formula.add(form);
        });
        save(ParseTools.parseList(formula, FormulaPOJO.class));
    }
    private final IFormulaDAO dao;
}