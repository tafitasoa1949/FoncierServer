/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package service;

import dao.TanyDAO;
import dtoFoncier.Detailtany;
import dtoFoncier.Tany;
import java.sql.Connection;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Tafitasoa-P15B-140
 */
@Stateless
public class FoncierBean implements FoncierBeanLocal {

    @Override
    public String huhu() {
        return "gggfgg";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Tany> getListTany(Connection con,int cin) throws Exception {
        return TanyDAO.getTanyByPersonne(con,cin);
    }

    @Override
    public Tany getById(Connection con, int id) throws Exception {
        return TanyDAO.getById(con, id);
    }

    @Override
    public List<Detailtany> getAllBornes(Connection con, int idTany) throws Exception {
        return TanyDAO.getAllBorne(con, idTany);
    }

    @Override
    public void insertNew(Connection cnctn, Tany tany, Detailtany[] list_detailtany) throws Exception {
        TanyDAO.insert(cnctn, tany, list_detailtany);
    }

    @Override
    public void insertDetailTany(Connection con, Detailtany detailTany) throws Exception {
        TanyDAO.insertDetailTany(con, detailTany);
    }
}
