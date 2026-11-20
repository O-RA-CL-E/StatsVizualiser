package com.basespringboot.business;

import com.basespringboot.form.Form;
import com.basespringboot.dto.FormDto;
import com.basespringboot.repository.FormRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import jakarta.transaction.Transactional;

@Service 
@Transactional 
@RequiredArgsConstructor

public class FormServiceImpl implements FormService {
    private final FormRepository formRepository;

    @Override
    public Form save(FormDto formDto) {
        Form form = new Form();
        form.setTitle(formDto.getTitle());
        return formRepository.save(form);
    }

    
}


