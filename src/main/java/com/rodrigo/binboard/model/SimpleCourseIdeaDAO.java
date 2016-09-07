package com.rodrigo.binboard.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo on 07/09/2016.
 */
public class SimpleCourseIdeaDAO implements CourseIdeasDAO{
    private List<CourseIdea> ideas;

    public SimpleCourseIdeaDAO() {
        this.ideas = new ArrayList<>();
    }

    @Override
    public boolean add(CourseIdea idea) {
        return ideas.add(idea);
    }

    @Override
    public List<CourseIdea> findAll() {
        return new ArrayList<>(ideas);
    }
}
