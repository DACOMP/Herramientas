package com.dacompsc.general.util.validator;

import android.support.annotation.NonNull;

/**
 * @author isaaclopez
 *
 */
public class EmptyValidtor extends EditTextValidator {
	
	/**
	 * Verifica que no este vacia la cadena estraida del {@link android.widget.EditText}
	 * @param errorMessage el mensaje de error
	 * @return la nueva instancia pra la validacion
	 */
	public static EmptyValidtor newInstance(String errorMessage){
		return new EmptyValidtor(errorMessage);
	}

	/**
	 * @param errorMessage el mensaje de error
	 */
	private EmptyValidtor(String errorMessage) {
		super(errorMessage);
	}

	/* (non-Javadoc)
	 * @see com.dacompsc.yunius.util.EditTextValidator#isValid(java.lang.CharSequence, boolean)
	 */
	@Override
	public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
		return !isEmpty;
	}

}
