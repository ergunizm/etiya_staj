package com.etiya.staj.business;

import com.etiya.staj.dataAccess.ControlRepository;
import com.etiya.staj.model.Control;
import com.etiya.staj.utility.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlManager implements ControlService{
    @Autowired
    private ControlRepository repository;

    @Override
    public List<Control> getAllControls() {
        return repository.findAll();
    }

    @Override
    public Control getSingleControlById(Long id) {
        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        return null;
    }

    @Override
    public DataResult<Control> addControl(Control control) {
        return new SuccessDataResult<>("Namespace added successfully!",repository.save(control));
    }

    @Override
    public DataResult<Control> updateControl(Control control) {
        if(repository.findById(control.getId()).isPresent()){
            Control tmp = repository.findById(control.getId()).get();
            tmp.setNamespace(control.getNamespace());
            tmp.setControlKey(control.getControlKey());
            repository.save(tmp);

            return new SuccessDataResult<>("Namespace is updated successfully!",tmp);
        }

        return new ErrorDataResult<>("Namespace could not be updated!");
    }

    @Override
    public Result deleteControl(Long id) {
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return new SuccessResult("Control is deleted!");
        }
        return new ErrorResult("Control could not be found!");
    }
}
