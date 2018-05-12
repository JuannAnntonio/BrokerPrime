/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.sigmact.broker.test;

import mx.sigmact.broker.dao.PruebaDao;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ing Emmanuel Estrada Gonzalez
 */
public class TbcDaoTest extends BaseTest{

    @Autowired
    @Qualifier("pruebaDao")
    private PruebaDao pruebaDao;

    @Test
    @Ignore
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void pruebaInyeccionDao() {
        System.out.println("todo ok");
        try {
            if (pruebaDao != null) {
                System.out.println("todo ok");
                System.out.println("Imprime resultado de la base "+pruebaDao.getTime());
            } else {
                System.err.println("Valio ");
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex);
        }
    }
}
