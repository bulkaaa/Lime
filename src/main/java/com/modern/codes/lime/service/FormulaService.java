package com.modern.codes.lime.service;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.dao.IFormulaDAO;
import com.modern.codes.lime.model.Formula;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.pojo.FormulaPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormulaService extends BasicCRUDService<Formula, FormulaPOJO, IFormulaDAO> implements IFormulaService {

    private IFormulaDAO dao;
    @Autowired
    public FormulaService(IFormulaDAO dao) {
        super(dao, Formula.class, FormulaPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<FormulaPOJO> findByProduct(Product product) {
        return ParseTools.parseList(dao.findByProduct(product), FormulaPOJO.class);
    }
}