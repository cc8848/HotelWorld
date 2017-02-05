package edu.nju.hotel.web.controller;

import edu.nju.hotel.data.dao.UserDao;
import edu.nju.hotel.data.model.BankCard;
import edu.nju.hotel.data.model.User;
import edu.nju.hotel.data.repository.UserRepository;
import edu.nju.hotel.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by zhouxiaofan on 2017/1/26.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    @RequestMapping("/activate")
    public String activate() {
        return "users/activate";
    }

    @RequestMapping("/index")
    public String hotelList(){
        return "users/hotelList";
    }

    @RequestMapping("/hotel/{id}")
    public String hotel(@PathVariable int id){
        return "users/hotel";
    }

    @PostMapping("/booking")
    public String booking(HttpServletRequest request){
        String start= (String) request.getParameter("start");
        return "users/booking";
    }

    @PostMapping("/submitbooking")
    public String handleBooking(){
        return "redirect:/users/success";
    }

    @RequestMapping("/success")
    public String success(){
        return "users/success";
    }

    @RequestMapping("/bookHistory")
    public String bookHistory(){
        return "users/bookHistory";
    }

    @RequestMapping("/checkinHistory")
    public String checkinHistory(){
        return "users/checkinHistory";
    }

    @PostMapping("/edit")
    public String edit(){
        return "redirect:/users/account";
    }

    @RequestMapping("/account")
    public String show(Model model) {
        User u=new User();
        u.setId(12);
        model.addAttribute("userInfo",u);
        return "users/info";
    }

    @RequestMapping("/json")
    public @ResponseBody Object json() {
        List<User> list = new ArrayList<User>();
        User u=new User();
        list.add(u);
        List<User> l2=userDao.findAll();
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("score","ss");
        modelMap.addAttribute("list",list);
//        modelMap.addAttribute("l2",l2);
        User u2=l2.get(0);
        BankCard b=u2.getBankCardByBank();
        System.out.println(u2.getId());
        System.out.println(u2.getIdcard());
        System.out.println(u2.getLevel());
        System.out.println(b.getBalance());
        System.out.println(b.getName());
        return modelMap;
    }
}