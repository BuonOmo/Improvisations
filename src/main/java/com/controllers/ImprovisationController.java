package com.controllers;

import com.dao.CategoryRepository;
import com.dao.ImprovisationRepository;
import com.entities.Category;
import com.entities.Improvisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping("/improvisation")
public class ImprovisationController {

    @Autowired
    private ImprovisationRepository improvisationRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(path = "/greeting",method = GET)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String greetings() {
        return "Hello, world!";
    }

    @RequestMapping(path = "", method = POST)
    public Improvisation save(@RequestBody Improvisation i) {
        System.err.println(i);
        Category found = categoryRepository.findByName(i.getCategory().getName());
        if (found == null) {
            Category cat = i.getCategory();
            if (cat != null && cat.getName() != null && !cat.getName().equals("") )
                categoryRepository.save(i.getCategory());
            else
                i.setCategory(null);
        } else {
            i.setCategory(found);
        }
        System.err.print(i);
        improvisationRepository.save(i);
        return i;
    }

    @RequestMapping(path = "", method = GET)
    public List<Improvisation> all() {
        return improvisationRepository.findAll();
    }

    @RequestMapping(path = "/:id", method = DELETE)
    public void delete(@RequestParam("id") Long id){
        improvisationRepository.delete(id);
    }

    // ------------------------------------------------------------------------------------------------- TIME CONVERSION
    /**
     * Convert seconds to a mm:ss string
     * @param duration duration to convert
     * @return The converted string, "mm:ss"
     */
    public static String secondsToMmss(int duration) {
        return String.format("%02d:%02d", duration / 60, duration % 60 );
    }

    /**
     * Convert a string mm:ss or ss to seconds
     * @param mmss A string with mm:ss, m:ss, m:s or ss format
     * @return The converted time in seconds
     */
    public static int mmssToSeconds(String mmss) {
        int seconds = 0;
        String [] array = mmss.split(":");
        for (int i = 0; i < array.length; i++)
            seconds += Math.pow(60, array.length-i-1) * Integer.parseInt(array[i]);

        return seconds;
    }
}
