package com.my.member.controller;

import com.my.member.dto.MemberDto;
import com.my.member.entity.Member;
import com.my.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {
    // 서비스를 가져와서 실행 준비
    // 1. @Autowired 사용
    @Autowired
    MemberService service;

    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("title", "리스트보기");
        // 서비스에 멤버리스트 정보 요청
        List<MemberDto> memberList = service.getAllList();
        model.addAttribute("list", memberList);
        return "showMember";
    }

    @GetMapping("/member/insertForm")
    public String insertFormView(Model model) {
        // insertForm에 껍데기 DTO 보냄.
        model.addAttribute("dto", new MemberDto());
        return "insertForm";
    }

    @PostMapping("/member/insert")
    // Validation 체크 수행 함.
    public String insert(@Valid @ModelAttribute("dto") MemberDto dto,
                         BindingResult bindingResult) {
        // 0. DTO에서 설정한 Validation에 오류가 있는 지 검사
        // 만약, 오류가 있다면 insertForm을 다시 보여 준 후 종료
        if (bindingResult.hasErrors()) {
            return "insertForm";
        }

        // 1. 폼에서 보낸 정보를 DTO로 받는다.
        System.out.println(dto);
        // 2. 받은 DTO를 서비스로 보낸다.
        service.insertMember(dto);
        // 3. 서비스에서 DTO를 엔티티로 바꾼다.
        // 4. 리포지토리를 이용해서 저장한다.
        // 5. 메인 리스트화면으로 돌아간다.
        return "redirect:/list";
    }

    @PostMapping("/member/delete/{id}")
    public String deleteMember(@PathVariable("id")Long id) {
        service.deleteMember(id);
        return "redirect:/list";
    }

    @GetMapping("/member/updateView")
    public String updateView(
            @RequestParam("updateId")Long updateId,
            Model model) {
        // 1. 받은 수정 아이디로 데이터를 검색해 온다.(DTO)
        MemberDto dto = service.findMember(updateId);
        // 2. DTO가 비어있는지 확인한다. ID의 유무를 확인 -> 조치
        if (ObjectUtils.isEmpty(dto)) {
            return "redirect:/list";
        } else {
            // 3. 받은 DTO를 수정폼으로 보낸다.
            model.addAttribute("dto", dto);
            return "updateForm";
        }
    }

    @PostMapping("/member/update")
    public String update(@Valid @ModelAttribute("dto")MemberDto dto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateForm";
        }
        service.updateMember(dto);
        return "redirect:/list";
    }

    @GetMapping("/member/search")
    public String search(@RequestParam("type")String type,
                         @RequestParam("keyword")String keyword,
                         Model model) {
        List<MemberDto> searchList = service.searchMember(type, keyword);
        if (ObjectUtils.isEmpty(searchList)) {
            // 검색 결과가 없을 경우
            searchList = null;
            model.addAttribute("list", searchList);
        } else {
          // 검색 결과가 있을 경우
            model.addAttribute("list", searchList);
        }
        return "showMember";
    }
}











