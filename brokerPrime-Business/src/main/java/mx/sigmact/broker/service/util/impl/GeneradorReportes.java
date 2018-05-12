/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.sigmact.broker.service.util.impl;

import mx.sigmact.broker.service.exception.ReporterJasperException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ing Emmanuel Estrada Gonzalez
 */
public final class GeneradorReportes {

    /**
     * String de error para formato no soportado.
     */
    public static final String ERROR_TIPO_REPORTE_NO_SOPORTADO = "jasper.exception.general";
    
    /**
     * Postfijo de archivos excel con el formato mayor a 2007.
     */
    private static final String EXCEL_DESPUES_2007 = ".xlsx";

    /**
     * Postfijo de archivos excel con el formato de 2007 o inferior.
     */
    private static final String EXCEL_ANTES_2007 = ".xls";
    
    /**
     * Postfijo de archivos pdf.
     */
    private static final String ARCHIVO_PDF = ".pdf";

    private GeneradorReportes() {

    }

    /**
     * M&eacute;todo generico para crear reportes.
     *
     * @param reportIS
     * @param nombreReporte nombre del reporte con todo y su extenci&oacute;n
     * (.xls o .pdf). Ej. miReporte.pdf
     * @param parametros Mapa con los parametros que estaran en el reporte.
     * @param detalle Lista de mapas para insertar en la banda detail del
     * reporte.
     * @return arreglo de bytes del reporte generado.
     * @throws mx.sigmact.broker.service.exception.ReporterJasperException
     */
    public static byte[] crearReporte(InputStream reportIS, String nombreReporte,
            Map<String, Object> parametros,
            List<?> detalle) throws ReporterJasperException {

        ReporteJasperUtil reporte = new ReporteJasperUtil();
        reporte.setReporteJasper(reportIS);
        reporte.setNombreReporte(nombreReporte);

        if (nombreReporte.endsWith(EXCEL_ANTES_2007)) {
            reporte.setFormatoReporte(ReporteJasperUtil.XLS);
        } else if (nombreReporte.endsWith(ARCHIVO_PDF)) {
            reporte.setFormatoReporte(ReporteJasperUtil.PDF);
        } else {
            throw new ReporterJasperException(GeneradorReportes.ERROR_TIPO_REPORTE_NO_SOPORTADO);
        }

        reporte.setParametrosReporte(parametros);
        reporte.setDatosReporte(detalle);
        try {
            return reporte.generarBytesReporte();
        } catch (ReporterJasperException cex) {
            if (cex.getMessage() != null) {
                throw new ReporterJasperException(ERROR_TIPO_REPORTE_NO_SOPORTADO, cex);
            } else {
                throw new ReporterJasperException(ERROR_TIPO_REPORTE_NO_SOPORTADO, cex);
            }
        }
    }
}
