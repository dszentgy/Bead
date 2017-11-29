package com.githup.dszentgy.beadando.dao;

import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.PostOffice;

import java.util.Collection;

public interface PostOfficeDAO {
    public Collection<PostOffice> readPostOffice();
    public PostOffice readPostOfficeByName(String name);
    public void createPostOffice(PostOffice postOffice);
    public void updatePostOffice(PostOffice postOffice) throws NotFoundNameException;
    public void deletePostOffice(PostOffice postOffice) throws NotFoundNameException;
}
