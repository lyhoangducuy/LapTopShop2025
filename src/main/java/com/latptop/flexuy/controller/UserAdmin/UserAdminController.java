package com.latptop.flexuy.controller.UserAdmin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    public String getUser(Model model){ 
        model.addAttribute("users",userService.findAll());
        return "admin/user/view";
    }
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userIds") List<Long> userIds) {
        // Xóa nhiều user theo ID
        userService.deleteAllById(userIds);
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
                                @RequestParam("file") MultipartFile file) {
        // Xử lý lưu file nếu có
        if (!file.isEmpty()) {
            storageService.storeFile(file);
            user.setAvatar(file.getOriginalFilename());
        }

        userService.createUser(user);
        return "redirect:/admin/user/viewUser";
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
