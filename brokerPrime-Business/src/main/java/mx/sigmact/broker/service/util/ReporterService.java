/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.sigmact.broker.service.util;

import java.util.List;
import java.util.Map;
import mx.sigmact.broker.base.enums.ReportsEnum;
import mx.sigmact.broker.service.exception.ReporterJasperException;

/**
 *
 * @author Ing Emmanuel Estrada Gonzalez
 */
public interface ReporterService {
    
    String MSG_ERROR = "jasper.exception.general";

    byte[] makeReport(ReportsEnum reporteEnum, String nombreReporte,
                                      Map<String, Object> parametros,
                                      List<?> detalle) throws ReporterJasperException;
    
}
