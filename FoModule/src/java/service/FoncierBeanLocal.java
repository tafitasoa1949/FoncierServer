/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package service;

import dtoFoncier.Detailtany;
import dtoFoncier.Tany;
import java.sql.Connection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tafitasoa-P15B-140
 */
@Local
public interface FoncierBeanLocal {
    public String huhu();
    public List<Tany> getListTany(Connection con,int cin)throws Exception;
    public Tany getById(Connection con,int id)throws Exception;
    public List<Detailtany> getAllBornes(Connection con,int idTany)throws Exception;
    public void insertNew(Connection con,Tany tany,Detailtany[] list_detailtany)throws Exception;
    public void insertDetailTany(Connection con,Detailtany detailTany)throws Exception;
}
