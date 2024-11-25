package com.boot.jdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.jdbc.model.biz.MyBiz;
import com.boot.jdbc.model.dto.MyDto;

@Controller
@RequestMapping("/myboard")
public class MyBoardController {
	
	@Autowired
	private MyBiz biz;
	
	//myboard/list 요청일때 처리해주겠다.
	@GetMapping("/list") 
	public String selectList(Model model) {
		List<MyDto> res = biz.selectList();
		model.addAttribute("list",res);
		return "myboardlist";
	}
	
	@GetMapping("/detail")
	public String selectOne(int myno, Model model) {
		
		MyDto dto = biz.selectOne(myno);
		model.addAttribute("dto",dto);
		
		return "myboarddetail";
	}
	
	@GetMapping("/insertform")
	public String insertForm() {
		
		return "myboardinsert";
	}
	
	@PostMapping("/insert")
	public String insertRes(MyDto dto) {
		int res = biz.insert(dto);
		
		if(res>0) {
			return "redirect:/myboard/list";
		}
		else {
			return "redirect:/myboard/insertform";
			
		}
		
	}
	
	//[1]
	//'/myboard/update' 요청을 처리하는 메소드
	//수정하려는 게시글 하나 db로부터 select 후
	// myboardupdate.jsp로 응답
	
	@GetMapping("/updateform")
	public String updateForm(int myno, Model model) {
		model.addAttribute("dto", biz.selectOne(myno));
		
		return "myboardupdate";
	}
	
	//[2]
	//'/myboard/update' 요청을 처리하는 메소드
	// db에 입력한 내용으로 수정 진행 -
	// UPDATE MYBOARD SET MYTITLE=${mytitle}, MYCONTENT=#{mycontent} WHERE MYNO="#{myno}
	// myboardlist.jsp로 응답
	@PostMapping("/update")
	public String updateRes(MyDto dto) {
		int res = biz.update(dto);
		
		if(res>0) {
			return "redirect:/myboard/list";
		}
		else {
			return "redirect:/myboard/updateform?myno="+dto.getMyno();
		}
	}
	
	@GetMapping("/delete")
	public String delete(int myno) {
		if(biz.delete(myno)>0) {
			return "redirect:/myboard/list";
		}
		else {
			return "redirect:/myboard/detail?myno="+myno;
		}
	}
	
	

}
