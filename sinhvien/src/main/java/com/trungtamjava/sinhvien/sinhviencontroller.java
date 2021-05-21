package com.trungtamjava.sinhvien;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trungtamjava.sinhvien.controller.sinhvien;

@RestController
public class sinhviencontroller {
	private List<sinhvien> sv= new ArrayList<sinhvien>();
	
	@PostMapping(value = "/sv")
	public sinhvien createNew(@RequestBody sinhvien model) {
		sv.add(model);
		return model;
	}
	@GetMapping(value="/sv/")
	public List<sinhvien> getAll(){
		return sv;
	}
	
	
	@DeleteMapping(value = "/sv")
	public void delete(@RequestParam(name="msv") int msv) {
		for (int i=0;i<sv.size();i++) {
			if(sv.get(i).getMsv().equals(msv)) {
			sv.remove(i);
			break;
		}
		}
	}
	
	@PutMapping(value = "/sv")
	public void update(@RequestBody sinhvien model) {
		for (int i=0;i<sv.size();i++) {
			if(sv.get(i).getMsv() == model.getMsv()) {
			sv.set(i, model);
			break;
			}
		}
	}
}
