package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.repository.CommentsRepository;
import com.example.aviasales2.repository.CompanyRepository;
import com.example.aviasales2.service.CommentsService;
import com.example.aviasales2.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private CommentsRepository commentsRepository;

    @GetMapping("/{id}")
    private Company getCompanyById(@PathVariable(name = "id") long id){
        if(id>0 && companyService.findByCompanyId(id) != null){
        return companyService.findByCompanyId(id);}
        else {return null;}

    }

    @PostMapping("/save")
    private Company saveCompany(@RequestBody Company company){return companyService.save(company);}

    @GetMapping("/delete/{id}")
    private String  deleteCompany(@PathVariable(name = "id") long id){
        Company company = companyRepository.findByCompanyId(id);
        if (company!=null){
        companyService.delete(id);
        return "deleted";
        }
        else {
            return "Company does not exist!";
        }
    }

    @PostMapping("/company_comment/{comp_id}")
    private Comments saveComment(@PathVariable("comp_id") long comp_id, @RequestBody Comments comment){
        Company company = companyService.findByCompanyId(comp_id);
        company.getComments().add(comment);
        comment.setCompany(company);
        return commentsService.save(comment);
    }


    @GetMapping("/getByName")
    private Company getCompanyByName(@RequestParam(name = "name")String name){return companyService.findByCompanyName(name);}

    @PutMapping("/update")
    private String updateCompany(@RequestBody Company updatedCompany){
        Company company = companyService.findByCompanyId(updatedCompany.getCompanyId());
        if (company != null){
        companyService.save(updatedCompany);
        return "Updated";}
        return "Company does not exist!";
    }

    @GetMapping("/getByRating")
    private List<Company> getCompanyByRating(@RequestParam("rating")int rating){return companyService.findByRating(rating);}

    @PutMapping("/update_comment/{comment_id}")
    private String update(@PathVariable("comment_id") long comment_id, @RequestBody Comments comments){
        comments.setId(comment_id);
        Comments old = commentsRepository.findCommentsById(comments.getId());
        if(old!=null){
            Company company = old.getCompany();
            old.setText(comments.getText());
            old.setCompany(company);
            commentsService.save(old);
            return "Updated";}
        return "Error!";
    }

    @GetMapping()
    private List<Company> getAllCompany(){return companyService.findAll();}


}
