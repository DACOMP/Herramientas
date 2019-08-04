package com.dacompsc.general.util;

import android.util.Log;

/**
 * @author isaaclopez
 *
 */
public class DebugClass {

	/**
	 * Variable que indica si se muestran los log o no
	 * Valor por default false
	 */
	public static final boolean debug = com.dacompsc.general.AplicationGeneral.DEBUG;

	/**
     * Funcion estatica que es utilizada para imprimir los log's que sirven para debuguear 
     * Solo se imprimiran si la variable {@value debug} es habilitada
     * 
     * @param TAG etiqueta que identifica la clase de la imprecion
     * @param text vector de String para mostar en log cat
     * @param type valor de enumeracion Tipo para tipo de error si es null el tipo por default es info
     * @param exception imprime excepcion si es diferente de null
     */
	@Deprecated
    public static void printLog(String TAG,Tipo type,Exception exception,String... text) {
    	if(debug) {
			StringBuilder str = new StringBuilder();
			for (String item: text) {
				str.append(item).append("\n");
			}
			if(str.toString().isEmpty()) {
				str.append("Vacio");
			}
			switch(type){
				case DEBUG:
					if(exception==null) {
						Log.d(TAG, str.toString());
					}else {
						Log.d(TAG, str.toString(),exception);
					}
					break;
				case ERROR:
					if(exception==null) {
						Log.e(TAG, str.toString());
					}else {
						Log.e(TAG, str.toString(),exception);
					}
					break;
				case INFO:
					if(exception==null) {
						Log.i(TAG, str.toString());
					}else {
						Log.i(TAG, str.toString(),exception);
					}
					break;
				case VERBOSE:
					if(exception==null) {
						Log.v(TAG, str.toString());
					}else {
						Log.v(TAG, str.toString(),exception);
					}
					break;
				case WARN:
					if(exception==null) {
						Log.w(TAG, str.toString());
					}else {
						Log.w(TAG, str.toString(),exception);
					}
					break;
				default:
					if(exception==null) {
						Log.i(TAG, str.toString());
					}else {
						Log.i(TAG, str.toString(),exception);
					}
					break;
			}
    	}
	}

    /**
     * Funcion estatica que es utilizada para imprimir los log's de error que sirven para debuguear
     * Solo se imprimiran si la variable {@value debug} es habilitada
     *
     * @param TAG etiqueta que identifica la clase de la impresión
     * @param exception imprime excepción si es diferente de null
     * @param text vector de String para mostar en log cat
     */
    public static void printErrorLog(String TAG,Exception exception,String... text) {
        if(debug) {
            if(TAG == null){
                throw new NullPointerException("TAG is null");
            }
            StringBuilder str = new StringBuilder();
            if(text == null){
                text = new String[]{"null"};
            }
            for (String item: text) {
                str.append(item).append("\n");
            }
            if(str.toString().isEmpty()) {
                str.append("Vacio");
            }
            if(exception==null) {
                Log.e(TAG, str.toString());
            }else {
                Log.e(TAG, str.toString(),exception);
            }
        }
    }

    /**
     * Funcion estatica que es utilizada para imprimir los log's que sirven para debuguear
     * Solo se imprimiran si la variable {@value debug} es habilitada
     *
     * @param TAG etiqueta que identifica la clase de la imprecion
     * @param text vector de String para mostar en log cat
     * @param type valor de enumeracion Tipo para tipo de error si es null el tipo por default es info
     */
    public static void printTLog(String TAG,Tipo type,String... text) {
        if(debug) {
            if(TAG == null){
                throw new NullPointerException("TAG is null");
            }
            StringBuilder str = new StringBuilder();
            if(text == null){
                text = new String[]{"null"};
            }
            for (String item: text) {
                str.append(item).append("\n");
            }
            if(str.toString().isEmpty()) {
                str.append("Vacio");
            }
            switch(type){
                case DEBUG:
                    Log.d(TAG, str.toString());
                    break;
                case INFO:
                    Log.i(TAG, str.toString());
                    break;
                case VERBOSE:
                    Log.v(TAG, str.toString());
                    break;
                case WARN:
                    Log.w(TAG, str.toString());
                    break;
                default:
                    Log.i(TAG, str.toString());
                    break;
            }
        }
    }

}
