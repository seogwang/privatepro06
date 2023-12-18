package com.privatepro06.controller;

import com.privatepro06.dto.BoardDTO;
import com.privatepro06.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api/list")
    @ResponseBody
    public List<BoardDTO> findAll(){
        List<BoardDTO> list = boardService.findAll();
        return list;
    }

    @GetMapping("/api/read")
    @ResponseBody
    public BoardDTO read(Long bno){
        BoardDTO dto = boardService.findByBno(bno);
        return dto;
    }

    @PostMapping("/api/write")
    @ResponseBody
    public Long register(@Valid BoardDTO dto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        }
        Long bno = boardService.register(dto);
        return bno;
    }

    @PostMapping("/api/modify")
    @ResponseBody
    public BoardDTO modify(@Valid BoardDTO dto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            String link = "bno="+dto.getBno();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno", dto.getBno());
        }
        boardService.modify(dto);
        redirectAttributes.addFlashAttribute("result", "수정 완료");
        redirectAttributes.addAttribute("bno", dto.getBno());
        return dto;
    }

    @PostMapping("/api/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("result", "삭제 완료");
        return "redirect:/apiboard/list";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','TEACHER')")
    @GetMapping("/board/list")
    public String boardList(Model model){
        List<BoardDTO> list = boardService.findAll();
        model.addAttribute("list", list);
        return "board/list";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','TEACHER')")
    @GetMapping("/board/read")
    public String readOne(Long bno, Model model){
        BoardDTO dto = boardService.findByBno(bno);
        model.addAttribute("dto", dto);
        return "board/read";
    }
}
