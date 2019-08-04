/**
 * 
 */
package com.dacompsc.general.base;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import com.afollestad.materialdialogs.MaterialDialog;
import com.dacompsc.general.R;
import com.dacompsc.general.util.validator.EditTextValidator;
import com.dacompsc.general.util.validator.PoolOfValidators;

/**
 * @author isaaclopez
 *
 */
public abstract class BaseSharedActivity extends AppCompatActivity {

    /**
     * @return el toolbar de la actividad
     */
    public abstract Toolbar getToolbar();

	/**
	 * Variable que guarda los {@link android.support.design.widget.TextInputLayout} con sus respectivos {@link com.dacompsc.shared.util.validator.EditTextValidator} a ser validados
	 */
	private PoolOfValidators validator;
    /**
     * Variabel que indica el recurso para el titulo del diálogo
     */
    private int int_dtitle= R.string.dialog_title;
    /**
     * Variabel que indica el recurso para el botón positivo del diálogo
     */
    private int int_dpositive = R.string.accept;
    /**
     * Variabel que indica el recurso para el botón positivo del diálogo de confirmación
     */
    private int int_dconfirm = R.string.confirm;
    /**
     * Variabel que indica el recurso para el botón negativo de los diálogos
     */
    private int int_dnegative = R.string.cancel;

    protected void setValidator(TextInputLayout text, EditTextValidator validator){
        if(this.validator==null){
            this.validator = new PoolOfValidators();
        }
        this.validator.putValidator(text,validator);
    }

    protected boolean isValidAllEditText(){
        return this.validator==null || this.validator.isValid();
    }

    /**
     * Indica el recurso a poner como título en el diálogo
     * @param titleDialog id del recurso
     */
    protected void setTitleDialog(@IntegerRes int titleDialog){
        this.int_dtitle = titleDialog;
    }

    /**
     * Indica el recurso a poner como texto del botón positivo en el dialogo
     * @param positive id del recurso
     */
    protected void setTextBottomPosDialog(@IntegerRes int positive){
        this.int_dpositive = positive;
    }

    /**
     * Indica el recurso a poner como texto del botón de confirmacion en el dialogo
     * @param confirm id del recurso
     */
    protected void setTextBottomConDialog(@IntegerRes int confirm){
        this.int_dconfirm = confirm;
    }

    /**
     * Indica el recurso a poner como texto del botón negativo en el dialogo
     * @param negative id del recurso
     */
    protected void setTextBottomNegDialog(@IntegerRes int negative){
        this.int_dnegative = negative;
    }

    /**
     * Dialogo informativo
     * @param text el texto informativo
     * @param callback el callback al precionar aceptar
     * @return el dialogo
     */
    protected MaterialDialog.Builder alert(int text, MaterialDialog.SingleButtonCallback callback){
        return new MaterialDialog.Builder(this)
                .title(this.int_dtitle)
                .content(text)
                .positiveText(this.int_dpositive)
                .onPositive(callback)
                .canceledOnTouchOutside(false)
                .cancelable(false);
    }

    /**
     * Dialogo de confirmación
     * @param text el texto a mostrar
     * @param confirm callback con función a ejecutar
     * @return el dialogo
     */
    protected MaterialDialog.Builder confirm(int text, MaterialDialog.SingleButtonCallback confirm){
        return new MaterialDialog.Builder(this)
                .title(this.int_dtitle)
                .content(text)
                .positiveText(this.int_dconfirm)
                .negativeText(this.int_dnegative)
                .onPositive(confirm);
    }

    /**
     * Dialogo informativo
     *
     * @param text     el texto informativo
     * @param callback el callback al precionar aceptar
     * @return el dialogo
     */
    protected MaterialDialog.Builder alert(String text, MaterialDialog.SingleButtonCallback callback) {
        return new MaterialDialog.Builder(this)
                .title(this.int_dtitle)
                .content(text)
                .positiveText(this.int_dpositive)
                .onPositive(callback)
                .canceledOnTouchOutside(false)
                .cancelable(false);
    }

    /**
     * Dialogo Error
     *
     * @param text     el texto de error
     * @param callback el callback al precionar aceptar
     * @return el dialogo
     */
    protected MaterialDialog.Builder error(String text, MaterialDialog.SingleButtonCallback callback) {
        return new MaterialDialog.Builder(this)
                .title(R.string.error)
                .content(text)
                .positiveText(this.int_dpositive)
                .onPositive(callback)
                .canceledOnTouchOutside(false)
                .cancelable(false);
    }

    /**
     * Dialogo informativo
     *
     * @param text el texto informativo
     * @return el dialogo
     */
    protected MaterialDialog.Builder loading(int text) {
        return new MaterialDialog.Builder(this)
                .content(text)
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .cancelable(false);
    }

    /**
     * Ocultar teclado despues de checar si existe algun error
     */
    protected void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
