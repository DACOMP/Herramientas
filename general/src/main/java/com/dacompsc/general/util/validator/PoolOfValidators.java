package com.dacompsc.general.util.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by isaaclopez on 19/01/16.
 * Clase que ayuda a administrar las validaciones de los {@link EditText} registradas en esta clase
 */
public class PoolOfValidators {

    /**
     * Map que contiene los validadores y son identificados por el ID del EditText
     */
    Map<Integer,List<EditTextValidator>> pool;
    /**
     * Map que contiene los EditText identificados por sus ID's
     */
    Map<Integer, TextInputLayout> poolEditText;

    public PoolOfValidators(){
        pool = new HashMap<>();
        poolEditText = new HashMap<>();
    }

    /**
     * Funcion para ir agregando validadores a la lista en su respectivo {@link EditText}
     * @param edit el campo de texto al que se le agregara el {@link EditTextValidator}
     * @param validator el validador a cumplir
     */
    public void putValidator(final TextInputLayout edit, EditTextValidator validator){
        List<EditTextValidator> list;
        if(pool.containsKey(edit.getId())){
            list = pool.get(edit.getId());
            list.add(validator);
            poolEditText.remove(edit);
            poolEditText.put(edit.getId(), edit);
        }else{
            list = new ArrayList<>();
            list.add(validator);
            pool.put(edit.getId(),list);
            poolEditText.put(edit.getId(),edit);
        }
    }

    public boolean isValid(){
        if (pool == null || pool.isEmpty()) {
            return true;
        }
        boolean valid = true;
        for (Map.Entry<Integer, List<EditTextValidator>> element : pool.entrySet()){
            EditText edit = poolEditText.get(element.getKey()).getEditText();
            if(edit==null){
                return false;
            }
            CharSequence text = edit.getText();
            boolean isEmpty = text.length() == 0;
            boolean local = true;
            for (EditTextValidator validator:element.getValue()) {
                boolean res = validator.isValid(text, isEmpty);
                valid = valid && res;
                local = res;
                if (!local) {
                    poolEditText.get(element.getKey()).setError(validator.getErrorMessage());
                    break;
                }
            }
            if(local){
                poolEditText.get(element.getKey()).setErrorEnabled(false);
            }
        }
        return valid;
    }

    public void removeValidator(final TextInputLayout edit, EditTextValidator validator){
        List<EditTextValidator> list;
        if(pool.containsKey(edit.getId())){
            list = pool.get(edit.getId());
            list.remove(validator);
            poolEditText.remove(edit);
            poolEditText.put(edit.getId(), edit);
        }
    }
    public void removeAllValidator(final TextInputLayout edit){
        List<EditTextValidator> list;
        if(pool.containsKey(edit.getId())){
            poolEditText.get(edit.getId()).setErrorEnabled(false);
            pool.remove(edit.getId());
            poolEditText.remove(edit.getId());
        }
    }

}
