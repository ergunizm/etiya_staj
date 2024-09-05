package com.etiya.staj.controller;

import com.etiya.staj.business.ControlService;
import com.etiya.staj.model.Control;
import com.etiya.staj.utility.results.DataResult;
import com.etiya.staj.utility.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/namespaces")
@CrossOrigin(origins = "http://localhost:4200")
public class ControlController {
    @Autowired
    private ControlService service;
    @GetMapping()
    public List<Control> getAll(){
        return service.getAllControls();
    }

    @GetMapping("/{id}")
    public Control getById(@PathVariable("id") Long id){
        return service.getSingleControlById(id);
    }

    @PostMapping()
    public DataResult<Control> add(@RequestBody() Control control){
        return service.addControl(control);
    }

    @PutMapping("/update")
    public DataResult<Control> update(@RequestBody() Control control){
        return service.updateControl(control);
    }

    @DeleteMapping()
    public Result delete(@RequestParam(required = true) Long id){
        return service.deleteControl(id);
    }
}
