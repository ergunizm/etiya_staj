package com.etiya.staj.business;

import com.etiya.staj.model.Control;
import com.etiya.staj.utility.results.DataResult;
import com.etiya.staj.utility.results.Result;

import java.util.List;

public interface ControlService {
    List<Control> getAllControls();
    Control getSingleControlById(Long id);
    DataResult<Control> addControl(Control control);
    DataResult<Control> updateControl(Control control);
    Result deleteControl(Long id);
}
