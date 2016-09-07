package com.rodrigo.binboard;


import com.rodrigo.binboard.model.CourseIdea;
import com.rodrigo.binboard.model.CourseIdeasDAO;
import com.rodrigo.binboard.model.SimpleCourseIdeaDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.post;

public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");
        port(8080);

        CourseIdeasDAO dao = new SimpleCourseIdeaDAO();

        get("/", (req, res) -> {
            Map<String, String> model = new HashMap<>();
            model.put("username", req.cookie("username"));
            return new ModelAndView( model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("sign-in", (req, res) -> {
            Map<String, String> model = new HashMap<>();
            String username = req.queryParams("username");
            res.cookie("username", username);
            model.put("username", username);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/ideas", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("ideas", dao.findAll());
            return new ModelAndView(model, "ideas.hbs");
        }, new HandlebarsTemplateEngine());

        post("/ideas", (req, res) -> {
            String title = req.queryParams("title");
            CourseIdea courseidea = new CourseIdea(title, req.cookie("username"));
            dao.add(courseidea);
            res.redirect("/ideas");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
