package com.basespringboot.business;

import com.basespringboot.form.Form;
import com.basespringboot.dto.FormDto;

public interface FormService {
    Form save(FormDto form);
}
