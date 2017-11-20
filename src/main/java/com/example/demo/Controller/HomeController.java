package com.example.demo.Controller;


import com.cloudinary.utils.ObjectUtils;
import com.example.demo.Config.CloudinaryConfig;
import com.example.demo.Entity.Actor;
import com.example.demo.Entity.Movie;
import com.example.demo.Repository.ActorRepository;
import com.example.demo.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    ActorRepository actorRepository;
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CloudinaryConfig cloudc;



    @RequestMapping("/")
    public String listActors(Model model)
    {
        model.addAttribute ( "actors",actorRepository.findAll () );

        return "list";
    }

    @GetMapping("/add")
    public String newActor(Model model)
    {
        model.addAttribute ( "actor",new Actor () );
        model.addAttribute ( "movie",new Movie () );

        return "form";
    }

    @PostMapping("/addprocess")
    public String processActor( @ModelAttribute("actor") Actor actor, @ModelAttribute ("movie") Movie movie, @RequestParam("file")MultipartFile file)
    {
        if(file.isEmpty ())
        {
            return "redirect:/add";
        }
        try{

            actor.getName ();
            actor.getRealname ();


            Map uploadResult= cloudc.upload ( file.getBytes (), ObjectUtils.asMap ( "resourcetype","auto" ) );

        //     byte[] uploadResult=cloudc.upload ( file.getBytes (),ObjectUtils. ( "resourcetype","auto" ) );
            actor.setHeadshot ( uploadResult.get ( "url" ).toString () );
            movie.getTitle ();
            movie.getYear ();
            movie.addActor ( actor );
            actor.addMovie ( movie );
            actorRepository.save ( actor );
//            movie.setTitle ( movie.getTitle () );
//            movie.setYear ( movie.getYear () );
//            movie.setDescription ( movie.getDescription () );



            movieRepository.save ( movie );



        }catch(IOException e)
        {
            e.printStackTrace ();

            return "redirect:/add";
        }

        return "redirect:/";
    }


}
