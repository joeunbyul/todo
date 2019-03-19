
package com.example.demo.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;

@RestController
@RequestMapping(value="/todo")
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@RequestMapping(value = {"","/index","/list"}, method = RequestMethod.GET)
    public ModelAndView  main(String contents, @ModelAttribute Todo todo, Pageable pageable, @RequestParam(value="rows", defaultValue="10")  String rows){
		ModelAndView mav = new ModelAndView();
		//List<Todo> list = new ArrayList<Todo>();
		
		if(rows == null) {
			rows = "10";	
		}

		int row = Integer.valueOf(rows);
		
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, row, Sort.by("cdate").descending());
        
		if(!"".equals(contents) && contents != null) {
			Page<Todo> todoList = todoService.selectList(contents,pageable);
			mav.addObject("list", todoList);
		} else {
			Page<Todo> todoList = todoService.selectAll(pageable);
			System.out.println("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {},"+
					todoList.getTotalElements()+","+ todoList.getTotalPages()+","+ todoList.getSize()+","+
					todoList.getNumber()+","+todoList.getNumberOfElements());
			mav.addObject("list", todoList);
		}
		mav.addObject("rows", rows);
		mav.addObject("contents", contents);
		mav.setViewName("todo-list");
        return mav;
    }
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView getInserView(@ModelAttribute Todo todo, String type, String listNum, RedirectAttributes redirectAttr){
		ModelAndView mav = new ModelAndView();
		if("modify".equals(type)) {
			Todo detail = todoService.select(Integer.valueOf(listNum));
        	mav.addObject("todo", detail);
        	mav.addObject("type", type);
		} 
		/* else if ("delete".equals(type)){
			mav.setViewName("redirect:/todo/delete");
			redirectAttr.addFlashAttribute("listnum", listNum);
			
			/*RedorectAttribute
			 * POST방식으로 전달하는것처럼 보이지만 POSt방식으로 전달하는 것은 아님.
			 * 리다이렉트가 발생하기 전에 모든 플래시 속성을 세션에 복사한다. 리다이렉션 이후에는 저장된 플래시 속성을 세션에서 모델로 이동
			 * 헤더에 파라미터를 붙이지 않기 때문에 URL에 노출되지 않는것임
			 * 리다이렉트 이후에는 소멸
			 *
			
			return mav;
		} */
		mav.setViewName("todo-form");
        return mav;
    }	
	
	@RequestMapping(value = "/regist_modify", method = RequestMethod.POST)
    public ModelAndView insertList( Todo todo , String type){
        //ApiResponseMessage message = new ApiResponseMessage("Success", "등록되었습니다.", "", "");
       // ResponseEntity<ApiResponseMessage> response = new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
        
        ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/todo/list");
        try {
        	if("modify".equals(type)) {
        		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        	todo.setCdate(timestamp);
	        	todoService.modify(todo);
        	} else {
	        	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        	todo.setCdate(timestamp);
	        	todoService.insertList(todo);
        	}
        } catch(Exception ex){
            //message = new ApiResponseMessage("Failed", "사용자 등록에 실패하였습니다.", "ERROR00001", "Fail to registration for user information.");
            //response = new ResponseEntity<ApiResponseMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return mav;
    }
	
	/*@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
    public Map<String, String> deleteTodo( String listnum ){
        //ApiResponseMessage message = new ApiResponseMessage("Success", "등록되었습니다.", "", "");
       // ResponseEntity<ApiResponseMessage> response = new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
        
        try {
        	Todo deleteTodo = todoService.select(Integer.valueOf(listnum));
        	System.out.println("delete!!!!!" + deleteTodo);
        	todoService.deleteList(deleteTodo);
        } catch(Exception ex){
            //message = new ApiResponseMessage("Failed", "사용자 등록에 실패하였습니다.", "ERROR00001", "Fail to registration for user information.");
            //response = new ResponseEntity<ApiResponseMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        	
        }
        Map<String, String> result = new HashMap<String, String>();
        result.put("result","success");
        
        return result;
    }*/
	
	/*@RequestMapping(value = "/delete")
    public ModelAndView deleteTodo(HttpServletRequest request,	 String listnum ){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/todo/list");
		
    	Map<String,?> redirectMap = RequestContextUtils.getInputFlashMap(request);
    	String num = "";
    	
    	if(redirectMap != null) {
    		num = (String) redirectMap.get("listnum");
    	}
    	
    	Todo deleteTodo = todoService.select(Integer.valueOf(num));
    	todoService.deleteList(deleteTodo);
        	
        
        return mav;
    }*/
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteTodo(int listnum){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/todo/list");

    	Todo deleteTodo = todoService.select(listnum);
    	todoService.deleteList(deleteTodo);
        	
        return mav;
    }
}
