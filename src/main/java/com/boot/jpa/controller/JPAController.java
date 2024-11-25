package com.boot.jpa.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.jpa.model.dao.JpaDao;
import com.boot.jpa.model.entity.JpaEntity;

@Controller
@RequestMapping("/member")
public class JPAController {

	@Autowired
	private JpaDao dao;
	
	@GetMapping("/list")
	public String selectList(Model model) {
		
		//List<JpaEntity> list = dao.findAll();
		
		List<JpaEntity> list = dao.findAll(Sort.by(Sort.Direction.DESC, "myno"));
		model.addAttribute("list", list);
		System.out.println(list.size());
		return "jpalist";
	}
	
	@GetMapping("/insertform")
	public String insertform() {
		
		return "jpainsert";
	}
	
	@PostMapping("/insert")
	public String insert(JpaEntity dto) {
		dto.setMydate(new Date());
		dao.save(dto);
		
		return "redirect:list";
	}
	
	@GetMapping("/detail")
	public String detail(int myno, Model model) {
		JpaEntity dto = dao.findByMyno(myno);
		//findBy필드이름 : 필드 이름 기반으로 조회 
		
		model.addAttribute("dto",dto);
		
		return "jpadetail";
	}
	
	@GetMapping("/updateform")
	public String updateForm(int myno, Model model) {
		model.addAttribute("dto", dao.findByMyno(myno));
		return "jpaupdate";
	}
	
	@PostMapping("/update")
	public String updateRes(JpaEntity dto) {
		dao.save(dto);
		return "redirect:detail?myno="+dto.getMyno();
				
	
	}
	
	@GetMapping("/delete")
	public String delete(int myno) {
		
		dao.deleteByMyno(myno);
		return "redirect:list";
	}
}
