package com.rodrigo.binboard.model;

import java.util.List;

/**
 * Created by Rodrigo on 07/09/2016.
 */
public interface CourseIdeasDAO {
    boolean add(CourseIdea ides);

    List<CourseIdea> findAll();
}
