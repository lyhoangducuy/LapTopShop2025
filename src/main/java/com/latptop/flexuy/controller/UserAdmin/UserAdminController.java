package com.latptop.flexuy.controller.UserAdmin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.latptop.flexuy.dto.UserUpdateDTO;
import com.latptop.flexuy.model.Role;
import com.latptop.flexuy.model.User;
import com.latptop.flexuy.service.UserService;
import com.latptop.flexuy.service.role.RoleService;
import com.latptop.flexuy.service.uploadFile.StorageService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/admin/user")
public class UserAdminController {
    private final UserService userService;
    private final RoleService roleService;
    
    public UserAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @Autowired
    private StorageService storageService;
    @GetMapping("/viewUser")
    public String getUser(Model model,HttpSession session,@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,@Param("keyword") String keyword) {
         
        Page<User> list=userService.getAll(pageNo);
        
        if (keyword != null && !keyword.isEmpty()) {
            list = userService.searchUser(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        }
        
        model.addAttribute("totalPage",list.getTotalPages());
        model.addAttribute("currentPage",pageNo);

        model.addAttribute("users",list);

        List<Long> checkedIds = (List<Long>) session.getAttribute("checkedUserIds");
        if (checkedIds == null) checkedIds = new ArrayList<>();
        model.addAttribute("checkedIds", checkedIds);

        return "admin/user/view";
    }

    @PostMapping("/delete")
    public String deleteUser(HttpSession session,
                            @RequestParam("action") String action,@RequestParam("userIds") List<Long> userIds) {
        userService.deleteAllByIdReal(session, action,userIds);
        return "redirect:/admin/user/viewUser";
    }


    @GetMapping("/detailUser/{id}")
    public String getUserDetail(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id); // gọi service lấy user theo id
        model.addAttribute("user", user);       // truyền user sang Thymeleaf
        return "admin/user/detailUser";         // trả về template
    }
    @GetMapping("/create")
    public String getCreateUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/createUser";
    }
    @PostMapping("/create")
    public String postCreateUser(@ModelAttribute User user, 
                                @RequestParam("file") MultipartFile file, Model model) {
        // Xử lý lưu file nếu có
        if (userService.findByEmail(user.getEmail())==null){
            userService.createUser(user);
            if (!file.isEmpty()) {
                storageService.storeFile(file);
                user.setAvatar(file.getOriginalFilename());
            }
            return "redirect:/admin/user/viewUser";
        }else 
            model.addAttribute("error","Email da ton tai");
        return "admin/user/createUser";
        
    }
    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable("id") Long id, Model model) {
        List<Role> listRoles = roleService.findAll(); 
    
        // 3. THÊM danh sách Roles vào Model
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("user", userService.findById(id));
        return "admin/user/editUser";
    }
    @PostMapping("/edit")
    public String postEdit(@ModelAttribute("user") UserUpdateDTO userDTO,@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            storageService.storeFile(file);
            userDTO.setAvatar(file.getOriginalFilename());
        }
       userService.update(userDTO);
        return "redirect:/admin/user/viewUser";
    }
    
    
}
