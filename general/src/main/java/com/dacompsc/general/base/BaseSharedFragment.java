package com.dacompsc.shared.base;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.dacompsc.shared.R;
import com.dacompsc.shared.util.validator.EditTextValidator;
import com.dacompsc.shared.util.validator.PoolOfValidators;
import com.redmadrobot.inputmask.MaskedTextChangedListener;
import com.redmadrobot.inputmask.helper.Mask;
import com.redmadrobot.inputmask.model.CaretString;

/**
 * @author isaaclopez
 *
 */
public abstract class BaseSharedFragment extends Fragment {

	/**
	 * Variable que guarda los {@link TextInputLayout} con sus respectivos {@link com.dacompsc.shared.util.validator.EditTextValidator} a ser validados
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


	/**
	 * Ingresa el titulo en el ActionBar
	 * @param title el titulo del toolbar
	 */
	protected void setTitle(String title){
		if(getToolbar()!=null)
			getToolbar().setTitle(title);
	}

	/**
	 * Ingresa el titulo en el ActionBar desde un recurso
	 * @param title el titulo del toolbar por id
	 */
	protected void setTitle(@StringRes int title){
		if(getToolbar()!=null)
			getToolbar().setTitle(title);
	}
	
	protected Toolbar getToolbar(){
		return ((BaseSharedActivity)getActivity()).getToolbar();
	}

    protected void setValidator(TextInputLayout text, EditTextValidator validator){
        if(this.validator==null){
            this.validator = new PoolOfValidators();
        }
        //this.validator.putValidator(text,validator);
		if(validator!=null)
			this.validator.putValidator(text,validator);
		else
			this.validator.removeAllValidator(text);

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
		return new MaterialDialog.Builder(getActivity())
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
		return new MaterialDialog.Builder(getActivity())
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
		return new MaterialDialog.Builder(getActivity())
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
		return new MaterialDialog.Builder(getActivity())
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
		return new MaterialDialog.Builder(getActivity())
				.content(text)
				.progress(true, 0)
				.canceledOnTouchOutside(false)
				.cancelable(false);
	}

	/**
	 * Funcion que agrega la mascara al input
	 * @param editText el edit text a agregar la mascara
	 * @param mask la mascara
	 * @param clistener el listener puede ser null
	 */
    protected void setMask(TextInputLayout editText, String mask, MaskedTextChangedListener.ValueListener clistener){
		if(clistener==null){
			clistener = new MaskedTextChangedListener.ValueListener() {
				@Override
				public void onTextChanged(boolean maskFilled, @NonNull final String extractedValue) {

				}
			};
		}
		MaskedTextChangedListener listener = new MaskedTextChangedListener(
				mask,
				true,
				editText.getEditText(),
				null,
				clistener
		);

		editText.getEditText().addTextChangedListener(listener);
		editText.getEditText().setOnFocusChangeListener(listener);
	}

	/**
	 * Remueve la mascara en el texto
	 * @param mask la mascara
	 * @param text el texto con mascara
	 * @return el texto sin mascara
	 */
	protected String removeMask(String mask, String text){
		Mask maskg = new Mask(mask);
		Mask.Result result = maskg.apply(
				new CaretString(
						text,
						text.length()
				),
				true // you may consider disabling autocompletion for your case
		);
		return result.getFormattedText().getString();
	}

	/**
	 * Ocultar teclado despues de checar si existe algun error
	 */
	protected void hideKeyboard() {
		View view = getActivity().getCurrentFocus();
		if (view != null) {
			((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
					hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
}
